package com.example.productionchain;

import java.util.ArrayList;
import java.util.List;

import Classes.Product;
import Classes.Resource;
import Classes.ResourceAggregator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private ArrayList<Product> products;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		if(products == null)
			products = new ArrayList<Product>();
		
		products.add(new Product("Wood",2.0, 2.2, 2, new Resource[]{}, products));
		products.add(new Product("Plank",10.0, 10, 5, new Resource[]{}, products));
		products.add(new Product("Iron",4.0, 4, 5,new Resource[]{}, products));
		products.add(new Product("Hammer",55.0, 60.0, 5, new Resource[]{}, products));
		
		for(int i = 0; i < products.size(); i++){
			for(int j = 0; j < products.size(); j++)
				products.get(i).AddMaterial(products.get(j));
		}
		
		products.get(1).materials.get(0).quantity = 3;
		products.get(3).materials.get(1).quantity = 2;
		products.get(3).materials.get(2).quantity = 1;

		SetEvents();
	} 
	
	private void SetEvents(){
		
		final Button exit = (Button) findViewById(R.id.exitbutton);
		exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
	            System.exit(0);
			}
		});
		
		final Button productslist = (Button) findViewById(R.id.ProductsListButton);
		productslist .setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),ProductlistActivity.class);
				i.putExtra("Products", products);
				startActivityForResult(i, 0);
			}
		});
		
		final Button productschain = (Button) findViewById(R.id.productschainsettings);
		productschain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),ChainSettingsActivity.class);
				i.putExtra("Products", products);
				startActivityForResult(i, 2);
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  switch(requestCode) {
	    case (0) : {
	      if (resultCode == Activity.RESULT_OK) {
	  		Bundle extras = data.getExtras();
			if (extras != null) {
			    products = data.getParcelableArrayListExtra("Products");
			}
	      }
	      break;
	    } 
	    case (1):{
	    	break;
	      }
	  }
	}
}
