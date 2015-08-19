package chadamine.com.databaselist.Cultivation.Plants;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.List;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.R;

public class PlantOverviewFragment extends Fragment {

    private Context mContext;
    private View mView;
    private Bundle mBundle;
    private CustomFragmentPagerAdapter mCustomFragmentPagerAdapter;
    private ViewPager mViewPager;

    public static PlantOverviewFragment newInstance(Bundle args) {
        PlantOverviewFragment f = new PlantOverviewFragment();

        if(args != null)
            f.setArguments(args);

        return f;
    }

    public PlantOverviewFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();

        mView = inflater.inflate(R.layout.fragment_plant_overview, container, false);

        if(getArguments() != null) {
            mBundle = getArguments();
        }

        if(savedInstanceState != null) {
            mBundle = savedInstanceState;
        }

        mBundle.putString("type", "plant");
        mCustomFragmentPagerAdapter =
                new CustomFragmentPagerAdapter(getFragmentManager(), mContext, mBundle);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) mView.findViewById(R.id.pager_sliding_tab_strip);

        mViewPager = (ViewPager) mView.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomFragmentPagerAdapter);

        tabs.setViewPager(mViewPager);

        return mView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    MenuItem mMenuItem;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_plants_activity, menu);

        mMenuItem = menu.findItem(R.id.edit_plant);

    }

    public void backPressed() {
        if(mViewPager.getCurrentItem() == 0) {
            if (mCustomFragmentPagerAdapter.getItem(0) instanceof PlantNewFragment)
                ((PlantNewFragment) mCustomFragmentPagerAdapter.getItem(0)).backPressed();
            else
                getFragmentManager().popBackStack();
        } else
            mViewPager.setCurrentItem(0);
    }
}
