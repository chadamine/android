package chadamine.com.databaselist.Cultivation.Plants;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.BaseFragments.SortableListFragment;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.R;

public class PlantHistoriesFragment extends SortableListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

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

    // TODO: Rename and change types of parameters
    public static PlantHistoriesFragment newInstance(Bundle args) {
        PlantHistoriesFragment f = new PlantHistoriesFragment();
        if(args != null)
            f.setArguments(args);
        return f;
    }

    public PlantHistoriesFragment() {
        mAddBackStack = "plant_histories";
        mUri = DatabaseSchema.PlantHistories.CONTENT_URI;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_history, container, false);

        return view;
    }


    public void onListItemClick(ListView l, View v, int position, long id) {

    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
