package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable{
	public int id;
	public String name;
	public ArrayList<Resource> materials;
	public double cost;
	public double value;
	public int laborCost;
	public Product(String name,double value, Resource[] mats, ArrayList<Product> products){
		id = new Random().nextInt();
		while(Contains(products,id))
			id = new Random().nextInt();
		materials = new ArrayList<>(Arrays.asList(mats));
		this.value = value;
		this.name = name;
		laborCost = 1;
		cost = 1;
	}
	
	public Product(String name, double value,double cost, int laborCost, Resource[] mats, ArrayList<Product> products){
		id = new Random().nextInt();
		while(Contains(products,id))
			id = new Random().nextInt();
		materials = new ArrayList<>(Arrays.asList(mats));
		this.value = value;
		this.name = name;
		this.laborCost = laborCost;
		this.cost = cost;
	}
	
	public Product(Parcel in){
		id = in.readInt();
		name = in.readString();
		value = in.readDouble();
		cost = in.readDouble();
		laborCost = in.readInt();
		materials = new ArrayList<Resource>();
		in.readList(materials, Resource.class.getClassLoader());
	}
	
	public void AddMaterial(Product p){
		if(! (id == p.id))
			materials.add(new Resource(p));
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeDouble(value);
		dest.writeDouble(cost);
		dest.writeInt(laborCost);
		dest.writeList(materials);
	}
	
	public static final Parcelable.Creator<Product> CREATOR= new Parcelable.Creator<Product>() {
		 
		@Override
		public Product createFromParcel(Parcel source) {
		return new Product(source);
		}
		 
		@Override
		public Product[] newArray(int size) {
		// TODO Auto-generated method stub
		return new Product[size];
		}
		};
	
	public String toString(){
		return name;
	}
	private boolean Contains(ArrayList<Product> products, int id){
		for(Product p : products)
			if(p.id == id) return true;
		return false;
	}
}
