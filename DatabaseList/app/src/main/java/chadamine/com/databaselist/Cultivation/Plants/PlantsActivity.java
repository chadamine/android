package chadamine.com.databaselist.Cultivation.Plants;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;

import chadamine.com.databaselist.Adapters.CustomFragmentPagerAdapter;
import chadamine.com.databaselist.R;

public class PlantsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plants);

        Bundle mBundle = new Bundle();

        CustomFragmentPagerAdapter customFragmentPagerAdapter =
                new CustomFragmentPagerAdapter(getSupportFragmentManager(), this, mBundle);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.pager_sliding_tab_strip);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(customFragmentPagerAdapter);

        tabs.setViewPager(viewPager);

        /*getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity, new PlantListFragment())
                .commit();*/
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
   }
}


