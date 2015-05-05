package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseContract.Journals;

/**
 * Created by chadamine on 5/4/2015.
 */
public class Journal implements DatabaseAdapter {

    private Uri mUri;
    //private static final String[] KEY_ARRAY = Journals.KEY_ARRAY;
    private static final String[] KEY_ID_ARRAY = Journals.KEY_ID_ARRAY;
    private ContentValues mContentValues;
    private static final String KEY_ID = Journals.KEY_ID;
    private Cursor mCursor;

    @Override
    public Uri getUri() {
        return mUri;
    }

    @Override
    public void insertValues(Context context, Uri uri) {

    }

    @Override
    public String[] getKeyIdArray() {
        return KEY_ID_ARRAY;
    }

    @Override
    public ContentValues getValues() {
        return mContentValues;
    }

    @Override
    public String getKeyID() {
        return KEY_ID;
    }

    @Override
    public Cursor getCursor() {
        return mCursor;
    }

    @Override
    public void setListItemContent(View view, Cursor cursor) {

    }

    public void setListItemContent(View view) {

    }

    public int getListItemLayoutId() {
        return 0;
    }
}
