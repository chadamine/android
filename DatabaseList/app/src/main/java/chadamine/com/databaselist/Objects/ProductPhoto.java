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
import chadamine.com.databaselist.Objects.Product;

/**
 * Created by chadamine on 4/29/2015.
 */
public class ProductPhoto extends Product {

    private String mPhotoName;
    private String mDirPhoto;
    private String mPhotoFullName;
    private String mTimestamp;

    public ProductPhoto(Product product) {
        super(product);

        mDirPhoto = Environment.getExternalStorageDirectory()
                + DatabaseContract.ProductPhotos.DIR_PRODUCT_PHOTOS;
    }

    public void setID(Context activity, int id) {
        Cursor cursor =
                activity.getContentResolver()
                        .query(DatabaseContract.ProductPhotos.CONTENT_URI,
                                new String[]{DatabaseContract.ProductPhotos.KEY_ID},
                                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            // TODO: use id to label picture
            setID(cursor.getInt(cursor
                    .getColumnIndexOrThrow(DatabaseContract.ProductPhotos.KEY_ID)));
        } else {
            super.setID(id);
        }

    }

    public String getNewPhotoFullName() {
        mTimestamp = new SimpleDateFormat(
                DatabaseContract.ProductPhotos.DATE_FORMAT).format(new Date());

        mPhotoFullName =
                this.getName() + "_"
                        + mTimestamp
                        + DatabaseContract.ProductPhotos.EXTENSION_PNG;
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
                .insert(DatabaseContract.ProductPhotos.CONTENT_URI, getValues());

    }

    @Override
    public ContentValues getValues() throws NullPointerException {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ProductPhotos.KEY_NAME, getName());
        values.put(DatabaseContract.ProductPhotos.KEY_DIRECTORY, mDirPhoto);
        values.put(DatabaseContract.ProductPhotos.KEY_TIMESTAMP, mTimestamp);

        return values;
    }
}
