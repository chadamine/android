package chadamine.com.databaselist;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by chadamine on 4/10/2015.
 */
public class NewProductFragment extends Fragment {

    private ContentValues values;
    DatabaseContract.Products products;
    View thisView;

    public NewProductFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        thisView = inflater.inflate(R.layout.fragment_newproduct, container, false);
        values = new ContentValues();

        Button btnSave = (Button) thisView.findViewById(R.id.button_newproduct_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                putValues();
                getActivity().getContentResolver().insert(products.CONTENT_URI, values);
                getFragmentManager().popBackStack();

            }
        });

        return thisView;
    }

    private void putValues() {
        values.put(products.KEY_NAME, ((EditText) thisView.findViewById(R.id.edittext_newproduct_name)).getText().toString());
        // values.put(products.KEY_TYPE, ((EditText) thisView.findViewById(R.id.edittext_newproduct_type)).getText().toString());
        // values.put(products.KEY_PRODUCTLINE, ((EditText) thisView.findViewById(R.id.edittext_newproduct_line)).getText().toString());
    }
}

