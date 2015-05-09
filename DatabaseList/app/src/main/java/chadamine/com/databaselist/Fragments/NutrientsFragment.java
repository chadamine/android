package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema.Nutrients;
import chadamine.com.databaselist.Objects.Nutrient;
import chadamine.com.databaselist.R;

public class NutrientsFragment extends ListFragment
    implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private Nutrient mNutrient;
    private View mView;
    //private String mSortOrder;
    private int mSortSelection;
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager;

    private ListCursorAdapter mListCursorAdapter;

    private static final int LIST_LOADER_ID = 0;
    private static final String SORT_DESC = " DESC";
    private static final String SORT_ASC = " ASC";

    /*public static NutrientsFragment newInstance(String param1, String sortOrder) {
        NutrientsFragment fragment = new NutrientsFragment();
        mSortOrder = sortOrder;

        return fragment;
    }*/

    public NutrientsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_nutrients, container, false);
        mContext = getActivity();
        mNutrient = new Nutrient(mContext);
        mListCursorAdapter = new ListCursorAdapter(mContext, null, 0, mNutrient);
        mLoaderManager = this;

        setHasOptionsMenu(true);

        if(savedInstanceState != null) {
            //mSortOrder = savedInstanceState.getString("sortOrder");
            mSortSelection = savedInstanceState.getInt("sortSelection");
            getLoaderManager().initLoader(LIST_LOADER_ID, savedInstanceState, this);
        }
        else
            getLoaderManager().initLoader(LIST_LOADER_ID, null, this);

        setListAdapter(mListCursorAdapter);

        return mView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment f = new NutrientNewFragment();
        Bundle args = new Bundle();
        args.putInt("sortSelection", mSortSelection);
        //args.putString("sortOrder", mSortOrder);

        switch (item.getItemId()) {

            case R.id.add_nutrient:
                f.setArguments(args);
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_nutrient_activity, new NutrientNewFragment())
                        .addToBackStack("newNutrient")
                        .commit();
                break;
        }

        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_nutrients, menu);

        Spinner spinner = (Spinner) MenuItemCompat
                .getActionView(menu.findItem(R.id.spinner_nutrients_sort));
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.nutrient_sort_items, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);

        spinner.setSelection(mSortSelection);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bundle loaderBundle = new Bundle();
                String sortOrder = "";

                switch (position) {

                    case 0:
                        sortOrder = Nutrients.KEY_ID + SORT_ASC;
                        mSortSelection = 0;
                        break;

                    case 5:
                        sortOrder = Nutrients.KEY_ID + SORT_DESC;
                        mSortSelection = 5;
                        break;

                    case 1:
                        sortOrder = Nutrients.KEY_NAME + SORT_ASC;
                        mSortSelection = 1;
                        break;

                    case 6:
                        sortOrder = Nutrients.KEY_NAME + SORT_DESC;
                        mSortSelection = 6;
                        break;
                }

                loaderBundle.putString("sortOrder", sortOrder);
                getLoaderManager().restartLoader(LIST_LOADER_ID, loaderBundle, mLoaderManager);
            }

            // this should not happen in this case
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String sortOrder = "";
        if(args != null)
            sortOrder = args.getString("sortOrder");

        return new CursorLoader(mContext, mNutrient.getUri(),
                mNutrient.getKeyIdArray(), null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mListCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mListCursorAdapter.swapCursor(null);
    }
}
