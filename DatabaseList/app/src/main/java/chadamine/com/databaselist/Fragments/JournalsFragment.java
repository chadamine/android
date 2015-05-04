package chadamine.com.databaselist.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.view.*;
import android.widget.*;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/12/2015.
 */
public class JournalsFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListCursorAdapter mListCursorAdapter;
    private ListCursorAdapter mSpinnerCursorAdapter;

    public JournalsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_journals, container, false);

        getLoaderManager().initLoader(0, null, this);

        mListCursorAdapter
                = new ListCursorAdapter(getActivity(),
                null, DatabaseContract.Journals.CONTENT_URI, R.layout.list_item_journal);
        setListAdapter(mListCursorAdapter);


        Button btnAdd = (Button) thisView.findViewById(R.id.button_addjournal);
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack("newJournal")
                        .replace(R.id.container, new NewJournalFragment())
                        .commit();
            }
        });

        return thisView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        getListView().setItemsCanFocus(false);
		
		getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {

					mode.getMenuInflater().inflate(R.menu.menu_products, menu);
					return true;
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					return false;
				}

				@Override
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					switch(item.getItemId()) {
						case R.id.delete:

							for(int i = (getListView().getCheckedItemCount() - 1); i >= 0; i--) {
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

					mode.setTitle(getListView().getCheckedItemCount() + " Products Selected");
					mListCursorAdapter.toggleSelection(position);
				}

			});
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        String[] projection = {
                DatabaseContract.Journals.KEY_ID,
                //   products.KEY_MANUFACTURER,
                //   products.KEY_TYPE,
                //   products.KEY_PRODUCTLINE,
                DatabaseContract.Journals.KEY_NAME
        };

        CursorLoader cursorLoader = new CursorLoader(getActivity(), DatabaseContract.Journals.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
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
