package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Database.DatabaseContract.Substrates;


/**
 * Created by chadamine on 4/30/2015.
 */
public class Substrate implements DatabaseAdapter {

    private static final Uri CONTENT_URI = Substrates.CONTENT_URI;
    private static final String[] KEY_ID_ARRAY = Substrates.KEY_ID_ARRAY;

    private Context mContext;
    private Cursor mCursor;


    public Substrate(Context c) {
        mContext = c;
        mCursor = mContext.getContentResolver().query(CONTENT_URI, KEY_ID_ARRAY, null, null, null);
    }

    @Override
    public Uri getUri() {
        return CONTENT_URI;
    }

    @Override
    public String[] getKeyIdArray() {
        return KEY_ID_ARRAY;
    }

    @Override
    public ContentValues getValues() {
        return null;
    }

    @Override
    public String getKeyID() {
        return null;
    }

    @Override
    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPhotoDir() {
        return null;
    }

    @Override
    public void setListItemContent(View view, Cursor cursor) {

    }

    @Override
    public void setContent(Cursor cursor) {

    }

    @Override
    public int getListItemLayoutId() {
        return 0;
    }
}
