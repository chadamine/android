package chadamine.com.databaselist.Plants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import chadamine.com.databaselist.Objects.Plant;
import chadamine.com.databaselist.Plants.PlantOverviewFragment;
import chadamine.com.databaselist.Plants.PlantsFragment;
import chadamine.com.databaselist.R;

public class PlantsActivity extends ActionBarActivity {

    private final String PLANTS_FRAGMENT_TAG = "plantsFragmentTag";
    private PlantOverviewFragment mPlantOverviewFragment;
    private PagerAdapter mCustomFragmentPagerAdapter;
    private FragmentManager mManager;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mManager = getSupportFragmentManager();
        setContentView(R.layout.activity_plants);

        mManager
                .beginTransaction()
                .replace(R.id.frame_plant_activity, new PlantsFragment())
                .commit();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

            }
        });
    }

    //TODO: FRAGMENT INTERACTION LISTENER TO HIDE/SHOW MENUITEMS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // return (true: normal menu processing | false: consume here)
        return false;
    }

    private Menu mMenu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_plants_activity, menu);
        return false;
    }

    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment f = mManager.findFragmentByTag("plant_new_fragment");
        if(f != null && f.isVisible()) {
            menu.findItem(R.id.edit_plant).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        PlantOverviewFragment f =
                (PlantOverviewFragment) mManager.findFragmentByTag("plant_overview");
        if(f != null && f.isVisible())
            f.backPressed();
        else
            finish();
    }
}


