package chadamine.com.databaselist;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Fragments.PlantNewFragment;
import chadamine.com.databaselist.Fragments.PlantViewFragment;
import chadamine.com.databaselist.Objects.Plant;

public class PlantsActivity extends ActionBarActivity {

    ViewPager mViewPager;
    String[] mPageData;
    List<Object> mPlants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plants);

        mPlants = new ArrayList<>();

        //mPageData = TODO: GET FROM DATABASE
        //mViewPager = (ViewPager) findViewById(R.id.frame_plant_activity);
        //mViewPager.setAdapter(new SwipePagerAdapter(this, getPlants(), DatabaseContract.Plants.CONTENT_URI));

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_plant_activity, new PlantViewFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_plants, menu);

        Spinner spinner = (Spinner) MenuItemCompat.getActionView(menu.findItem(R.id.spinner_plant_menu));
        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.plant_sort_items, android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(mSpinnerAdapter);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.add_plant:
                getSupportFragmentManager().beginTransaction()
                        .addToBackStack("new plant fragment")
                        .add(R.id.frame_plant_activity, new PlantNewFragment()).commit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Object> getPlants() {

        return loadPlants();
    }

    private List<Object> loadPlants() {

        mPlants = new ArrayList<>();
        String[] mKeyArray = DatabaseContract.Plants.KEY_ID_ARRAY;
        Plant plant = new Plant();
        Cursor cursor = getContentResolver().query(
                DatabaseContract.Plants.CONTENT_URI, DatabaseContract.Plants.KEY_ID_ARRAY,
                DatabaseContract.Plants.KEY_ARRAY[0], null, null);
        for(int i = 0; i < cursor.getCount(); i++ ) {
            plant = new Plant();
            plant.setName(cursor.getString(
                            cursor.getColumnIndexOrThrow(DatabaseContract.Plants.KEY_NAME)));
            mPlants.add(i, plant);
            cursor.moveToNext();
        }

        return mPlants;
    }
}
