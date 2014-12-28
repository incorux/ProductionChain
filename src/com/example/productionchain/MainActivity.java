package com.example.productionchain;

import java.util.ArrayList;
import java.util.List;

import Classes.Product;
import Classes.Resource;
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
		
		products.add(new Product("First",0.0,new Resource[]{}));
		products.add(new Product("Second",0.0,new Resource[]{}));
		products.get(0).AddMaterial(products.get(1));
		products.get(1).AddMaterial(products.get(0));
		

		SetEvents();
	} 
	
	private void SetEvents(){
		final Button addproduct = (Button) findViewById(R.id.AddProductButton);
		addproduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), AddproductActivity.class);
				ArrayList<Resource> res = new ArrayList<>();
				for(Product pr : products)
					res.add(new Resource(pr));
				Product p = new Product("Enter a name",0, res.toArray(new Resource[]{}));
				i.putExtra("NewProduct", p);
				startActivityForResult(i, 0);
			}
		});
		
		final Button exit = (Button) findViewById(R.id.exitbutton);
		exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
	            System.exit(0);
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
			    products.add(product);
			    for(Product p : products){
			    	p.AddMaterial(product);
			    }
			}
	      }
	      break;
	    } 
	  }
	}
}
