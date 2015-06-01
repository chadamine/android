package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Database.DatabaseSchema.Products;
import chadamine.com.databaselist.Objects.Product;
import chadamine.com.databaselist.R;
import chadamine.com.databaselist.Adapters.SpinnerCursorAdapter;

/**
 * Created by chadamine on 4/10/2015.
 */
public class ProductsFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private String mSortOrder;
    private int mSortSelection;

    private static final int LIST_LOADER_ID = 0;

    private Context mContext;
    private Product mProduct;
    private Bundle mBundle;

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager;
    private ListCursorAdapter mListCursorAdapter;

    public static ProductsFragment newInstance(Bundle args) {
        ProductsFragment f = new ProductsFragment();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        View thisView = inflater.inflate(R.layout.fragment_products, container, false);
        mLoaderManager = this;
        mProduct = new Product(mContext);
        getLoaderManager().initLoader(LIST_LOADER_ID, mBundle, this);

        prepareList();
        //prepareSpinner(thisView);

        return thisView;
    }

    private void prepareList() {
        getLoaderManager().initLoader(LIST_LOADER_ID, null, this);

        Cursor cursor =  mContext.getContentResolver().query(mProduct.getUri(),
                mProduct.getKeyIdArray(), null, null, mSortOrder);

        mListCursorAdapter
                = new ListCursorAdapter(mContext, cursor,
                0, mProduct);
        setListAdapter(mListCursorAdapter);
    }
/*
    private void prepareSpinner(View view) {
        final Cursor cursor =  mContext.getContentResolver().query(
                DatabaseSchema.Journals.CONTENT_URI,
                null, null, null, null);

        final SpinnerCursorAdapter spinnerCursorAdapter = new SpinnerCursorAdapter(mContext,
                getMergedCursor(cursor));

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_sample);
        spinner.setAdapter(spinnerCursorAdapter);
    }*/
/*
    private Cursor getMergedCursor(Cursor c) {

        String[] projection = new String[] {"_id", "name" };
        MatrixCursor extras = new MatrixCursor(projection);
        extras.addRow(new String[] { "-1", "Select Journal..."});
        extras.addRow(new String[] { "-2", "New..."});
        Cursor[] cursors = { extras, c };
        Cursor extendedCursor = new MergeCursor(cursors);

        return extendedCursor;
    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            mBundle = savedInstanceState;
        } else if(getArguments() != null) {
            mBundle = getArguments();
        } else if(mBundle == null)
            mBundle = new Bundle();

        if(mBundle.containsKey("sortSelection"))
            mSortSelection = mBundle.getInt("sortSelection");


        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        getListView().setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {

                mode.getMenuInflater().inflate(R.menu.menu_products_action, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
               switch(item.getItemId()) {
                   case R.id.delete_products:

                       for(int i = (getListView().getCheckedItemCount() - 1); i >= 0; i--) {
                               int checkedItemKey = getListView()
                                       .getCheckedItemPositions()
                                       .keyAt(i);
                           mListCursorAdapter.remove(checkedItemKey);
                       }

                       mode.finish();
                   case R.id.select_all_products:
                       //mListCursorAdapter.selectAll();
                       break;
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
                String plurality = mListCursorAdapter.getSelectedItems().size() == 1 ? " Product Selected" : " Products Selected";
                mode.setTitle(getListView().getCheckedItemCount() + plurality);

            }

        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_products, menu);

        Spinner spinner = (Spinner) MenuItemCompat
                .getActionView(menu.findItem(R.id.spinner_products_sort));
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(mContext,
                R.array.product_sort_items, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);

        spinner.setSelection(mSortSelection);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0:
                        mSortOrder = Products.KEY_ID + DatabaseSchema.SORT_ASC;
                        mSortSelection = 0;
                        break;

                    case 4:
                        mSortOrder = Products.KEY_ID + DatabaseSchema.SORT_DESC;
                        mSortSelection = 5;
                        break;

                    case 2:
                        mSortOrder = Products.KEY_NAME + DatabaseSchema.SORT_ASC;
                        mSortSelection = 1;
                        break;

                    case 5:
                        mSortOrder = Products.KEY_NAME + DatabaseSchema.SORT_DESC;
                        mSortSelection = 6;
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

        switch(item.getItemId()) {
            case R.id.add_product:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_product_activity,
                                ProductNewFragment.newInstance(mBundle))
                .addToBackStack("newProduct")
                        .commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        if(args != null)
            mSortOrder = args.getString("sortOrder");

        return new CursorLoader(mContext,
            DatabaseSchema.Products.CONTENT_URI,
            DatabaseSchema.Products.KEY_ID_ARRAY,
            null, null, mSortOrder);
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
