package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable{
	public int Id;
	public String Name;
	public ArrayList<Resource> materials;
	public double Value;
	public Product(String name,double value, Resource[] mats){
		Id = new Random().nextInt();
		materials = new ArrayList<>(Arrays.asList(mats));
		Value = value;
		Name = name;
	}
	
	public Product(int id,String name,double value, Resource[] mats){
		Id = id;
		materials = new ArrayList<>(Arrays.asList(mats));
		Value = value;
		Name = name;
	}
	
	public Product(Parcel in){
		Id = in.readInt();
		Name = in.readString();
		Value = in.readDouble();
		materials = new ArrayList<Resource>();
		in.readList(materials, Resource.class.getClassLoader());
	}
	
	public void AddMaterial(Product p){
		materials.add(new Resource(p));
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(Id);
		dest.writeString(Name);
		dest.writeDouble(Value);
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
		return Name;
	}
		 
}
