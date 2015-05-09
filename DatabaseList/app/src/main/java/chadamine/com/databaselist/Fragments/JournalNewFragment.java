package chadamine.com.databaselist.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_journal_new, container, false);
        mValues = new ContentValues();
        mContext = getActivity();

        setUpButton();

        return mView;
    }

    private void setUpButton() {

        Button btnSave = (Button) mView.findViewById(R.id.button_newjournal_save);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                createJournal();

                mValues.put(DatabaseSchema.Products.KEY_NAME,
                        ((EditText) mView.findViewById(R.id.edittext_newjournal_name))
                                .getText().toString());

                getActivity().getContentResolver().insert(mJournal.getUri(), mJournal.getValues());
                getFragmentManager().popBackStack();
            }
        });
    }

    private void createJournal() {
        mJournal = new Journal(mContext);
        mJournal.setName(
                ((EditText) mView.findViewById(R.id.edittext_newjournal_name))
                        .getText().toString());
    }
}
