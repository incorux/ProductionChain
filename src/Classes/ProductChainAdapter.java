package Classes;

import java.util.ArrayList;

import com.example.productionchain.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductChainAdapter extends ArrayAdapter<Batch> {
  private final Context context;
  private final ArrayList<Batch> values;

  public ProductChainAdapter(Activity context, ArrayList<Batch> batches) {
    super(context, R.layout.rowlayout,R.id.label, batches);
    this.context = context;
    this.values = batches;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		TextView valueView = (TextView) rowView.findViewById(R.id.number);
		
		ImageView upView = (ImageView) rowView.findViewById(R.id.up);
		ImageView downView = (ImageView) rowView.findViewById(R.id.down);
		return rowView;
	    }
} 
