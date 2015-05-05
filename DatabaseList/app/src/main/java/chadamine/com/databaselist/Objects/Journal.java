package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Database.DatabaseContract.Journals;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 5/4/2015.
 */
public class Journal implements DatabaseAdapter {

    //private static final String[] KEY_ARRAY = Journals.KEY_ARRAY;
    private Cursor mCursor;
    private Context mContext;

    private Uri CONTENT_URI = Journals.CONTENT_URI;
    private static final String KEY_ID = Journals.KEY_ID;
    private static final String KEY_NAME = Journals.KEY_NAME;
    private static final String[] KEY_ID_ARRAY = Journals.KEY_ID_ARRAY;

    private String mName;

    public Journal(Context c) {
        mContext = c;
        /*mCursor = mContext.getContentResolver()
                .query(getUri(), getKeyIdArray(), null, null, null);*/
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
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, getName());

        return values;
    }

    @Override
    public String getKeyID() {
        return KEY_ID;
    }

    @Override
    public Cursor getCursor() {
        return mCursor;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getPhotoDir() {
        return Journals.DIR_PHOTOS;
    }

    private HashMap<String, View> mViews;

    public void setViews(HashMap<String, View> views) {
        mViews = views;
    }
    // TODO: MAKE THIS WORK
    public void setContent(Cursor c) {

        for(Map.Entry<String, View> view : mViews.entrySet()) {
            if(view.getKey() == "list_item_journal")
                return;
                /*for(View field : view.getValue().getResources())
                    if(field.getClass().toString() == EditText.class.toString())
                        ((EditText) field).setText(c.getString(c.getColumnIndexOrThrow(KEY_NAME)));*/
        }

    }

    @Override
    public void setListItemContent(View v, Cursor c) {
        ((TextView) v.findViewById(R.id.textview_journallist_name))
                .setText(c.getString(c.getColumnIndexOrThrow(KEY_NAME)));
    }

    public int getListItemLayoutId() {
        return mContext.getResources()
                .getIdentifier("list_item_journal", "layout", mContext.getPackageName());
    }
}
