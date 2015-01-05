package Classes;

import java.util.ArrayList;

import com.example.productionchain.ProductlistActivity;
import com.example.productionchain.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductListAdapter extends ArrayAdapter<Product> {
  public ProductlistActivity context;
  public ArrayList<Product> values;

  public ProductListAdapter(Context context, ArrayList<Product> products) {
    super(context, R.layout.rowlayout,R.id.label, products);
    this.context = (ProductlistActivity) context;
    this.values = products;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.productslist_row, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.productnamelabel);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.productdelete);
    
    textView.setText(values.get(position).name);
    
    imageView.setOnClickListener(new DeleteListener(position,this,values));
    
    return rowView;
  }
} 