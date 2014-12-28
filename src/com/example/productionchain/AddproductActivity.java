package com.example.productionchain;

import Classes.MyArrayAdapter;
import Classes.Product;
import Classes.Resource;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
		String[] arr = new String[p.materials.size()];
		int it = 0;
		for(Resource r : p.materials)
		{
			arr[it] = r.label;
			it++;
		}
	    MyArrayAdapter adapter = new MyArrayAdapter(this, p.materials.toArray(new Resource[]{}),arr);
	    setListAdapter(adapter);
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
				  
				  if(valuefield.getText().toString().equals(""))
					  valuefield.setText("0");
				  
				  String Name = namefield.getText().toString();
				  Double Value= Double.parseDouble(valuefield.getText().toString());
				  
				  product.Name = Name;
				  product.Value = Value;
				  resultIntent.putExtra("NewProduct", product);
				  setResult(Activity.RESULT_OK, resultIntent);
				  finish();
			}
		});
		
		final Button cancelbutton = (Button) findViewById(R.id.cancelbutton);
		cancelbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				  setResult(Activity.RESULT_CANCELED, null);
				  finish();
			}
		});
  }
} 