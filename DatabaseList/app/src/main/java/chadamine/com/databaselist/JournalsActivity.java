package chadamine.com.databaselist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import chadamine.com.databaselist.Fragments.JournalsFragment;
import chadamine.com.databaselist.R;

public class JournalsActivity extends ActionBarActivity {

    private final String JOURNALS_FRAGMENT_TAG = "journalsFragmentTag";
    private JournalsFragment mJournalsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journals);

        if(savedInstanceState != null) {
            mJournalsFragment = (JournalsFragment) getSupportFragmentManager()
                    .findFragmentByTag(JOURNALS_FRAGMENT_TAG);
        } else {
            if (mJournalsFragment == null)
                mJournalsFragment = new JournalsFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_journals_activity, mJournalsFragment, JOURNALS_FRAGMENT_TAG)
                    .commit();
        }

        //if(!mJournalsFragment.isInLayout())

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_journals_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
