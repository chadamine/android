package chadamine.com.databaselist;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Fragments.PlantNewFragment;
import chadamine.com.databaselist.Fragments.PlantsFragment;
import chadamine.com.databaselist.Objects.Plant;

public class PlantsActivity extends ActionBarActivity {

    ViewPager mViewPager;
    String[] mPageData;
    List<Object> mPlants;
    Bundle mBundle;
    String mSortOrder;
    Fragment mContent;
    Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_plants);
        View view = getLayoutInflater().inflate(R.layout.fragment_plants, null);
        setContentView(R.layout.activity_plants);

        //loadPlants();

        //mPageData = TODO: GET FROM DATABASE
        //mViewPager = (ViewPager) findViewById(R.id.frame_plant_activity);
        //mViewPager.setAdapter(new SwipePagerAdapter(this, mPlants, DatabaseContract.Plants.CONTENT_URI));

        if (savedInstanceState != null) {
            mBundle = savedInstanceState;
            mSortOrder = savedInstanceState.getString("sort_order");
        }

        /*mContent = getSupportFragmentManager().getFragment(
                savedInstanceState, "mContent");*/

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_plant_activity, new PlantsFragment()).commit();
    }

    /**
     * Save all appropriate fragment state.
     *
     * @param outState
     */
        @Override
    protected void onSaveInstanceState(Bundle outState) {
            outState.putString("sort_order", mSortOrder);
        super.onSaveInstanceState(outState);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //mCurrentFragment = getFragmentManager().findFragmentById(R.layout.fragment_plant_view);

        /*if(mCurrentFragment != null) {
            Toast.makeText(this, mCurrentFragment.getId() + " " + R.layout.fragment_plant_view + " " + mSortOrder,
                    Toast.LENGTH_LONG).show();
            if(mCurrentFragment instanceof PlantViewFragment) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_plant_activity, PlantsFragment.newInstance(mSortOrder)).commit();
            }
        }*/
    }

    /**
     * Called when a fragment is attached to the activity.
     *
     * @param fragment
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        mCurrentFragment = fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plants_options, menu);

        return true;
    }


    private void loadPlants() {

        mPlants = new ArrayList<>();
        String[] mKeyArray = DatabaseSchema.Plants.KEY_ID_ARRAY;
        Plant plant = new Plant();
        Cursor cursor = getContentResolver().query(
                DatabaseSchema.Plants.CONTENT_URI, DatabaseSchema.Plants.KEY_ID_ARRAY,
                DatabaseSchema.Plants.KEY_ARRAY[0], null, null);
        for(int i = 0; i < cursor.getCount(); i++ ) {
            plant = new Plant();
            plant.setName(cursor.getString(
                            cursor.getColumnIndexOrThrow(DatabaseSchema.Plants.KEY_NAME)));
            mPlants.add(i, plant);
            cursor.moveToNext();
        }

        //return mPlants;
    }
}
