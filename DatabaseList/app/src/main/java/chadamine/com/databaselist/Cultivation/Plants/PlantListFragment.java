package chadamine.com.databaselist.Cultivation.Plants;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chadamine.com.databaselist.BaseFragments.BaseListFragment;
import chadamine.com.databaselist.BaseFragments.BaseOverviewFragment;
import chadamine.com.databaselist.R;

public class PlantListFragment extends BaseListFragment {

    public PlantListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflateView(mLayout, inflater, container);

        mContext = getActivity();
        mLayout = R.layout.fragment_plants;
        mDelete = R.id.delete_plant;
        mSortSelection = -1;
        mMenu = R.menu.menu_plants;
        mActionMenu = R.menu.menu_plants_action;
        mSortArray = R.array.plant_sort_items;
        mSpinnerSort = R.id.spinner_plant_menu;
        mBaseFragment = BaseOverviewFragment.newInstance(mBundle);

        return view;
    }


}
