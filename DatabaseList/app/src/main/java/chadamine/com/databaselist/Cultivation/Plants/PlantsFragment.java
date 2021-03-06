package chadamine.com.databaselist.Cultivation.Plants;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.view.ActionMode;
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

import java.util.List;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter.FirstPageFragmentListener;
import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Database.DatabaseSchema.Plants;
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

    private long mId;
    private static FirstPageFragmentListener mListener;

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager;

    private ListCursorAdapter mListCursorAdapter;

    private static final int LIST_LOADER_ID = 0;
    private static final String SORT_DESC = DatabaseSchema.SORT_DESC;
    private static final String SORT_ASC = DatabaseSchema.SORT_ASC;

   public PlantsFragment() { }

    public static PlantsFragment newInstance(Bundle args) {
        PlantsFragment f = new PlantsFragment();
        if(args != null)
            f.setArguments(args);
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

        mView = inflater.inflate(R.layout.fragment_simple_list, container, false);
        mContext = getActivity();
        mPlant = new Plant(mContext);
        mLoaderManager = this;

        getLoaderManager().initLoader(LIST_LOADER_ID, mBundle, this);

        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("plant_overview"));

        mListCursorAdapter = new ListCursorAdapter(mContext, null, 0, mPlant);
        setListAdapter(mListCursorAdapter);

        if(getArguments() != null)
            mBundle = getArguments();

        //TODO: FIND BETTER WAY (VIEWPAGER?) TO HANDLE EXCESS FRAGMENTS
        // HACK TO REMOVE FRAGMENTS LEFTOVER FROM OVERVIEW FIASCO

        FragmentManager manager = getFragmentManager();
        List<Fragment> fragments = manager.getFragments();

        for(Fragment f : fragments) {
            if(f != null)
                if (fragments.indexOf(f) > 0)
                    manager.beginTransaction().remove(f).commit();
        }

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            mBundle = getArguments();
            if(mBundle.containsKey("sortSelection"))
                mSortSelection = mBundle.getInt("sortSelection");
        } else if(mBundle == null)
            mBundle = new Bundle();

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                mode.getMenuInflater().inflate(R.menu.menu_plants_action, menu);
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

                        DialogFragment dialog = new DialogFragment();
                        dialog.show(getFragmentManager(), null);

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

                mListCursorAdapter.toggleSelection(position);
                String plurality = mListCursorAdapter
                        .getSelectedItems().size() == 1 ? " Plant Selected" : " Plants Selected";
                mode.setTitle(getListView().getCheckedItemCount() + plurality);
            }

        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_plants, menu);

        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menu.findItem(R.id.spinner_plant_menu));
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.plant_sort_items, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);

        // set spinner selection with no animation
        // prevents firing setOnItemSelectedListener when instantiated
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

        mBundle.putInt("position", position);
        mBundle.putLong("id", id);
        mBundle.putBoolean("isNew", false);
        mBundle.putString("type", "plant");

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity,
                        PlantOverviewFragment.newInstance(mBundle), "plant_overview")
                .addToBackStack("plant_overview")
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mBundle.putBoolean("isNew", true);
        mBundle.remove("id");

        switch(item.getItemId()) {
            case R.id.add_plant:

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_plant_activity,
                                PlantOverviewFragment.newInstance(mBundle), "plant_overview")
                        .addToBackStack("plant_overview")
                        .commit();

                break;
        }

        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState = mBundle;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String sortOrder = "";
        if(args != null) {
            sortOrder = args.getString("sortOrder");
        }

        return new CursorLoader(mContext, mPlant.getUri(),
                mPlant.getKeyIdArray(), null, null, sortOrder);
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
