package chadamine.com.databaselist.Cultivation.Plants;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chadamine.com.databaselist.BaseFragments.BaseListFragment;
import chadamine.com.databaselist.BaseFragments.BaseOverviewFragment;
import chadamine.com.databaselist.Database.DatabaseSchema.Plants;
import chadamine.com.databaselist.R;

public class PlantListFragment extends BaseListFragment {

    public PlantListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        mContext = getActivity();
        mDBObject = new Plant(mContext);

        mLayout = R.layout.fragment_plants;
        mUri = Plants.CONTENT_URI;
        mDelete = R.id.delete_plant;
        mSortSelection = -1;
        mMenu = R.menu.menu_plants;
        mActionMenu = R.menu.menu_plants_action;
        mSortArray = R.array.plant_sort_items;
        mSpinnerSort = R.id.spinner_plant_menu;

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

        mBaseFragment = BaseOverviewFragment.newInstance(mBundle);

        View view = inflateView(mLayout, inflater, container);
        return view;
    }
}
