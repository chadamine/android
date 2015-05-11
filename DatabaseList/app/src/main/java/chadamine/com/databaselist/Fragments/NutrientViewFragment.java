package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Objects.Nutrient;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 5/9/2015.
 */
public class NutrientViewFragment extends Fragment {

    private Bundle mBundle;
    private Context mContext;
    private View mView;
    private Nutrient mNutrient;

    public static NutrientViewFragment newInstance(Bundle savedInstanceState) {

        NutrientViewFragment f = new NutrientViewFragment();
        f.setArguments(savedInstanceState);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_nutrient_view, container, false);
        mNutrient = new Nutrient(mContext);

        //if (savedInstanceState != null)
            mBundle = getArguments();

        setFields();

        return mView;
    }

    private void setFields() {

        String sortOrder = "";
        String selection = "";
        String[] selectionArgs = {};
        int position = 0;

        if(mBundle != null) {

            if(mBundle.containsKey("position"))
                position = mBundle.getInt("position");
            if (mBundle.containsKey("sortOrder"))
                sortOrder = mBundle.getString("sortOrder");
            if (mBundle.containsKey("selection"))
                selection = mBundle.getString("selection");
            if (mBundle.containsKey("selectionArgs"))
                selectionArgs = mBundle.getStringArray("selectionArgs");
        }

        Cursor c = mContext.getContentResolver().query(mNutrient.getUri(),
                mNutrient.getKeyIdArray(), selection, selectionArgs, sortOrder);

        try {
            c.moveToPosition(position);
        } catch (IndexOutOfBoundsException e) {
            Log.w("cursor_position", e.toString());
        }

        ((TextView) mView.findViewById(R.id.textview_nutrient_view_name))
                .setText(c.getString(c.getColumnIndexOrThrow(DatabaseSchema.Nutrients.KEY_NAME)));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_nutrient_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.edit_nutrient:

                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
