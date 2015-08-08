package chadamine.com.databaselist.Cultivation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema.Substrates;


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
        try {
            mCursor = mContext.getContentResolver().query(CONTENT_URI, KEY_ID_ARRAY, null, null, null);
        } catch (NullPointerException e) {
            mCursor = null;
        }
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
    public int getId() {
        return 0;
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
    public ContentValues getHistoryValues() {
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
    public String getCurrentDate() {
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
    public void setContent(View view) {

    }

    @Override
    public int getListItemLayoutId() {
        return 0;
    }
}
