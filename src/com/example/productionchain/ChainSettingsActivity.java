package com.example.productionchain;

import java.util.ArrayList;

import Classes.Product;
import Classes.ProductListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChainSettingsActivity extends Activity {
	
	private ArrayList<Product> products;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chainsettings);
		
		Intent i = getIntent();
		if(i != null){
			products = i.getParcelableArrayListExtra("Products");	
		}
		SetEvents();
	}
	
	private void SetEvents(){
		final Button productschain = (Button) findViewById(R.id.productschain);
		productschain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),ProductionChainActivity.class);
				
			    EditText moneyfield = (EditText) (findViewById(R.id.Settings_Money));
				EditText laborfield = (EditText) (findViewById(R.id.Settings_Labor));
				
				Double money = Double.parseDouble(moneyfield.getText().toString());
				int labor = Integer.parseInt(laborfield.getText().toString());
				
				i.putExtra("Products", products);
				i.putExtra("Labor", labor);
				i.putExtra("Money",money);
				
				startActivityForResult(i, 0);
			}
		});
		
		final Button cancel = (Button) findViewById(R.id.settings_cancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  setResult(Activity.RESULT_CANCELED, null);
				  finish();
			}
		});
	}
}
