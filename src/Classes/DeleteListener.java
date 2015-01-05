package Classes;

import java.util.ArrayList;

import android.view.View;
import android.view.View.OnClickListener;

public class DeleteListener implements OnClickListener
{
	int position;
	ProductListAdapter adapter;
	ArrayList<Product> products;
	public DeleteListener(int aVariable, ProductListAdapter myArrayAdapter, ArrayList<Product> res) {
		position = aVariable;
		adapter = myArrayAdapter;
		products=  res;
		}
	
	@Override
	public void onClick(View v)
	{
			products.remove(position);
			//adapter.context.products.remove(position);
			adapter.notifyDataSetChanged();
	}
};
