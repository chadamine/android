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
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Database.DatabaseSchema.Photos;


/**
 * Created by chadamine on 4/29/2015.
 */
public class Photo implements DatabaseAdapter {

    private String mDirPhoto;
    private String mName;
    private String mPhotoFullName;
    private String mTimestamp;
    private String mType;
    private Context mContext;
    private DatabaseAdapter mDatabaseObject;

    private static final Uri CONTENT_URI = Photos.CONTENT_URI;
    private static final String[] KEY_ID_ARRAY = Photos.KEY_ID_ARRAY;
    private static final String KEY_ID = Photos.KEY_ID;

    private static final String EXT_DIR = Environment.getExternalStorageDirectory().toString();

    public Photo(Context c, DatabaseAdapter databaseObject) {
        mContext = c;
        mDatabaseObject = databaseObject;
        mDirPhoto = EXT_DIR
                + this.getPhotoDir() + databaseObject.getPhotoDir();
    }

    public void setID(Context activity, int id) {
        Cursor cursor =
                activity.getContentResolver()
                        .query(DatabaseSchema.Photos.CONTENT_URI,
                                new String[]{DatabaseSchema.Photos.KEY_ID},
                                null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            // TODO: use id to label picture?
            setID(mContext, cursor.getInt(cursor
                    .getColumnIndexOrThrow(DatabaseSchema.Photos.KEY_ID)));
        }
    }

    public String getType() {
        return mType;
    }

    public void setName(String name) {
        mPhotoFullName =
                name + "_"
                        + mTimestamp
                        + DatabaseSchema.Photos.EXTENSION_PNG;
    }
    public String getNewPhotoFullName() {
        mTimestamp = new SimpleDateFormat(
                DatabaseSchema.Photos.DATE_FORMAT).format(new Date());

        mPhotoFullName =
                mDatabaseObject.getName() + "_"
                        + mTimestamp
                        + DatabaseSchema.Photos.EXTENSION_PNG;
        return mPhotoFullName;
    }

    public Uri getUri() {
        Uri imageUri = Uri.fromFile(new File(getPhotoFolder(),
                getNewPhotoFullName()));

        return imageUri;
    }

    @Override
    public String[] getKeyIdArray() {
        return KEY_ID_ARRAY;
    }

    @Override
    public String getPhotoDir() {
        return Photos.DIR_PHOTOS;
    }

    public String getPhotoFolder() {
        return mDirPhoto;
    }

    public String getCurrentPhotoFullName() {
        return mPhotoFullName;
    }

    public void dbInsertPictures(Context activity) {
        activity.getContentResolver()
                .insert(CONTENT_URI, getValues());
    }

    @Override
    public ContentValues getValues() throws NullPointerException {
        ContentValues values = new ContentValues();
        values.put(Photos.KEY_NAME, getName());
        values.put(Photos.KEY_DIRECTORY, getPhotoDir()
                + mDatabaseObject.getPhotoDir());
        values.put(Photos.KEY_TIMESTAMP, mTimestamp);
        //values.put(Photos.KEY_TYPE, mType);

        return values;
    }

    @Override
    public String getKeyID() {
        return KEY_ID;
    }

    @Override
    public Cursor getCursor() {
        return null;
    }

    @Override
    public String getName() {
        return mDatabaseObject.getName();
    }



    @Override
    public void setListItemContent(View view, Cursor cursor) {

    }

    @Override
    public void setContent(View view) {

    }

    @Override
    public int getListItemLayoutId() {
        return 0;
    }
}
