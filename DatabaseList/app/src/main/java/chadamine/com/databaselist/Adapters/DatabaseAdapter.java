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

    Uri getHistoryUri();

    int getId();

    String getKeyID();

    String[] getKeyIdArray();

    ContentValues getValues();

    ContentValues getHistoryValues();

    // TODO: MAKE NEWCURSOR OR REMOVE
    Cursor getCursor();

    // TODO: THIS SHOULD JUST BE ASSOCIATED WITH KEY_ID
    String getName();

    String getCurrentDate();

    String getPhotoDir();

    // SET LIST ITEMS USING CURSOR FROM LISTCURSORADAPTER
    void setListItemContent(View v, Cursor c);

    // SET CONTENT OF FRAGMENTS
    // TODO: SET WITH CURSOR FOR PAGERADAPTER?
    void setContent(View view);

    int getListItemLayoutId();
    // TODO: ADD GETNEWITEMLAYOUT

}
