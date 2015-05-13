package chadamine.com.databaselist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import chadamine.com.databaselist.Fragments.NutrientsFragment;


public class NutrientsActivity extends ActionBarActivity {

    private final String NUTRIENTS_FRAGMENT_TAG = "nutrientsFragmentTag";
    private NutrientsFragment mNutrientsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nutrients);

        if(savedInstanceState != null) {
            mNutrientsFragment = (NutrientsFragment) getSupportFragmentManager()
                    .findFragmentByTag(NUTRIENTS_FRAGMENT_TAG);
        } else if (mNutrientsFragment == null)
            mNutrientsFragment = new NutrientsFragment();

        if(!mNutrientsFragment.isInLayout())
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_nutrient_activity, mNutrientsFragment,
                        NUTRIENTS_FRAGMENT_TAG).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nutrients_activity, menu);
        return true;
    }
}
