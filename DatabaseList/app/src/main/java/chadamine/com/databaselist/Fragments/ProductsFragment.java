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
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Spinner;

import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Objects.Product;
import chadamine.com.databaselist.R;
import chadamine.com.databaselist.Adapters.SpinnerCursorAdapter;

/**
 * Created by chadamine on 4/10/2015.
 */
public class ProductsFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListCursorAdapter mListCursorAdapter;
    private static final int LIST_LOADER_ID = 0;
    private Product mProduct;
    private Context mContext;

    public ProductsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        View thisView = inflater.inflate(R.layout.fragment_products, container, false);

        mProduct = new Product(mContext);

        prepareList();
        prepareSpinner(thisView);

        thisView.findViewById(R.id.button_addproduct)
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        getFragmentManager()
                                .beginTransaction()
                                .addToBackStack("newProduct")
                                .replace(R.id.container, new ProductNewFragment())
                                .commit();
                    }
                });

        return thisView;
    }

    private void prepareList() {
        getLoaderManager().initLoader(LIST_LOADER_ID, null, this);

        mListCursorAdapter
                = new ListCursorAdapter(mContext, null,
                0, mProduct);
        setListAdapter(mListCursorAdapter);
    }

    private void prepareSpinner(View view) {
        final Cursor cursor =  mContext.getContentResolver().query(
                DatabaseContract.Journals.CONTENT_URI,
                null, null, null, null);

        final SpinnerCursorAdapter spinnerCursorAdapter = new SpinnerCursorAdapter(mContext,
                getMergedCursor(cursor));

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_sample);
        spinner.setAdapter(spinnerCursorAdapter);
    }

    private Cursor getMergedCursor(Cursor c) {

        String[] projection = new String[] {"_id", "name" };
        MatrixCursor extras = new MatrixCursor(projection);
        extras.addRow(new String[] { "-1", "Select Journal..."});
        extras.addRow(new String[] { "-2", "New..."});
        Cursor[] cursors = { extras, c };
        Cursor extendedCursor = new MergeCursor(cursors);

        return extendedCursor;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

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
    public Loader onCreateLoader(int id, Bundle args) {

        return new CursorLoader(mContext,
            DatabaseContract.Products.CONTENT_URI,
            DatabaseContract.Products.KEY_ID_ARRAY,
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
