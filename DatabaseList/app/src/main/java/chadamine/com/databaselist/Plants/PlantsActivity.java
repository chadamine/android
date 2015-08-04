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
    }

    //TODO: FRAGMENT INTERACTION LISTENER TO HIDE/SHOW MENUITEMS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle mBundle = new Bundle();
        mBundle.putBoolean("isNew", true);

        switch (item.getItemId()) {
            case R.id.add_plant:

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_plant_activity,
                                PlantOverviewFragment.newInstance(mBundle), "plant_overview")
                        .addToBackStack("plant_overview")
                        .commit();
                break;
            case R.id.save_plant:
                Plant mPlant = new Plant(this);
                /*if(!mIsNew) {
                    mPlant.setName(
                            ((EditText) mView.findViewById(R.id.edittext_plant_new_name)).getText()
                                    .toString());
                    mPlant.update(mView, mId);
                    //mBundle.putInt("position", mCursor.getCount() - 1);
                }
                else {
                    mPlant.saveFields(mView, false);
                    mBundle.putInt("position", mCursor.getCount());
                }

                mBundle.putBoolean("isNew", false);

                hideKeyboard();
                //mListener.onSwitchToNewFragment(mBundle);
                getActivity().getSupportFragmentManager().popBackStack();*/
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_plants_activity, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Fragment f = mManager.findFragmentByTag("plant_new_fragment");
        if(f != null && f.isVisible()) {
            menu.findItem(R.id.edit_plant).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

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

        //invalidateOptionsMenu();

    }
}


