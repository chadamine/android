package chadamine.com.databaselist.History;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Adapters.HistoryDatabaseAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema.Histories;

/**
 * Created by chadamine on 8/13/2015.
 */
public class History implements HistoryDatabaseAdapter {

    private Context mContext;
    private DatabaseAdapter mDBObject;
    private final String[] KEY_ID_ARRAY = Histories.KEY_ID_ARRAY;
    private final Uri URI = Histories.CONTENT_URI;
    private Date mDate;
    private String mCategory;
    private String mType;
    private String mAction;

    public History (Context c, DatabaseAdapter dbObject) {
        mContext = c;
        mDBObject = dbObject;
    }

    @Override
    public Uri getUri() {
        return URI;
    }

    @Override
    public int getId() {
        Cursor c = getNewCursor(KEY_ID_ARRAY, null, null, null);
        c.moveToLast();
        int id = c.getInt(c.getColumnIndex("_id"));
        return id;
    }

    @Override
    public String getKeyID() {
        return Histories.KEY_ID;
    }

    @Override
    public String[] getKeyIdArray() {
        return KEY_ID_ARRAY;
    }

    @Override
    public ContentValues getValues() {

        ContentValues values = new ContentValues();
        values.put(Histories.KEY_DATE, getDateFormatted());
        values.put(Histories.KEY_CATEGORY, getCategory());
        values.put(Histories.KEY_ACTION, getAction());
        values.put(Histories.KEY_TYPE, getType());

        return values;
    }

    @Override
    public Cursor getCursor() { return null; }

    public Cursor getNewCursor(@Nullable String[] idArray,
                               @Nullable String selection,
                               @Nullable String[] selectionArgs,
                               @Nullable String sortOrder) {
        String[] idA;

        if(idArray == null)
            idA = mDBObject.getKeyIdArray();
        else
            idA = idArray;

        Cursor cursor = mContext.getContentResolver().query(mDBObject.getUri(), idA, selection, selectionArgs, sortOrder);
        cursor.moveToFirst();

        return cursor;
    }

    @Override
    public Date getDate() {
        return new Date();
    }

    @Override
    public String getDateFormatted() {
        return new SimpleDateFormat("yyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    @Override
    public void setListItemContent(View v, Cursor c) {

    }

    @Override
    public int getListItemLayoutId() {
        return 0;
    }

    @Override
    public String getCategory() {
        return mCategory;
    }

    @Override
    public String getAction() {
        return mAction;
    }

    @Override
    public String getType() {
        return mType;
    }
}
