package chadamine.com.databaselist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import chadamine.com.databaselist.Fragments.PlantNewFragment;
import chadamine.com.databaselist.Fragments.PlantViewFragment;
import chadamine.com.databaselist.Fragments.PlantsFragment;

public class PlantsActivity extends ActionBarActivity {

    private final String PLANTS_FRAGMENT_TAG = "plantsFragmentTag";
    private final String PLANT_NEW_FRAGMENT_TAG = "plantNewFragmentTag";
    private final String PLANT_VIEW_FRAGMENT_TAG = "plantViewFragmentTag";
    private PlantsFragment mPlantsFragment;
    private PlantViewFragment mPlantViewFragment;
    private PlantNewFragment mPlantNewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plants);

        if (savedInstanceState != null) {

            mPlantsFragment = (PlantsFragment) getSupportFragmentManager()
                    .findFragmentByTag(PLANTS_FRAGMENT_TAG);
 /*           mPlantViewFragment = (PlantViewFragment) getSupportFragmentManager()
                    .findFragmentByTag(PLANT_VIEW_FRAGMENT_TAG);
            mPlantNewFragment = (PlantNewFragment) getSupportFragmentManager()
                    .findFragmentByTag(PLANT_NEW_FRAGMENT_TAG);
        } else if (mPlantsFragment == null) {
            mPlantsFragment = new PlantsFragment();
        } else if (mPlantViewFragment == null) {
            mPlantViewFragment = new PlantViewFragment();
        } else if (mPlantNewFragment == null) {
            mPlantNewFragment = new PlantNewFragment();
            */
        }

        if(!mPlantsFragment.isInLayout())
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity, mPlantsFragment,
                        PLANTS_FRAGMENT_TAG).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plants_activity, menu);

        return true;
    }
}
