package chadamine.com.databaselist.Fragments;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/12/2015.
 */
public class NewJournalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View thisView = inflater.inflate(R.layout.fragment_new_journal, container, false);
        final ContentValues values = new ContentValues();

        Button btnSave = (Button) thisView.findViewById(R.id.button_newjournal_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                values.put(DatabaseContract.Products.KEY_NAME, ((EditText) thisView.findViewById(R.id.edittext_newjournal_name)).getText().toString());

                getActivity().getContentResolver().insert(DatabaseContract.Journals.CONTENT_URI, values);
                getFragmentManager().popBackStack();
            }
        });

        return thisView;
    }
}
