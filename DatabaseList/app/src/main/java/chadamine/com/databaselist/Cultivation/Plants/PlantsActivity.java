package chadamine.com.databaselist.Cultivation.Plants;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.LinearLayout.LayoutParams;

import com.astuetz.PagerSlidingTabStrip;

import chadamine.com.databaselist.R;

public class PlantsActivity extends AppCompatActivity {

    PagerSlidingTabStrip mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_plants);

        Bundle mBundle = new Bundle();
        mBundle.putString("type", "plant");

        /*Uri myUri = DatabaseSchema.Plants.CONTENT_URI;
        String myUriStr = myUri.toString();
        Uri transformed = Uri.parse(myUriStr);
        if(transformed.equals(myUri))
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, myUriStr + " vs " + transformed.toString(), Toast.LENGTH_SHORT).show();*/

/*        CustomFragmentPagerAdapter customFragmentPagerAdapter =
                new CustomFragmentPagerAdapter(getSupportFragmentManager(), this, mBundle, new TabListener());

        mTabs = (PagerSlidingTabStrip) findViewById(R.id.pager_sliding_tab_strip);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(customFragmentPagerAdapter);

        mTabs.setViewPager(viewPager);

        viewPager.setEnabled(false);

        TabListener listener = new TabListener();
        listener.toggleTabs();*/
    }

    public interface PlantTabListener {
        void toggleTabs();
    }

    private final class TabListener implements PlantTabListener {

        @Override
        public void toggleTabs() {

            int height = mTabs.getLayoutParams().height;
            if (height > 0)
                height = 0;
            else
                height = 96;

            LayoutParams tabLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, height);

            mTabs.setLayoutParams(tabLayoutParams);
        }
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


