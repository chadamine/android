package chadamine.com.databaselist.Fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.TintEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Objects.Product;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/10/2015.
 */
public class NewProductFragment extends Fragment {

    private Product mProduct;
    private View mView;
    private List<View> mFields;
    private EditText mEditTextName;
    private ProductPicture mProductPicture;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public NewProductFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_new_product, container, false);
        mProduct = new Product();
        mFields = new ArrayList<>();
        mProductPicture = new ProductPicture(mProduct);

        mEditTextName = (EditText) mView.findViewById(R.id.edittext_newproduct_name);
        mFields.add(mEditTextName);

        Button btnSave = (Button) mView.findViewById(R.id.button_newproduct_save);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mProduct.hasName()) {
                    getActivity().getContentResolver()
                            .insert(DatabaseContract.Products.CONTENT_URI, mProduct.getValues());
                    getFragmentManager().popBackStack();
                }
                else
                    Toast.makeText(getActivity(),
                            "Product must have a name to save", Toast.LENGTH_LONG).show();
            }
        });

        (mView.findViewById(R.id.button_takepicture)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mProduct.setName(mEditTextName.getText().toString());

                if (mProduct.hasName()) {
                    startCamera();
                } else
                    Toast.makeText(getActivity(),
                            "Product must have a name to take a photo", Toast.LENGTH_LONG).show();
            }
        });

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(getActivity(),
                "Product image saved as " + mProduct.getName()
                    + "\nin the following folder: " + mProductPicture.getPictureFolder(),
                Toast.LENGTH_LONG).show();
    }

    private void clearFields() {

        View view = null;
        for(int i = 0; i < mFields.size(); i++)  {
            view = mFields.get(i);
            if(view.getClass().getName() == TintEditText.class.getName().toString())
                ((EditText) view).setText("");
            //TODO: if(Spinner) choose option: 0

            toggleFocusable(view);
        }
    }

    private void toggleFocusable(View view) {
        if(view.isFocused()) {
            view.setFocusable(false);
            view.setFocusable(true);
        }
    }

    private void startCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mProductPicture.getUri());
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }
}

