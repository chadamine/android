package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.HashMap;

import android.widget.*;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine  on 4/12/2015.
 */
public class ListCursorAdapter extends CursorAdapter {

    private Uri mUri;
    private int mID;
	private String mKeyID;
	private String[] mKeyArray;
	
    Context mContext;
    private HashMap<Integer, Long> mIds;
    private SparseBooleanArray mSelectedItems;

    public ListCursorAdapter(Context context, Cursor c, Uri uri, int id) {
        super(context, c);
        mContext = context;
        mUri = uri;
        mID = id;
        mSelectedItems = new SparseBooleanArray();
        mIds = new HashMap<>();
		setDatabase();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

		
		HashMap<String, EditText> map = new HashMap<>();
		//for(int i = 0; i<mKeyArray.length; i++)
		EditText name = (EditText) view.findViewById(R.id.textview_productlist_name);
		    map.put(cursor.getString(cursor.getColumnIndex(mKeyArray[0])), name);
		//map.put(cursor.getString(cursor.getColumnIndex(mKeyArray[1])), R.id.textview_productlist_manufacturer);
		//map.put(cursor.getString(cursor.getColumnIndex(mKeyArray[2])), R.id.textview_productlist_type);
		//map.put(cursor.getString(cursor.getColumnIndex(mKeyArray[3])), R.id.textview_productlist_line);
		
		
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
		
		
		
		//map.get(key)
		//for(String key:mKeyArray)
		for(int i = 0; i < mKeyArray.length; i++)
			(map.get(mKeyArray[i])).setText(
				cursor.getString(cursor.getColumnIndex(mKeyArray[i])));
    }
	
	private void setDatabase() {
		switch(DatabaseContract.URI_MATCHER.match(mUri)) {

            case DatabaseContract.PRODUCTS:
				mKeyID = DatabaseContract.Products.KEY_ID;
				mKeyArray = DatabaseContract.Products.KEY_ID_ARRAY;
				mUri = DatabaseContract.Products.CONTENT_URI;
				break;

			case DatabaseContract.JOURNALS:
				mKeyID = DatabaseContract.Journals.KEY_ID;
				mUri = DatabaseContract.Journals.CONTENT_URI;
				mKeyArray = DatabaseContract.Journals.KEY_ID_ARRAY;
				break;
				
			default:
				mKeyID = "";
				mUri = null;
				mKeyArray = null;
		}
	}

	@Override
	public Cursor getCursor() {
		
		return mContext.getContentResolver().query(mUri, mKeyArray, mKeyID, null, null);
	}
	
	

    public void toggleSelection(int position) {

        boolean checked = !mSelectedItems.get(position); 
		
		//setDatabase();
		Cursor cursor = getCursor();

        cursor.moveToPosition(position);
		
        if(checked) {
            mSelectedItems.put(position, checked);
            mIds.put(position,
                    cursor.getLong(
                            cursor.getColumnIndex(mKeyID)));
        } else {
            mIds.remove(position);
            mSelectedItems.delete(position);
        }

        notifyDataSetChanged();
    }

    public void refreshSelection() {
		
        mIds = new HashMap<>();
        mSelectedItems = new SparseBooleanArray();
    }

	
    public void remove(int key) {
		
        mContext.getContentResolver().delete(mUri, mKeyID + " = " + mIds.get(key), null);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
        return LayoutInflater.from(context).inflate(mID, parent, false);
    }
}
