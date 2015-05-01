package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import chadamine.com.databaselist.Database.DatabaseContract;

/**
 * Created by chadamine on 4/29/2015.
 */
public class Photo extends Product {

    private String mPhotoName;
    private String mDirPhoto;
    private String mPhotoFullName;
    private String mTimestamp;
    private String mType;

    public static final String TYPE_PRODUCT = "product";

    public Photo(Product product) {
        super(product);
        mType = TYPE_PRODUCT;

        mDirPhoto = Environment.getExternalStorageDirectory()
                + DatabaseContract.Photos.DIR_PRODUCT_PHOTOS;
    }

    public Photo(Plant plant) {

    }

    public void setID(Context activity, int id) {
        Cursor cursor =
                activity.getContentResolver()
                        .query(DatabaseContract.Photos.CONTENT_URI,
                                new String[]{DatabaseContract.Photos.KEY_ID},
                                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            // TODO: use id to label picture?
            setID(cursor.getInt(cursor
                    .getColumnIndexOrThrow(DatabaseContract.Photos.KEY_ID)));
        } else {
            super.setID(id);
        }

    }

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public String getNewPhotoFullName() {
        mTimestamp = new SimpleDateFormat(
                DatabaseContract.Photos.DATE_FORMAT).format(new Date());

        mPhotoFullName =
                this.getName() + "_"
                        + mTimestamp
                        + DatabaseContract.Photos.EXTENSION_PNG;
        return mPhotoFullName;
    }

    public void setPhotoName(String name) {
        mPhotoName = name;
    }
    public String getPhotoName() {
       return mPhotoName;
    }

    public Uri getUri() {
        Uri imageUri = Uri.fromFile(new File(getPhotoFolder(), getNewPhotoFullName()));

        return imageUri;
    }

    public String getPhotoFolder() {
        return mDirPhoto;
    }

    public String getCurrentPhotoName() {
        return mPhotoName;
    }

    public String getCurrentPhotoFullName() {
        return mPhotoFullName;
    }

    public void dbInsertPictures(Context activity) {
        activity.getContentResolver()
                .insert(DatabaseContract.Photos.CONTENT_URI, getValues());
    }

    @Override
    public ContentValues getValues() throws NullPointerException {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.Photos.KEY_NAME, getName());
        values.put(DatabaseContract.Photos.KEY_DIRECTORY, mDirPhoto);
        values.put(DatabaseContract.Photos.KEY_TIMESTAMP, mTimestamp);
        values.put(DatabaseContract.Photos.KEY_TYPE, mType);

        return values;
    }
}
