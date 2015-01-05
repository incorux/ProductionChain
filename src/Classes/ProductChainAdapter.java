package Classes;

import java.util.ArrayList;

import com.example.productionchain.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProductChainAdapter extends ArrayAdapter<Batch> {
  private final Context context;
  private final ArrayList<Batch> values;

  public ProductChainAdapter(Activity context, ArrayList<Batch> batches) {
    super(context, R.layout.rowlayout, batches);
    this.context = context;
    this.values = batches;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  View rowView = inflater.inflate(R.layout.productchainlist_row, null);
		  // configure view holder
		  TextView textView = (TextView) rowView.findViewById(R.id.chain_efficiency);
		  LinearLayout buyLayout = (LinearLayout) rowView.findViewById(R.id.chain_buy);
		  LinearLayout createLayout = (LinearLayout) rowView.findViewById(R.id.chain_create);
		  LinearLayout sellLayout = (LinearLayout) rowView.findViewById(R.id.chain_sell);

	    // fill data
		textView.setText(values.get(position).Efficiency.toString());
		Batch batch = values.get(position);
		FillList(batch.Create, createLayout);
		FillList(batch.Buy, buyLayout);
		FillList(batch.Sell, sellLayout);
		
		return rowView;
	    }
  private void FillList(ArrayList<Resource> list, LinearLayout view){
	  if(list.size()== 0){
		  LinearLayout parent = ((LinearLayout) view.getParent());
		  ((LinearLayout) parent.getParent()).removeView(parent);
		  return;
	  }
		for(int i = 0 ; i < list.size() ; i++){
			TextView tv = new TextView(context);
			Resource r = list.get(i);
			tv.setText(r.quantity + " x " + r.label);
			view.addView(tv);
		}
		View separator = new View(context);
		separator.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,1));
		separator.setBackgroundColor(Color.GRAY);
		view.addView(separator);
  }
} 
