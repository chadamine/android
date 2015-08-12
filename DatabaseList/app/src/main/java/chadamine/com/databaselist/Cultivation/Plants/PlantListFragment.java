package chadamine.com.databaselist.Cultivation.Plants;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import chadamine.com.databaselist.BaseFragments.SortableListFragment;
import chadamine.com.databaselist.Database.DatabaseSchema.Plants;
import chadamine.com.databaselist.R;

public class PlantListFragment extends SortableListFragment {

    public PlantListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {

        mContext = getActivity();
        mDBObject = new Plant(mContext);

        mLayout = R.layout.fragment_plants;
        mUri = Plants.CONTENT_URI;
        mDelete = R.id.delete_plant;
        mAdd = R.id.add_plant;

        mSingular = "Plant";
        mPlural = "Plants";
        mAddBackStack = "plant_overview";
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
        ((ListView)view.findViewById(R.id.list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListItemClick(position, id);
            }
        });

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

    public void onListItemClick(int position, long id) {

        mBundle.putInt(KEY_POSITION, position);
        mBundle.putLong(KEY_ID, id);
        mBundle.putBoolean(KEY_IS_NEW, false);

        replaceFragment();
    }

    private void replaceFragment() {
        getFragmentManager().beginTransaction()
                .replace(mActivityFrame, PlantNewOverviewFragment.newInstance(mBundle))
                .addToBackStack(mAddBackStack)
                .commit();
    }
}
