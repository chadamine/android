package chadamine.com.databaselist.Journals;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import chadamine.com.databaselist.Database.DatabaseSchema;
import chadamine.com.databaselist.Objects.Journal;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/12/2015.
 */
public class JournalNewFragment extends Fragment {

    private View mView;
    private ContentValues mValues;
    private Journal mJournal;
    private Context mContext;
    private Bundle mBundle;

    public static JournalNewFragment newInstance(Bundle args) {
        JournalNewFragment f = new JournalNewFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_journal_new, container, false);
        mValues = new ContentValues();
        mContext = getActivity();

        // restore previous state (i.e. on screen rotation)
        if(savedInstanceState != null) {
            mBundle = savedInstanceState;
        }

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
            //mSortOrder = mBundle.getString("sortOrder");
            //mSortSelection = mBundle.getInt("sortSelection");
        } else
            mBundle = new Bundle();
    }

    private void saveData() {

        createJournal();

        mValues.put(DatabaseSchema.Products.KEY_NAME,
                ((EditText) mView.findViewById(R.id.edittext_newjournal_name))
                        .getText().toString());

        getActivity().getContentResolver().insert(mJournal.getUri(), mJournal.getValues());
        getFragmentManager().popBackStack();
    }

    private void createJournal() {
        mJournal = new Journal(mContext);
        mJournal.setName(
                ((EditText) mView.findViewById(R.id.edittext_newjournal_name))
                        .getText().toString());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_journal_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.save_journal:
                saveData();
                getFragmentManager().popBackStack();
                hideKeyboard();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideKeyboard() {
        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(mView.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
