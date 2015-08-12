package chadamine.com.databaselist.Cultivation.Plants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.BaseFragments.BaseOverviewFragment;
import chadamine.com.databaselist.R;

public class PlantNewOverviewFragment extends BaseOverviewFragment {

    public PlantNewOverviewFragment() {
    }

    public static PlantNewOverviewFragment newInstance(Bundle args) {
        PlantNewOverviewFragment f = new PlantNewOverviewFragment();
        if(args != null)
            f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if(getArguments() != null)
            mBundle = getArguments();

        else if(savedInstanceState != null)
            mBundle = savedInstanceState;
        else
            mBundle = new Bundle();

        mContext = getActivity();
        mMenu = R.menu.menu_plants_activity;
        mEdit = R.id.edit_plant;
        mNumTabs = 3;

        List<String> titles = new ArrayList<>();
        titles.add("Information");
        titles.add("Measurements");
        titles.add("History");

        mTabTitles = titles;

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflateView(R.layout.fragment_plant_overview, inflater, container, mBundle);
    }
}
