package chadamine.com.databaselist.Cultivation.Plants;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import chadamine.com.databaselist.BaseFragments.BaseListFragment;
import chadamine.com.databaselist.Database.DatabaseSchema.Plants;
import chadamine.com.databaselist.R;

public class PlantListFragment extends BaseListFragment {

    private final String BACKSTACK_LABEL = "plant_overview";
    public PlantListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {

        mContext = getActivity();
        mDBObject = new Plant(mContext);

        mLayout = R.layout.fragment_simple_list;
        mUri = Plants.CONTENT_URI;
        mDelete = R.id.delete_plant;
        mAdd = R.id.add_plant;

        mSingular = "Plant";
        mPlural = "Plants";
        mSortSelection = -1;
        mMenu = R.menu.menu_plants;
        mActionMenu = R.menu.menu_plants_action;
        mSortArray = R.array.plant_sort_items;
        mSpinnerSort = R.id.spinner_plant_menu;
        mActivityFrame = R.id.frame_plant_activity;

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

        View view = inflateView(mLayout, inflater, container);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mBundle.putBoolean(KEY_IS_NEW, true);
        mBundle.remove(KEY_ID);

        if(item.getItemId() == mAdd) {
            replaceFragment();
        }

        return false;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        mBundle.putInt(KEY_POSITION, position);
        mBundle.putLong(KEY_ID, id);
        mBundle.putBoolean(KEY_IS_NEW, false);

        replaceFragment();
    }

    private void replaceFragment() {
        getFragmentManager().beginTransaction()
                .replace(mActivityFrame, PlantNewOverviewFragment.newInstance(mBundle), BACKSTACK_LABEL)
                .addToBackStack(BACKSTACK_LABEL)
                .commit();
    }
}