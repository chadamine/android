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

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Objects.DatabaseObject;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine  on 4/12/2015.
 */
public class ListCursorAdapter extends CursorAdapter {

    //private Uri mUri;
    private int mID;
	//private String mKeyID;
	//private String[] mKeyArray;
    private DatabaseObject mDatabaseObject;
    private View mView;
	
    Context mContext;
    private HashMap<Integer, Long> mIds;
    private SparseBooleanArray mSelectedItems;

    public ListCursorAdapter(Context context, Cursor c, Uri uri, int flag, int id) {
        super(context, c, flag);
        mContext = context;
        //mUri = uri;
        mID = id;
        mSelectedItems = new SparseBooleanArray();
        mIds = new HashMap<>();
		//setDatabase();
    }

    public ListCursorAdapter(Context context, Cursor c, int flags, DatabaseObject dbObject) {
        super(context, c, flags);
        mContext = context;
        mDatabaseObject = dbObject;
        mIds = new HashMap<>();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        mDatabaseObject.setListItemContent(view);
        mView = view;

        /*switch(DatabaseContract.URI_MATCHER.match(mUri)) {

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
                break;

            case DatabaseContract.PLANTS:

                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_NAME));
                if(!name.isEmpty())
                    ((TextView) view.findViewById(R.id.textview_plants_item_name)).setText(name );

                String species = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_SPECIES));
                //if(species == "")
                    ((TextView) view.findViewById(R.id.textview_plants_item_species)).setText(species);


                String cultivar = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_CULTIVAR));
                //if(cultivar == "")
                    ((TextView) view.findViewById(R.id.textview_plants_item_cultivar)).setText(cultivar);

                String stage = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_STAGE));
                //if(stage == "")
                    ((TextView) view.findViewById(R.id.textview_plants_item_stage)).setText(stage);

                String age = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_AGE));
                //if(age == "")
                    ((TextView) view.findViewById(R.id.textview_plants_item_age)).setText(age);
                break;
        }*/
    }
	
	/*private void setDatabase() {
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

            case DatabaseContract.PLANTS:
                mKeyID = DatabaseContract.Plants.KEY_ID;
                mUri = DatabaseContract.Plants.CONTENT_URI;
                mKeyArray = DatabaseContract.Plants.KEY_ID_ARRAY;
                break;
        }
	}*/

	@Override
	public Cursor getCursor() {

        return mDatabaseObject.getCursor();
        /*return mContext.getContentResolver()
                .query(mDatabaseObject.getUri(),
                        mDatabaseObject.getKeyArray(),
                        mDatabaseObject.getKeyID(), null, null);*/

		//return mContext.getContentResolver().query(mUri, mKeyArray, mKeyID, null, null);
	}

    public void toggleSelection(int position) {

        boolean checked = !mSelectedItems.get(position); 
		
		Cursor cursor = getCursor();

        cursor.moveToPosition(position);
		
        if(checked) {
            mSelectedItems.put(position, checked);
            mIds.put(position,
                    cursor.getLong(
                            cursor.getColumnIndex(mDatabaseObject.getKeyID())));
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
		
        mContext.getContentResolver().delete(mDatabaseObject.getUri(), mDatabaseObject.getKeyID() + " = " + mIds.get(key), null);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
        return LayoutInflater.from(context).inflate(R.layout.list_item_plant, parent, false);
    }
}
