package chadamine.com.databaselist.Journals;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.*;
import android.support.v4.content.*;
import android.support.v4.view.MenuItemCompat;
import android.view.*;
import android.widget.*;

import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Database.DatabaseSchema.Journals;

import chadamine.com.databaselist.Adapters.ListCursorAdapter;

import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/12/2015.
 */
public class JournalsFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private Journal mJournal;

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager;
    private ListCursorAdapter mListCursorAdapter;
    private ListCursorAdapter mSpinnerCursorAdapter;

    private String mSortOrder;
    private int mSortSelection;

    private Bundle mBundle;

    private static final int LIST_LOADER_ID = 0;
    private static final String SORT_ASC = DatabaseSchema.SORT_ASC;
    private static final String SORT_DESC = DatabaseSchema.SORT_DESC;

    public JournalsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View thisView = inflater.inflate(R.layout.fragment_journals, container, false);
        mContext = getActivity();

        mJournal = new Journal(mContext);
        mLoaderManager = this;
        prepareList();

        return thisView;
    }

    private void prepareList() {

        getLoaderManager().initLoader(LIST_LOADER_ID, null, this);

        Cursor cursor = mContext.getContentResolver().query(mJournal.getUri(),
                mJournal.getKeyIdArray(), null, null, mSortOrder);

        mListCursorAdapter
                = new ListCursorAdapter(mContext, cursor, 0, mJournal);

        setListAdapter(mListCursorAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_journals, menu);

        Spinner spinner = (Spinner) MenuItemCompat
                .getActionView(menu.findItem(R.id.spinner_journals_sort));
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.journal_sort_items, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);

        spinner.setSelection(mSortSelection, false);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        mSortOrder = Journals.KEY_ID + SORT_ASC;
                        mSortSelection = 0;
                        break;

                    case 2:
                        mSortOrder = Journals.KEY_ID + SORT_DESC;
                        mSortSelection = 2;
                        break;

                    case 1:
                        mSortOrder = Journals.KEY_NAME + SORT_ASC;
                        mSortSelection = 1;
                        break;

                    case 3:
                        mSortOrder = Journals.KEY_NAME + SORT_DESC;
                        mSortSelection = 3;
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
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_journal:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_journals_activity,
                                JournalNewFragment.newInstance(mBundle))
                        .addToBackStack("newJournal")
                        .commit();
                break;
        }
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState = mBundle;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            mBundle = savedInstanceState;
        } else {
            mBundle = new Bundle();
        }

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        getListView().setItemsCanFocus(false);
		
		getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {

					mode.getMenuInflater().inflate(R.menu.menu_journals_action, menu);
					return true;
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					return false;
				}

				@Override
				public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
					switch(item.getItemId()) {
						case R.id.delete_journal:

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

					mListCursorAdapter.toggleSelection(position);
                    String plurality = mListCursorAdapter.getSelectedItems().size()
                            == 1 ? " Journal Selected" : " Journals Selected";
                    mode.setTitle(getListView().getCheckedItemCount() + plurality);
                }

			});
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        getFragmentManager().beginTransaction()
                .replace(R.id.frame_journals_activity, JournalViewFragment.newInstance(mBundle))
                .addToBackStack("journalView").commit();

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

       /* String[] projection = {
                Journals.KEY_ID,
                //   products.KEY_MANUFACTURER,
                //   products.KEY_TYPE,
                //   products.KEY_PRODUCTLINE,
                Journals.KEY_NAME
        };

        CursorLoader cursorLoader =
                new CursorLoader(mContext,
                        Journals.CONTENT_URI, Journals.KEY_ID_ARRAY, null, null, null);*/

        String sortOrder = "";
        if(args != null)
            sortOrder = args.getString("sortOrder");

            return new CursorLoader(mContext, mJournal.getUri(),
                    mJournal.getKeyIdArray(), null, null, sortOrder);
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
