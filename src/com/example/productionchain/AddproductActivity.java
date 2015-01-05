package com.example.productionchain;

import Classes.MaterialsListAdapter;
import Classes.Product;
import Classes.Resource;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddproductActivity extends ListActivity {
	
	Product product = null;
	
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.activity_addproduct);
    SetEvents();
    
    Intent i = getIntent();
	if (i!= null) {
		Product p = i.getParcelableExtra("NewProduct");
		this.product = p;
		
	    MaterialsListAdapter adapter = new MaterialsListAdapter(this, p.materials.toArray(new Resource[]{}));
	    setListAdapter(adapter);
	    
	    EditText costfield = (EditText) (findViewById(R.id.productcost));
		EditText laborfield = (EditText) (findViewById(R.id.productlabor));
		EditText namefield = (EditText) (findViewById(R.id.productnamefield));
		EditText valuefield = (EditText) (findViewById(R.id.productvalue));
		
		costfield.setText(Double.toString(product.cost));
		laborfield.setText(Integer.toString(product.laborCost));
		namefield.setText(product.name);
		valuefield.setText(Double.toString(product.value));
	}
  }
  
  private void SetEvents(){
	  
		final Button savebutton = (Button) findViewById(R.id.addproductsave);
		savebutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent resultIntent = new Intent();
				  
				  EditText namefield = (EditText) (findViewById(R.id.productnamefield));
				  EditText valuefield = (EditText) (findViewById(R.id.productvalue));
				  EditText costfield = (EditText) (findViewById(R.id.productcost));
				  EditText laborfield = (EditText) (findViewById(R.id.productlabor));
				  
				  if(valuefield.getText().toString().equals("")){
					  valuefield.setText("0");
					  Toast.makeText(getApplicationContext(), "Product's value cannot be empty", 
					   Toast.LENGTH_LONG).show();
				  }
				  
				  String Name = namefield.getText().toString();
				  Double Value= Double.parseDouble(valuefield.getText().toString());
				  Double Cost= Double.parseDouble(costfield.getText().toString());
				  int Labor = Integer.parseInt(laborfield.getText().toString());
				  
				  product.name = Name;
				  product.value = Value;
				  product.cost = Cost;
				  product.laborCost = Labor;
				  resultIntent.putExtra("NewProduct", product);
				  setResult(Activity.RESULT_OK, resultIntent);
				  finish();
			}
		});
		
		final Button cancelbutton = (Button) findViewById(R.id.addproductcancel);
		cancelbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  setResult(Activity.RESULT_CANCELED, null);
				  finish();
			}
		});
		
		final TextView nameView= (TextView) findViewById(R.id.productnamefield);
		nameView.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				String s = ((TextView) v).getText().toString();
				if(s.equals("Enter a name"))
					((TextView) v).setText("");
				}
				else{
					if(((TextView) v).getText().toString().equals(""))
						((TextView) v).setText("Enter a name");
				}
			}
		});
		
		final TextView laborView= (TextView) findViewById(R.id.productlabor);
		laborView.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				int s = Integer.parseInt(((TextView) v).getText().toString());
				if(s == 1)
					((TextView) v).setText("");
				}
				else{
					if(((TextView) v).getText().toString().equals(""))
						((TextView) v).setText("1");
				}
			}
		});
		
		final TextView costView= (TextView) findViewById(R.id.productcost);
		costView.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				double s = Double.parseDouble(((TextView) v).getText().toString());
				if(s == 1.0)
					((TextView) v).setText("");
				}
				else{
					if(((TextView) v).getText().toString().equals(""))
						((TextView) v).setText("1.0");
				}
			}
		});
		
		final TextView valueView= (TextView) findViewById(R.id.productvalue);
		valueView.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus){
				double s = Double.parseDouble(((TextView) v).getText().toString());
				if(s == 1.0)
					((TextView) v).setText("");
				}
				else{
					if(((TextView) v).getText().toString().equals(""))
						((TextView) v).setText("1.0");
				}
			}
		});
  }
} 