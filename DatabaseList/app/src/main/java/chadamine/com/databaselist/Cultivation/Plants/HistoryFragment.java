package chadamine.com.databaselist.Cultivation.Plants;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import chadamine.com.databaselist.Adapters.ListCursorAdapter;
import chadamine.com.databaselist.BaseFragments.BaseListFragment;
import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.History.History;
import chadamine.com.databaselist.R;

public class HistoryFragment extends BaseListFragment {

    private Context mContext;
    private final String BACKSTACK_LABEL = "plant_history_overview";


    private Plant mPlant;
    private View mView;

    private String mSortOrder;
    private int mSortSelection;
    private Bundle mBundle;
    private History mHistory;

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderManager;
    private ListCursorAdapter mListCursorAdapter;

    private static final int LIST_LOADER_ID = 0;
    private static final String SORT_DESC = " DESC";
    private static final String SORT_ASC = " ASC";

    // TODO: Rename and change types of parameters
    public static HistoryFragment newInstance(Bundle args) {
        HistoryFragment f = new HistoryFragment();
        if(args != null)
            f.setArguments(args);
        return f;
    }

    public HistoryFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setRetainInstance(true);
        setMembers();
        super.onCreate(savedInstanceState);
    }

    public void setMembers() {
        mContext = getActivity();
        mDBObject = new Plant(mContext);
        mHistory = new History(mContext, mDBObject);

        mLayout = R.layout.fragment_simple_list;
        mUri = DatabaseSchema.Histories.CONTENT_URI;

        mSingular = "History";
        mPlural = "Histories";
        mSortSelection = -1;
        //mMenu = R.menu.menu_plants;
        //mActionMenu = R.menu.menu_plants_action;
        //mSortArray = R.array.plant_sort_items;
        //mSpinnerSort = R.id.spinner_plant_menu;
        //mActivityFrame = R.id.frame_plant_activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(getArguments() != null)
            mBundle = getArguments();

        else if(savedInstanceState != null)
            mBundle = savedInstanceState;
        else
            mBundle = new Bundle();

        View view = inflateView(mLayout, inflater, container);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mBundle.putInt(KEY_POSITION, position);
        mBundle.putLong(KEY_ID, id);
        mBundle.putBoolean(KEY_IS_NEW, false);

        replaceFragment();
    }

    private void replaceFragment() {
        /*getFragmentManager().beginTransaction()
                .replace(mActivityFrame, PlantNewOverviewFragment.newInstance(mBundle), BACKSTACK_LABEL)
                .addToBackStack(BACKSTACK_LABEL)
                .commit();*/
    }

    // TODO: MOVE THIS TO BASELISTFRAGMENT
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = getListView().getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /*@Override
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }*/

}
