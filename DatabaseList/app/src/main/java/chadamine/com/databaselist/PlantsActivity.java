package chadamine.com.databaselist;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;

import com.astuetz.PagerSlidingTabStrip;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.Fragments.PlantOverviewFragment;
import chadamine.com.databaselist.Fragments.PlantsFragment;

public class PlantsActivity extends ActionBarActivity {

    private final String PLANTS_FRAGMENT_TAG = "plantsFragmentTag";
    private PlantOverviewFragment mPlantOverviewFragment;
    private PagerAdapter mCustomFragmentPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plants);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity, new PlantsFragment())
                .commit();

        /*if (savedInstanceState != null) {
            mPlantOverviewFragment = (PlantOverviewFragment) getSupportFragmentManager()
                    .findFragmentByTag(PLANTS_FRAGMENT_TAG);

        } else {

            if (mPlantOverviewFragment == null) {
                mPlantOverviewFragment = new PlantOverviewFragment();
            }*/


        //}


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plants_activity, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    //mCustomFragmentPagerAdapter.FirstPageListener mListener = new FirstPageListener();
    /*getSupportFragmentManager().beginTransaction()
            .replace(R.id.frame_plant_activity, new PlantsFragment())
            .commit();*/
   }
}


