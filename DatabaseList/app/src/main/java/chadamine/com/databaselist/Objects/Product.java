package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Fragments.ProductPicture;

/**
 * Created by chadamine on 4/29/2015.
 */
public class Product {

    //TODO: Make ProductImage subclass

    private int mProductID;
    private String mProductName;
    private ProductPicture mProductPicture;
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
