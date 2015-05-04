package chadamine.com.databaselist.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseContract;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plants, container, false);

        prepareList();

        return view;
    }

    /**
     * Attach to list view once the view hierarchy has been created.
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void prepareList() {

        getLoaderManager().initLoader(LIST_LOADER_ID, null, this);
        Cursor cursor = getActivity().getContentResolver().query(DatabaseContract.Plants.CONTENT_URI, DatabaseContract.Plants.KEY_ID_ARRAY, null, null, null);
        String[] from = DatabaseContract.Plants.KEY_ARRAY;
        int[] to = {R.id.textview_plants_item_name, R.id.textview_plants_item_species, R.id.textview_plants_item_stage, R.id.textview_plants_item_age, R.id.textview_plants_item_date };

        mListCursorAdapter
/*
                = new SimpleCursorAdapter(getActivity(), R.layout.list_item_plant, null, from, to, 0);
*/
                = new ListCursorAdapter(getActivity(), null,
                DatabaseContract.Plants.CONTENT_URI, R.layout.list_item_plant);
        setListAdapter(mListCursorAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                mode.getMenuInflater().inflate(R.menu.menu_fragment_plants, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete:

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

    /**
     * Called when all saved state has been restored into the view hierarchy
     * of the fragment.  This can be used to do initialization based on saved
     * state that you are letting the view hierarchy track itself, such as
     * whether check box widgets are currently checked.  This is called
     * after {@link #onActivityCreated(Bundle)} and before
     * {@link #onStart()}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        //prepareList();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                DatabaseContract.Plants.CONTENT_URI,
                DatabaseContract.Plants.KEY_ID_ARRAY,
                null, null, null);
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
