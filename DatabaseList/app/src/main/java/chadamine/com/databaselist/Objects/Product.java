package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/29/2015.
 */
public class Product implements DatabaseObject{

    private int mID;
    private String mName;
    private Photo mPhoto;
    private Double mSize;
    private Uri mUri;
    private String[] mKeyArray;
    private Cursor mCursor;
    private String mManufacturer;
    private String mType;
    private String mProductLine;
    private String mKeyID;
    private String mKeyName;
    private String mKeyManufacturer;
    private String mKeyLine;
    private String mKeyType;

    //private Manufacturer mManufacturer;
    //private Supplier mSupplier;

    public Product() {}

    public Product(Product product) {
        mID = product.getID();
        mName = product.getName();
        mUri = DatabaseContract.Products.CONTENT_URI;

        mKeyArray = DatabaseContract.Products.KEY_ID_ARRAY;
        mKeyID = mKeyArray[0];
        mKeyName = mKeyArray[1];
        mKeyManufacturer = mKeyArray[2];
        mKeyLine = mKeyArray[3];
        mKeyType = mKeyArray[4];
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

    @Override
    public Uri getUri() {
        return null;
    }

    @Override
    public void insertValues(Context context, Uri uri) {

    }

    @Override
    public String[] getKeyArray() {
        return new String[0];
    }

    public ContentValues getValues() throws NullPointerException {

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Products.KEY_NAME, getName());

        return values;
    }

    @Override
    public String getKeyID() {
        return null;
    }

    @Override
    public Cursor getCursor() {
        return null;
    }

    @Override
    public void setListItemContent(View view, Cursor cursor) {

    }

    public void setListItemContent(View view) {

    }

    public int getListItemLayoutId() {
        return 0;
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

    public void setProductFields(View view, Cursor cursor, int position) {
        mCursor = cursor;
        loadFromDatabase(position);

        ((TextView) view.findViewById(R.id.textview_productlist_name)).setText(getName());
            //mCursor.getString(mCursor.getColumnIndex(DatabaseContract.Products.KEY_NAME)));
        ((TextView) view.findViewById(R.id.textview_productlist_manufacturer)).setText(getManufacturer());
                //mCursor.getString(mCursor.getColumnIndex(DatabaseContract.Products.KEY_MANUFACTURER)));
        ((TextView) view.findViewById(R.id.textview_productlist_type)).setText(getType());
                //mCursor.getString(mCursor.getColumnIndex(DatabaseContract.Products.KEY_TYPE)));
        ((TextView) view.findViewById(R.id.textview_productlist_line)).setText(getProductLine());
                //mCursor.getString(mCursor.getColumnIndex(DatabaseContract.Products.KEY_LINE)));
    }

    public void loadFromDatabase(int position) {

        if (mCursor != null) {
            mCursor.moveToPosition(position);

            mID = mCursor.getInt(mCursor.getColumnIndexOrThrow(DatabaseContract.Products.KEY_ID));
            mName = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.Products.KEY_NAME));
            mManufacturer = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.Products.KEY_MANUFACTURER));
            mType = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseContract.Products.KEY_TYPE));
            // TODO: mPhoto from file using location, name, and timestamp fields
        }
    }

    public void setManufacturer(String manufacturer) {
        mManufacturer = manufacturer;
    }

    public String getManufacturer() {
        return mManufacturer;
    }

    public void setType(String type) {
        mType = type;
    }
    public String getType() {
        return mType;
    }

    public void setProductLine(String line) {
        mProductLine = line;
    }
    public String getProductLine() {
        return mProductLine;
    }
}
