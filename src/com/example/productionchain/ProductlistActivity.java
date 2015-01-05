package com.example.productionchain;

import java.util.ArrayList;

import Classes.Product;
import Classes.ProductListAdapter;
import Classes.Resource;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ProductlistActivity extends ListActivity {

	public ArrayList<Product> products;
	private ProductListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productlist);
		
		Intent i = getIntent();
		if(i != null){
			products = i.getParcelableArrayListExtra("Products");
		    adapter = new ProductListAdapter(this, products);
		    setListAdapter(adapter);	
		}
		
		SetEvents();
	}
	
	private void SetEvents(){
		final Button addproduct = (Button) findViewById(R.id.productslist_add);
		addproduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), AddproductActivity.class);
				ArrayList<Resource> res = new ArrayList<>();
				for(Product pr : products)
					res.add(new Resource(pr));
				Product p = new Product("Enter a name",0, res.toArray(new Resource[]{}), products);
				i.putExtra("NewProduct", p);
				startActivityForResult(i, 0);
			}
		});
		
		final Button cancelbutton = (Button) findViewById(R.id.productslist_cancel);
		cancelbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(Activity.RESULT_CANCELED, null);
			  finish();
			}
		});
		
		final Button savebutton = (Button) findViewById(R.id.productslist_save);
		savebutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra("Products", products);
				setResult(Activity.RESULT_OK, i);
				finish();
			}
		});
		
		final ListView lv = getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//			    Toast.makeText(getApplicationContext(),
//			      "Click ListItem Number " + position, Toast.LENGTH_LONG)
//			      .show();
				Intent i = new Intent(getApplicationContext(), AddproductActivity.class);
				Product p = products.get(position);
				i.putExtra("NewProduct", p);
				startActivityForResult(i, 0);
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
			    Product product = extras.getParcelable("NewProduct");
			    
			    int i = Find(product.id);
			    if( i == -1){
				    products.add(product);
				    for(Product p : products){
				    	p.AddMaterial(product);
				    }
			    }
			    else 
			    	products.set(i, product);
			    
			    adapter.notifyDataSetChanged();
			}
	      }
	      break;
	    } 
	    case (1):{
	    	break;
	      }
	  }
	}
	
	private int Find(int Id){
		for(int i = 0 ; i < products.size(); i++)
		{
			if(products.get(i).id == Id) return i;
		}
		return -1;
	}
}
