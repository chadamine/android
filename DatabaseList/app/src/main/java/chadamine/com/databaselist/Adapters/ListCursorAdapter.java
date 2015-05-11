package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import java.util.HashMap;

/**
 * Created by chadamine  on 4/12/2015.
 */
public class ListCursorAdapter extends CursorAdapter {

    private int mID;
    private DatabaseAdapter mDatabaseObject;
    private View mView;
    private Cursor mCursor;
	
    Context mContext;
    private HashMap<Integer, Long> mIds;
    private SparseBooleanArray mSelectedItems;

    public ListCursorAdapter(Context context, Cursor c, int flags, DatabaseAdapter dbObject) {
        super(context, c, flags);
        mContext = context;
        mDatabaseObject = dbObject;
        mIds = new HashMap<>();
        mSelectedItems = new SparseBooleanArray();
        mCursor = c;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        mDatabaseObject.setListItemContent(view, cursor);

        // TODO: MAKE THIS WORK
        //mDatabaseObject.setContent();

        mView = view;
    }

	@Override
	public Cursor getCursor() {
        return mCursor;
	}

    public void selectAll() {

        for(int i = 0; i < mSelectedItems.size(); i++) {
            mCursor.moveToPosition(i);
            mSelectedItems.put(i, false);
            mIds.put(i, mCursor.getLong(
                    mCursor.getColumnIndex(mDatabaseObject.getKeyID())));
        }

        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        boolean checked = !mSelectedItems.get(position);
		//Cursor cursor = getCursor();

        mCursor.moveToPosition(position);

        if(checked) {
            mSelectedItems.put(position, checked);
            mIds.put(position, mCursor.getLong(
                            mCursor.getColumnIndex(mDatabaseObject.getKeyID())));
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
        return LayoutInflater.from(context).inflate(mDatabaseObject.getListItemLayoutId(), parent, false);
    }

    public SparseBooleanArray getSelectedItems() {
        return mSelectedItems;
    }
}
