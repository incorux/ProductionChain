package Classes;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;

public class CustomListener implements OnClickListener
{
	boolean upOrDown;
	int position;
	MyArrayAdapter adapter;
	Resource[] resources;
	public CustomListener(int aVariable, MyArrayAdapter myArrayAdapter,Resource[] res, boolean UpOrDown) {
		position = aVariable;
		adapter = myArrayAdapter;
		resources=  res;
		upOrDown = UpOrDown;
		}
	
	@Override
	public void onClick(View v)
	{
		if(upOrDown)
		{
			resources[position].quantity++;
			if(resources[position].quantity > 99) resources[position].quantity = 99;
			else adapter.notifyDataSetChanged();
		}else{
			resources[position].quantity--;
			if(resources[position].quantity < 0) resources[position].quantity = 0;
			else adapter.notifyDataSetChanged();
		}
	}

};
