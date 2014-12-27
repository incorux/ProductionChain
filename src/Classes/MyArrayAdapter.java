package Classes;

import com.example.productionchain.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyArrayAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final Resource[] values;

  public MyArrayAdapter(Context context, Resource[] products, String[] ar) {
    super(context, R.layout.rowlayout,R.id.label, ar);
    this.context = context;
    this.values = products;
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
    
    textView.setText(values[position].label);
    valueView.setText("0");
    return rowView;
  }
} 