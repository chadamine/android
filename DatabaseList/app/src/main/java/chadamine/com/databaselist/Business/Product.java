package chadamine.com.databaselist.Business;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Database.DatabaseSchema.Products;

import chadamine.com.databaselist.Photos.Photo;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/29/2015.
 */
public class Product implements DatabaseAdapter {

    private int mId;
    private String mName;
    private Photo mPhoto;
    private Double mSize;
    private static final Uri CONTENT_URI = Products.CONTENT_URI;
    private static final String[] KEY_ID_ARRAY = Products.KEY_ID_ARRAY;
    private Cursor mCursor;
    private String mManufacturer;
    private String mType;
    private String mProductLine;

    private Context mContext;

    private static final String KEY_ID = KEY_ID_ARRAY[0];
    private static final String KEY_NAME = KEY_ID_ARRAY[1];
    private static final String KEY_MANUFACTURER = KEY_ID_ARRAY[2];
    private static final String KEY_LINE = KEY_ID_ARRAY[3];
    private static final String KEY_TYPE = KEY_ID_ARRAY[4];

    //private Manufacturer mManufacturer;
    //private Supplier mSupplier;

    public Product(Context c) {
        mContext = c;
        mCursor = mContext.getContentResolver()
                .query(getUri(), getKeyIdArray(), null, null, null);
    }

    public int getId() {
        return mId;
    }

    public String getName() throws NullPointerException {
        return mName;
    }

    @Override
    public String getCurrentDate() {
        return null;
    }

    @Override
    public String getPhotoDir() {
        return Products.DIR_PHOTOS;
    }

    public int getID() {
        return mId;
    }

    public void setID(int id) {
        mId = id;
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
        return CONTENT_URI;
    }

    @Override
    public Uri getHistoryUri() {
        return null;
    }

    @Override
    public String[] getKeyIdArray() {
        return KEY_ID_ARRAY;
    }

    public ContentValues getValues() throws NullPointerException {

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, getName());

        return values;
    }

    public int getListItemLayoutId() {
        return mContext.getResources()
                .getIdentifier("list_item_product", "layout", mContext.getPackageName());
    }

    @Override
    public void setListItemContent(View view, Cursor c) {
        String name = c.getString(c.getColumnIndexOrThrow(KEY_NAME));

        if (!name.isEmpty())
            ((TextView) view.findViewById(R.id.textview_product_name)).setText(name);
    }

    @Override
    public void setContent(View view) {

    }

    public void setProductFields(View view, Cursor cursor, int position) {
        mCursor = cursor;
        loadFromDatabase(position);

        ((TextView) view.findViewById(R.id.textview_product_name)).setText(getName());
        ((TextView) view.findViewById(R.id.textview_product_manufacturer)).setText(getManufacturer());
        ((TextView) view.findViewById(R.id.textview_product_type)).setText(getType());
        ((TextView) view.findViewById(R.id.textview_product_line)).setText(getProductLine());
    }

    public void loadFromDatabase(int position) {

        if (mCursor != null) {
            mCursor.moveToPosition(position);

            mId = mCursor.getInt(mCursor.getColumnIndexOrThrow(DatabaseSchema.Products.KEY_ID));
            mName = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseSchema.Products.KEY_NAME));
            mManufacturer = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseSchema.Products.KEY_MANUFACTURER));
            mType = mCursor.getString(mCursor.getColumnIndexOrThrow(DatabaseSchema.Products.KEY_TYPE));
            // TODO: mPhoto from file using location, name, and timestamp fields
        }
    }
    @Override
    public String getKeyID() {
        return KEY_ID;
    }

    @Override
    public Cursor getCursor() {
        return mCursor;
    }

    public void setListItemContent(View view) {

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
