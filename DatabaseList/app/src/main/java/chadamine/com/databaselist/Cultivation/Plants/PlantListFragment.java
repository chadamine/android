package chadamine.com.databaselist.Cultivation.Plants;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.BaseFragments.BaseListFragment;
import chadamine.com.databaselist.Database.DatabaseSchema.Plants;
import chadamine.com.databaselist.R;

public class PlantListFragment extends BaseListFragment {

    private final String BACKSTACK_LABEL = "plant_overview";
    private static CustomFragmentPagerAdapter.FirstPageFragmentListener mListener;

    public PlantListFragment() {
        super.mLayout = R.layout.fragment_simple_list;
        super.mUri = Plants.CONTENT_URI;
        super.mDelete = R.id.delete_plant;
        super.mAdd = R.id.add_plant;

        super.mSingular = "Plant";
        super.mPlural = "Plants";
        super.mSortSelection = -1;
        super.mMenu = R.menu.menu_plants;
        super.mActionMenu = R.menu.menu_plants_action;
        super.mSortArray = R.array.plant_sort_items;
        super.mSpinnerSort = R.id.spinner_plant_menu;
        super.mActivityFrame = R.id.frame_plant_activity;
    }

    public static PlantListFragment newInstance(Bundle args, CustomFragmentPagerAdapter.FirstPageFragmentListener listener) {
        PlantListFragment f = new PlantListFragment();
        if(args != null)
            f.setArguments(args);

        mListener = listener;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        mContext = getActivity();
        mDBObject = new Plant(mContext);

        super.onCreate(savedInstanceState);
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

        mBundle.putString("type", "plant");

        View view = super.inflateView(mLayout, inflater, container);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mBundle.putBoolean(KEY_IS_NEW, true);
        mBundle.remove(KEY_ID);

        if(item.getItemId() == mAdd) {
            replaceFragment();
        }

        return true;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        mBundle.putInt(KEY_POSITION, position);
        mBundle.putLong(KEY_ID, id);
        mBundle.putBoolean(KEY_IS_NEW, false);

        replaceFragment();
    }

    private void replaceFragment() {
        mListener.onSwitchToNewFragment(mBundle);
        /*getFragmentManager().beginTransaction()
                .replace(mActivityFrame,
                        PlantViewFragment.newInstance(mBundle, mListener), BACKSTACK_LABEL)
                .addToBackStack(BACKSTACK_LABEL)
                .commit();*/
    }
}