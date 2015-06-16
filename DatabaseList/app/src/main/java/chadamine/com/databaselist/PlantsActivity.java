package chadamine.com.databaselist;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import com.astuetz.PagerSlidingTabStrip;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.Fragments.PlantsFragment;

public class PlantsActivity extends ActionBarActivity {

    private final String PLANTS_FRAGMENT_TAG = "plantsFragmentTag";
    private PlantsFragment mPlantsFragment;
    private PagerAdapter mCustomFragmentPagerAdapter;
    private PagerSlidingTabStrip tabs;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plants);

        if (savedInstanceState != null) {
            mPlantsFragment = (PlantsFragment) getSupportFragmentManager()
                    .findFragmentByTag(PLANTS_FRAGMENT_TAG);

        } else {

            if (mPlantsFragment == null) {
                mPlantsFragment = new PlantsFragment();
            }

            /*getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_plant_activity, mPlantsFragment, PLANTS_FRAGMENT_TAG).commit();*/
        }

        mCustomFragmentPagerAdapter = new CustomFragmentPagerAdapter(getSupportFragmentManager(), this);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.pager_sliding_tab_strip);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomFragmentPagerAdapter);

        tabs.setViewPager(mViewPager);
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
}
