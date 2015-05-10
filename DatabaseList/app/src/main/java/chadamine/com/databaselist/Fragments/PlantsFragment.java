package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema.Plants;
import chadamine.com.databaselist.Objects.Plant;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/30/2015.
 */
public class PlantsFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private Plant mPlant;
    private View mView;

    private String mSortOrder;
    private int mSortSelection;
    private Bundle mBundle;

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager;

    private ListCursorAdapter mListCursorAdapter;

    private static final int LIST_LOADER_ID = 0;
    private static final String SORT_DESC = " DESC";
    private static final String SORT_ASC = " ASC";

   public PlantsFragment() { }

    public static PlantsFragment newInstance(Bundle savedInstanceState) {
        PlantsFragment f = new PlantsFragment();
        f.setArguments(savedInstanceState);

        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_plants, container, false);
        mContext = getActivity();
        mPlant = new Plant(getActivity());
        mLoaderManager = this;

        if(savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");

            mSortOrder = savedInstanceState.getString("sortOrder");
            mSortSelection = savedInstanceState.getInt("sortSelection");

            getLoaderManager().initLoader(LIST_LOADER_ID, mBundle, this);

        } else {
            mBundle = new Bundle();
            getLoaderManager().initLoader(LIST_LOADER_ID, null, this);
        }

        mListCursorAdapter = new ListCursorAdapter(getActivity(), null, 0, mPlant);
        setListAdapter(mListCursorAdapter);

        return mView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
            mSortOrder = mBundle.getString("sortOrder");
            mSortSelection = mBundle.getInt("sortSelection");
        }

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                mode.getMenuInflater().inflate(R.menu.menu_action_mode_plants, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete_plant:

                        for (int i = (getListView().getCheckedItemCount() - 1); i >= 0; i--) {
                            int checkedItemKey = getListView()
                                    .getCheckedItemPositions()
                                    .keyAt(i);
                            mListCursorAdapter.remove(checkedItemKey);
                        }

                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mListCursorAdapter.refreshSelection();
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id,
                                                  boolean checked) {

                mode.setTitle(getListView().getCheckedItemCount() + " Plants Selected");
                mListCursorAdapter.toggleSelection(position);
            }

        });
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_plants_plants, menu);

        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menu.findItem(R.id.spinner_plant_menu));
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.plant_sort_items, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);

        spinner.setSelection(mSortSelection);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        mSortOrder = Plants.KEY_ID + SORT_ASC;
                        mSortSelection = 0;
                        break;

                    case 10:
                        mSortOrder = Plants.KEY_ID + SORT_DESC;
                        mSortSelection = 10;
                        break;

                    case 1:
                        mSortOrder = Plants.KEY_NAME + SORT_ASC;
                        mSortSelection = 1;
                        break;

                    case 11:
                        mSortOrder = Plants.KEY_NAME + SORT_DESC;
                        mSortSelection = 11;
                        break;
                }

                mBundle.putString("sortOrder", mSortOrder);
                mBundle.putInt("sortSelection", mSortSelection);
                getLoaderManager().restartLoader(LIST_LOADER_ID, mBundle, mLoaderManager);
            }

            // this should not happen in this case
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        /*Fragment f = new PlantViewFragment();
        mBundle.putInt("sortSelection", mSortSelection);
        f.setArguments(mBundle);*/
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity, PlantViewFragment.newInstance(mBundle))
                .addToBackStack("plantView").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mBundle.putInt("sortSelection", mSortSelection);
        mBundle.putString("sortOrder", mSortOrder);

        Fragment f = new PlantNewFragment();
        f.setArguments(mBundle);

        switch(item.getItemId()) {
            case R.id.add_plant:
                getFragmentManager().beginTransaction()
                        .addToBackStack("newPlant")
                        .replace(R.id.frame_plant_activity, f).commit();
                break;
        }

        return true;
    }

    // TODO: SETUP CONTEXT MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle("bundle", mBundle);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String sortOrder = "";
        if(args != null) {
            sortOrder = args.getString("sortOrder");
        }

        return new CursorLoader(getActivity(),
               mPlant.getUri(), mPlant.getKeyIdArray(), null, null, sortOrder);
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
