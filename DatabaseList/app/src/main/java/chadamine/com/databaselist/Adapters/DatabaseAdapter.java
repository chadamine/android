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

    int getId();

    String getKeyID();

    String[] getKeyIdArray();

    ContentValues getValues();

    // TODO: MAKE NEWCURSOR OR REMOVE
    Cursor getCursor();

    // TODO: THIS SHOULD JUST BE ASSOCIATED WITH KEY_ID
    String getName();

    String getPhotoDir();

    // SET LIST ITEMS USING CURSOR FROM LISTCURSORADAPTER
    void setListItemContent(View view, Cursor cursor);

    // SET CONTENT OF FRAGMENTS
    // TODO: SET WITH CURSOR FOR PAGERADAPTER?
    void setContent(View view);

    int getListItemLayoutId();

    // TODO: ADD GETNEWITEMLAYOUT

}
