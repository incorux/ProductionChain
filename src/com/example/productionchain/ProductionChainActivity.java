package com.example.productionchain;

import java.util.ArrayList;

import Classes.Batch;
import Classes.Product;
import Classes.ProductChainAdapter;
import Classes.ProductListAdapter;
import Classes.Resource;
import Classes.ResourceAggregator;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class ProductionChainActivity extends ListActivity {

	ArrayList<Product> products;
	ArrayList<ResourceAggregator> aggregators;
	ArrayList<Batch> batches;
	private ProductChainAdapter adapter;
	int Labor;
	double Money;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chain);
		
		Intent i = getIntent();
		if(i != null){
			products = i.getParcelableArrayListExtra("Products");
			Labor = i.getIntExtra("Labor", 0);
			Money = i.getDoubleExtra("Money", 0);
			
			Compute();
		}
		
		ListView lv = getListView();
		lv.setChoiceMode(0);
		
		final Button cancel = (Button) findViewById(R.id.productchain_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  setResult(Activity.RESULT_CANCELED, null);
				  finish();
			}
		});
	}
	
	private void Compute(){
		ArrayList<Double> productsCosts = new ArrayList<Double>();
		
		productsCosts = simulateProduction(productsCosts, Labor, Money);
		ResourceHolder holder = new ResourceHolder(Labor,Money);
		batches = new ArrayList<>();
		while(true){
			// Find most efficient formulae
			Batch batch = new Batch();
			double max = 0;
			int index = -1;
			for(int i = 0; i < productsCosts.size(); i++){
				ResourceAggregator ag = aggregators.get(i);
				if(productsCosts.get(i) > max && ag.cost <= holder.Money && ag.labor <= holder.Labor)
				{
					index = i;
					max = productsCosts.get(i);
				}
			}
			if(index == -1) break;
			
			int times = Double.valueOf(holder.Money/ aggregators.get(index).cost).intValue();
			int times2 =  holder.Labor/ aggregators.get(index).labor;
			int mintimes = Math.min(times,times2);
			Simulate(products.get(index), mintimes , holder, batch, products.get(index).value, holder.Money, holder.Labor);
			batch.AddToSell(new Resource(products.get(index)), mintimes);
			productsCosts = simulateProduction(productsCosts,holder.Labor, holder.Money);
			batch.Efficiency = (double) Math.round(max * 100) / 100;;
			batches.add(batch);
		}
	    adapter = new ProductChainAdapter(this, batches);
	    setListAdapter(adapter);	
	}

	private ArrayList<Double> simulateProduction(ArrayList<Double> productsCosts, int labor, double money) {
		aggregators = new ArrayList<ResourceAggregator>();
		productsCosts = new ArrayList<>();
		ArrayList<Boolean> valueset = new ArrayList<>();
		for(int i = 0 ; i < products.size(); i++){
			productsCosts.add(0.0);
			aggregators.add(new ResourceAggregator());
			valueset.add(false);
		}
		boolean keepGoing = true;
		while(keepGoing){
			keepGoing = false;
			for(int i = 0; i < products.size(); i++){
				Product p = products.get(i);
				if(valueset.get(i)){
					continue;
				}
				keepGoing = true;
				valueset.set(i, true);
				ResourceHolder holder = new ResourceHolder(labor,money);
				double moneybef = holder.Money;
				int laborbef = holder.Labor;
				if(Simulate(p,1,holder,null, p.value, money, labor)){  
				aggregators.get(i).cost = moneybef - holder.Money;
				aggregators.get(i).labor = laborbef - holder.Labor;
				productsCosts.set(i, (p.value - aggregators.get(i).cost) / aggregators.get(i).labor);
				}else productsCosts.set(i, -Double.MAX_VALUE);
			}
		}
		return productsCosts;
	}
	
	private Product Find(int Id){
		for(Product pr : products)
			if(pr.id == Id)
				return pr;
		return null;
	}
	
	private boolean Simulate(Product p, int quantity, ResourceHolder holder, Batch b, double value, double totalmoney, int totallabor){
		for(int i = 0 ; i < quantity ; i++){
			double moneyspent = totalmoney - holder.Money;
			int laborspent = totallabor - holder.Labor;
			double laborProfit = -1; 
			if(holder.Labor >= p.laborCost)
				laborProfit = (value - moneyspent) / (p.laborCost + laborspent);
			double moneyProfit = -1;
			if(holder.Money >= p.cost)	
				moneyProfit = (value - moneyspent - p.cost) / laborspent;
			if(p.laborCost != 0 && laborProfit > moneyProfit && holder.Labor >= p.laborCost)
			{
				holder.Labor -= p.laborCost;
				if(b!=null)
					b.AddToCreate(new Resource(p), 1);
				if(p.materials.size() > 0)
				{
					for(int j = 0; j < p.materials.size(); j++){
						Resource r = p.materials.get(j);
						if(r.quantity == 0) continue;
						if(!Simulate(Find(r.Id),r.quantity, holder, b, value, totalmoney, totallabor)) return false;
					}
				}
			}else if(p.cost != 0 && holder.Money >= p.cost)
			{
				holder.Money -= p.cost;
				if(b!=null)
					b.AddToBuy(new Resource(p), 1);
			}else{
				holder.Labor = 0;
				holder.Money = 0.0;
				return false;
			}
		}
		return true;
	}
	
	class ResourceHolder{
		public int Labor;
		public Double Money;
		
		public ResourceHolder(int labor, double money)
		{
			Labor = labor;
			Money = money;
		}
	}
}
