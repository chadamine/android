package chadamine.com.databaselist.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

/**
 * Created by chadamine on 5/4/2015.
 */
public interface DatabaseAdapter {

    Uri getUri();

    void insertValues(Context context, Uri uri);

    String[] getKeyIdArray();

    ContentValues getValues();

    String getKeyID();

    Cursor getCursor();

    String getName();

    String getPhotoDir();

    void setListItemContent(View view, Cursor cursor);

    int getListItemLayoutId();

}
