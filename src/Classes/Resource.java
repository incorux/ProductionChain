package Classes;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Resource implements Parcelable {
	public String label;
	public int quantity;
	public int Id;
	
	public Resource(Product p){
		label = p.Name;
		quantity = 0;
		Id = p.Id;
	}
	
	public Resource(Parcel in){
		Id = in.readInt();
		label= in.readString();
		quantity= in.readInt();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(Id);
		dest.writeString(label);
		dest.writeDouble(quantity);
	}
	
	public static final Parcelable.Creator<Resource> CREATOR= new Parcelable.Creator<Resource>() {
		 
		@Override
		public Resource createFromParcel(Parcel source) {
		return new Resource(source);
		}
		 
		@Override
		public Resource[] newArray(int size) {
		// TODO Auto-generated method stub
		return new Resource[size];
		}
		};
	
	public String toString(){
		return label;
	}
	
}
