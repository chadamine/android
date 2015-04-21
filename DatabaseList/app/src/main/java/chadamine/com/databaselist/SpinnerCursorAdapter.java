package chadamine.com.databaselist;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by calipinski on 4/19/2015.
 */


public class SpinnerCursorAdapter extends CursorAdapter {

    private Context mContext;
    private Cursor mCursor;

    /**
     * Swap in a new Cursor, returning the old Cursor.  Unlike
     * {@link #changeCursor(android.database.Cursor)}, the returned old Cursor is <em>not</em>
     * closed.
     *
     * @param newCursor The new cursor to be used.
     * @return Returns the previously set Cursor, or null if there wasa not one.
     * If the given new Cursor is the same instance is the previously set
     * Cursor, null is also returned.
     */
    @Override
    public Cursor swapCursor(Cursor newCursor) {
        return super.swapCursor(newCursor);
    }

    public SpinnerCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {


        return LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
           return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ((TextView) view.findViewById(android.R.id.text1)).setText(cursor.getString(
                cursor.getColumnIndex(DatabaseContract.Journals.KEY_NAME)));
    }
}
