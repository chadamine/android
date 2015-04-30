package chadamine.com.databaselist.Objects;

import android.content.ContentValues;

import chadamine.com.databaselist.Database.DatabaseContract;

/**
 * Created by chadamine on 4/29/2015.
 */
public class Product {

    private int mProductID;
    private String mProductName;
    private ProductPhoto mProductPhoto;
    //private Manufacturer mManufacturer;
    //private Supplier mSupplier;

    public Product() {}

    public Product(Product product) {
        mProductID = product.getID();
        mProductName = product.getName();
    }

    public String getName() throws NullPointerException {
        return mProductName;
    }

    public int getID() {
        return mProductID;
    }

    public void setID(int id) {
        mProductID = id;
    }

    public void setName(String name) {
        mProductName = name;
    }

    public boolean hasName() {

        if(mProductName.length() > 0)
            return true;
        else
            return false;
    }

    public ContentValues getValues() throws NullPointerException {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Products.KEY_NAME, getName());

        return values;
    }
}
