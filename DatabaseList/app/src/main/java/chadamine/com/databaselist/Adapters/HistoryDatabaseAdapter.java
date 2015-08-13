package chadamine.com.databaselist.Adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.Date;

/**
 * Created by chadamine on 8/13/2015.
 */
public interface HistoryDatabaseAdapter {

    Uri getUri();

    int getId();

    String getKeyID();

    String[] getKeyIdArray();

    ContentValues getValues();

    Cursor getCursor();

    Cursor getNewCursor(@Nullable String[] idArray, @Nullable String selection,
                               @Nullable String[] selectionArgs, @Nullable String sortOrder);

    // TODO: PUT THIS IN A UTILITIES CLASS
    Date getDate();

    String getDateFormatted();

    void setListItemContent(View v, Cursor c);

    int getListItemLayoutId();

    String getCategory();
    String getAction();
    String getType();
}
