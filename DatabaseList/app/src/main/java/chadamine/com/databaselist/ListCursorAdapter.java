package chadamine.com.databaselist;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by chadamine  on 4/12/2015.
 */
public class ListCursorAdapter extends CursorAdapter {

    Uri mUri;
    int mID;
    Context mContext;
    private HashMap<Integer, Long> mProductsIds;
    private SparseBooleanArray mSelectedItems;

    public ListCursorAdapter(Context context, Cursor c, Uri uri, int id) {
        super(context, c);
        mContext = context;
        mUri = uri;
        mID = id;
        mSelectedItems = new SparseBooleanArray();
        mProductsIds = new HashMap<>();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        switch(DatabaseContract.URI_MATCHER.match(mUri)) {

            case DatabaseContract.PRODUCTS:
                ((TextView) view.findViewById(R.id.textview_productlist_name)).setText(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.Products.KEY_NAME)));
                ((TextView) view.findViewById(R.id.textview_productlist_manufacturer)).setText(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.Products.KEY_MANUFACTURER)));
                ((TextView) view.findViewById(R.id.textview_productlist_type)).setText(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.Products.KEY_TYPE)));
                ((TextView) view.findViewById(R.id.textview_productlist_line)).setText(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.Products.KEY_LINE)));
                break;

            case DatabaseContract.JOURNALS:
                ((TextView) view.findViewById(R.id.textview_journallist_name)).setText(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.Journals.KEY_NAME)));
                /*((TextView) view.findViewById(android.R.id.text1)).setText(
                        cursor.getString(cursor.getColumnIndex(DatabaseContract.Journals.KEY_NAME)));*/

                break;

            default:
        }
    }

    public void toggleSelection(int position) {

        boolean checked = !mSelectedItems.get(position);
        Cursor cursor = mContext.getContentResolver().query(DatabaseContract.Products.CONTENT_URI,
                DatabaseContract.Products.KEY_ID_ARRAY, "_id", null, null);

        cursor.moveToPosition(position);
        if(checked) {
            mSelectedItems.put(position, checked);
            //Toast.makeText(mContext, "Position: " + position, Toast.LENGTH_SHORT).show();
            mProductsIds.put(position,
                    cursor.getLong(
                            cursor.getColumnIndex(DatabaseContract.Products.KEY_ID)));
        } else {
            mProductsIds.remove(position);
            mSelectedItems.delete(position);
        }

        notifyDataSetChanged();
    }

    public void refreshSelection() {
        mProductsIds = new HashMap<>();
        mSelectedItems = new SparseBooleanArray();
    }

    public void remove(int key) {
        mContext.getContentResolver().delete(DatabaseContract.Products.CONTENT_URI,
                "_id = " + mProductsIds.get(key), null);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(mID, parent, false);
    }
}
