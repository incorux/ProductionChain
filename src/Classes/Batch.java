package Classes;

import java.util.ArrayList;

public class Batch {
	public ArrayList<Resource> Create;
	public ArrayList<Resource> Buy;
	public ArrayList<Resource> Sell;
	public Double Efficiency;
	
	public Batch(){
		Create = new ArrayList<>();
		Buy = new ArrayList<>();
		Sell = new ArrayList<>();
	}
	
	public void AddToSell(Resource r, int times){
		FindAndAdd(r,Sell, times);
	}
	
	public void AddToCreate(Resource r, int times){
		FindAndAdd(r,Create, times);
	}
	
	public void AddToBuy(Resource r, int times){
		FindAndAdd(r,Buy, times);
	}
	
	private void FindAndAdd(Resource r, ArrayList<Resource> list, int times){
		int index = -1;
		for(int i = 0; i < list.size(); i++){
			if(r.Id == list.get(i).Id)
				index = i;
		}
		if(index > -1)
			list.get(index).quantity = list.get(index).quantity + times;
		else {
			list.add(r);
			list.get(list.size()-1).quantity = times;
		}
	}
}
