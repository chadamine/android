package chadamine.com.databaselist.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

import java.util.HashMap;

/**
 * Created by chadamine on 5/4/2015.
 */
public interface DatabaseAdapter {

    Uri getUri();

    String[] getKeyIdArray();

    ContentValues getValues();

    String getKeyID();

    Cursor getCursor();

    String getName();

    String getPhotoDir();

    void setListItemContent(View view, Cursor cursor);

    void setContent(Cursor cursor);

    int getListItemLayoutId();

}
