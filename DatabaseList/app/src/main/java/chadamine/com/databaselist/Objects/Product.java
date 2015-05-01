package chadamine.com.databaselist.Objects;

import android.content.ContentValues;

import chadamine.com.databaselist.Database.DatabaseContract;

/**
 * Created by chadamine on 4/29/2015.
 */
public class Product {

    private int mID;
    private String mName;
    private Photo mPhoto;
    private Double mSize;

    //private Manufacturer mManufacturer;
    //private Supplier mSupplier;

    public Product() {}

    public Product(Product product) {
        mID = product.getID();
        mName = product.getName();
    }

    public String getName() throws NullPointerException {
        return mName;
    }

    public int getID() {
        return mID;
    }

    public void setID(int id) {
        mID = id;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean hasName() {

        if(mName.length() > 0)
            return true;
        else
            return false;
    }

    public ContentValues getValues() throws NullPointerException {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Products.KEY_NAME, getName());

        return values;
    }


    public Double getSize() {
        return mSize;
    }

    public void setmSize(Double size) {
        mSize = size;
    }

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setmPhoto(Photo photo) {
        mPhoto = photo;
    }
}
