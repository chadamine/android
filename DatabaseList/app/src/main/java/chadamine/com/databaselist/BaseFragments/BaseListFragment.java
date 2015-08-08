package chadamine.com.databaselist.BaseFragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.List;

import chadamine.com.databaselist.Adapters.DatabaseAdapter;
import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.R;

public class BaseListFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    public Bundle mBundle;
    public Context mContext;
    public Uri mUri;
    public int mUrId;
    public DatabaseAdapter mDBObject;
    public ListCursorAdapter mListCursorAdapter;
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager = this;
    public int mSortSelection;
    public String mSortOrder;
    public BaseOverviewFragment mBaseFragment;

    private final String KEY_SORT_SELECTION = "sort_selection";
    private final String KEY_SORT_ORDER = "sort_order";

    public @LayoutRes int mLayout;
    public @MenuRes int mMenu;
    public @MenuRes int mActionMenu;
    public @IdRes int mDelete;
    public @ArrayRes int mSortArray;
    public @IdRes int mSpinnerSort;

    private int getUrId() {
        return mUrId;
    }

    public static BaseListFragment newInstance(Bundle args) {
        BaseListFragment f = new BaseListFragment();

        if(args != null)
            f.setArguments(args);
        return f;
    }

    public BaseListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrId = DatabaseSchema.URI_MATCHER.match(mUri);

        if (getArguments() != null) {
            mBundle = getArguments();

            if(mBundle.containsKey(KEY_SORT_SELECTION))
                mSortSelection = mBundle.getInt(KEY_SORT_SELECTION);
        }

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    protected View inflateView(@LayoutRes int resId, LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(resId, container, false);

        prepareView();
        trimFragmentManager();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareList();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(mMenu, menu);

        Spinner spinner = (Spinner) MenuItemCompat
                .getActionView(menu.findItem(mSpinnerSort));
        prepareSpinner(spinner);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                        mBaseFragment.newInstance(mBundle), "plant_overview")
                .addToBackStack("plant_overview")
                .commit();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortOrder = "";
        if(args != null) {
            sortOrder = args.getString(KEY_SORT_ORDER);
        }

        return new CursorLoader(mContext, mDBObject.getUri(),
                mDBObject.getKeyIdArray(), null, null, sortOrder);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mListCursorAdapter.swapCursor(null);
    }

    private void prepareView() {
        getLoaderManager().initLoader(getUrId(), mBundle, this);

        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag("plant_overview"));

        mListCursorAdapter = new ListCursorAdapter(mContext, null, 0, mDBObject);
        setListAdapter(mListCursorAdapter);
    }

    private void trimFragmentManager() {
        FragmentManager manager = getFragmentManager();
        List<Fragment> fragments = manager.getFragments();

        for(Fragment f : fragments) {
            if(f != null)
                if (fragments.indexOf(f) > 0)
                    manager.beginTransaction().remove(f).commit();
        }
    }

    private void prepareList() {
        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                mode.getMenuInflater().inflate(mActionMenu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                if(item.getItemId() == mDelete){

                        DialogFragment dialog = new DialogFragment();
                        dialog.show(getFragmentManager(), null);

                        for (int i = (getListView().getCheckedItemCount() - 1); i >= 0; i--) {
                            int checkedItemKey = getListView()
                                    .getCheckedItemPositions()
                                    .keyAt(i);
                            mListCursorAdapter.remove(checkedItemKey);
                        }

                        mode.finish();
                }

                return true;
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

    private void prepareSpinner(Spinner spinner) {

        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(mContext,
                mSortArray, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);

        // set spinner selection with no animation
        // prevents firing setOnItemSelectedListener when instantiated
        spinner.setSelection(mSortSelection);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String desc = mDBObject.getKeyID() + DatabaseSchema.SORT_DESC;
                String asc = mDBObject.getKeyID() + DatabaseSchema.SORT_ASC;

                switch (position) {

                    case 0:
                        mSortOrder = asc;
                        mSortSelection = 0;
                        break;

                    case 10:
                        mSortOrder = desc;
                        mSortSelection = 10;
                        break;

                    case 1:
                        mSortOrder = asc;
                        mSortSelection = 1;
                        break;

                    case 11:
                        mSortOrder = desc;
                        mSortSelection = 11;
                        break;
                }

                mBundle.putString("sortOrder", mSortOrder);
                mBundle.putInt("sortSelection", mSortSelection);

                getLoaderManager().restartLoader(getUrId(), mBundle, mLoaderManager);
            }

            // this should not happen in this case
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
