package chadamine.com.databaselist.Journals;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import chadamine.com.databaselist.Cultivation.Plants.PlantNewFragment;
import chadamine.com.databaselist.Database.DatabaseSchema.Journals;
import chadamine.com.databaselist.R;

public class JournalViewFragment extends android.support.v4.app.Fragment {

    private static View mView;
    private static Context mContext;
    private Journal mJournal;
    private Bundle mBundle;
    private String mSortOrder;
    private int mCursorPosition;
    private List<View> mFields;
    private Cursor mCursor;
    
    public JournalViewFragment() {}

    public static JournalViewFragment newInstance(Bundle args) {
        JournalViewFragment f = new JournalViewFragment();
        if(args != null)
            f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            return;
        }
        
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_journal_view, container, false);
        mContext = getActivity();
        mJournal = new Journal(mContext);

        if(getArguments() != null) {
            mBundle = getArguments();

            if(mBundle.containsKey("sortOrder"))
                mSortOrder = mBundle.getString("sortOrder");

            if(mBundle.containsKey("position"))
                mCursorPosition = mBundle.getInt("position");

            setViewItemContent();
        }

        return mView;
    }

    private void setViewItemContent() {
        // set up view content from Journal object

        mCursor = mJournal.getNewCursor(mJournal.getKeyIdArray(), null, null, mSortOrder);
        mCursor.moveToPosition(mCursorPosition);

        String name = mCursor.getString(mCursor.getColumnIndexOrThrow(Journals.KEY_NAME));
        /*((TextView) mView.findViewById(R.id.textview_journal_view_info_name))
                .setText(name == null ? "" : name);*/

        mCursor.moveToNext();
    }
    
    /*private void clearFields() {
        mFields = new ArrayList<>();

        mFields.add(mView.findViewById(R.id.textview_journal_view_info_name));
        mFields.add(mView.findViewById(R.id.textview_journal_view_info_species));
        mFields.add(mView.findViewById(R.id.textview_journal_view_info_cultivar));
        mFields.add(mView.findViewById(R.id.textview_journal_view_info_stage));
        mFields.add(mView.findViewById(R.id.textview_journal_view_info_age));

        for(View view : mFields)
            ((TextView) view).setText("");
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_journal_view, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.edit_journal:
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_journals_activity, PlantNewFragment.newInstance(mBundle))
                        .addToBackStack("newJournal")
                        .commit();
        }

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState = mBundle;
    }

}
