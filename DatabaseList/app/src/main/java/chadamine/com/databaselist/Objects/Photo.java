package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Database.DatabaseContract.Photos;


/**
 * Created by chadamine on 4/29/2015.
 */
public class Photo implements DatabaseAdapter {

    private String mPhotoName;
    private String mDirPhoto;
    private String mPhotoFullName;
    private String mTimestamp;
    private String mType;
    private Context mContext;
    private DatabaseAdapter mDatabaseObject;

    private static final Uri CONTENT_URI = Photos.CONTENT_URI;

    private static final String EXT_DIR = Environment.getExternalStorageDirectory().toString();
/*
    // TODO: REMOVE
    public static final String TYPE_PRODUCT = DatabaseContract.Products.TABLE_NAME;
    public static final String TYPE_PLANT = DatabaseContract.Plants.TABLE_NAME;

*/

    public Photo(Context c, DatabaseAdapter databaseObject) {
        mContext = c;
        //mType = TYPE_PRODUCT;
        mDatabaseObject = databaseObject;

        mDirPhoto = EXT_DIR
                + getPhotoDir();
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
            setID(mContext, cursor.getInt(cursor
                    .getColumnIndexOrThrow(DatabaseContract.Photos.KEY_ID)));
        }
    }

/*    // TODO: REMOVE
    public void setType(String type) {
        mType = type;
    }*/

    public String getType() {
        return mType;
    }

    public String getNewPhotoFullName() {
        mTimestamp = new SimpleDateFormat(
                DatabaseContract.Photos.DATE_FORMAT).format(new Date());

        mPhotoFullName =
                mDatabaseObject.getName() + "_"
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

    @Override
    public void insertValues(Context context, Uri uri) {

    }

    @Override
    public String[] getKeyIdArray() {
        return new String[0];
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

    @Override
    public String getKeyID() {
        return null;
    }

    @Override
    public Cursor getCursor() {
        return null;
    }

    public void setName(String name) {
        mPhotoName = name;
    }
    @Override
    public String getName() {
        return mPhotoFullName;
    }

    @Override
    public String getPhotoDir() {
        return Photos.DIR_PHOTOS;
    }

    @Override
    public void setListItemContent(View view, Cursor cursor) {

    }

    @Override
    public int getListItemLayoutId() {
        return 0;
    }
}
