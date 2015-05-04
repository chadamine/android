package chadamine.com.databaselist.Objects;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;

/**
 * Created by chadamine on 5/4/2015.
 */
public interface DatabaseObject {

    Uri getUri();

    void insertValues(Context context, Uri uri);

    String[] getKeyArray();

    ContentValues getValues();

    String getKeyID();

    Cursor getCursor();

    void setListItemContent(View view);

    //android.R.layout getListItemLayoutId();

    //View getListItemLayout();
}
