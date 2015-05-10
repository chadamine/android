package chadamine.com.databaselist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import chadamine.com.databaselist.Fragments.PlantsFragment;

public class PlantsActivity extends ActionBarActivity {

    ViewPager mViewPager;
    String[] mPageData;

    Bundle mBundle;
    String mSortOrder;
    Fragment mContent;
    private final String PLANTS_FRAGMENT_TAG = "plantsFragmentTag";
    PlantsFragment mPlantsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plants);

        //mPageData = TODO: GET FROM DATABASE
        //mViewPager = (ViewPager) findViewById(R.id.frame_plant_activity);
        //mViewPager.setAdapter(new SwipePagerAdapter(this, mPlants, DatabaseContract.Plants.CONTENT_URI));

        if (savedInstanceState != null) {
            mPlantsFragment = (PlantsFragment) getSupportFragmentManager().findFragmentByTag(PLANTS_FRAGMENT_TAG);
        } else if (mPlantsFragment == null) {
            mPlantsFragment = new PlantsFragment();
        }

        if(!mPlantsFragment.isInLayout())
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity, mPlantsFragment, PLANTS_FRAGMENT_TAG).commit();
    }

        @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plants_activity, menu);

        return true;
    }
}
