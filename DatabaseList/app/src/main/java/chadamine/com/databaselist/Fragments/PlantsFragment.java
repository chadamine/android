package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Database.DatabaseContract.Plants;
import chadamine.com.databaselist.Objects.Plant;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/30/2015.
 */
public class PlantsFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private View mView;
    private ListCursorAdapter mListCursorAdapter;
    //private SimpleCursorAdapter mListCursorAdapter;
    private static final int LIST_LOADER_ID = 0;
    private Plant mPlant;
    private Context mContext;
    private static final String SORT_DESC = " DESC";
    private static final String SORT_ASC = " ASC";
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_plants, container, false);

        mPlant = new Plant(getActivity());
        mContext = getActivity();
        mView = view;
        mLoaderManager = this;

        prepareList();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        prepareMenuSpinner(menu, inflater);
    }

    private void prepareMenuSpinner(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plants_fragment_options, menu);

        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menu.findItem(R.id.spinner_plant_menu));
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.plant_sort_items, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bundle loaderBundle = new Bundle();
                String sortOrder = null;

                switch (position) {

                    case 0:
                        sortOrder = Plants.KEY_ID + SORT_ASC;
                        break;

                    case 1:
                        sortOrder = Plants.KEY_ID + SORT_DESC;
                        break;

                    case 2:
                        sortOrder = Plants.KEY_NAME + SORT_ASC;
                        break;

                    case 3:
                        sortOrder = Plants.KEY_NAME + SORT_DESC;
                        break;
                }

                loaderBundle.putString("sort_order", sortOrder);
                getLoaderManager().restartLoader(LIST_LOADER_ID, loaderBundle, mLoaderManager);
                mListCursorAdapter.notifyDataSetChanged();
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

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity, PlantViewFragment.newInstance(position))
                .addToBackStack("plant_view").commit();
    }

    private void prepareList() {

        getLoaderManager().initLoader(LIST_LOADER_ID, null, this);

        mListCursorAdapter
                = new ListCursorAdapter(getActivity(), null, 0, mPlant);

        setListAdapter(mListCursorAdapter);

    }

    // TODO: SETUP CONTEXT MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(getActivity(),
               Plants.CONTENT_URI,
                Plants.KEY_ID_ARRAY,
                null, null,
                args != null ? args.getString("sort_order") : null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        mListCursorAdapter.notifyDataSetChanged();
        getListView().setAdapter(mListCursorAdapter);   // <-

        mListCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mListCursorAdapter.swapCursor(null);
    }

}
