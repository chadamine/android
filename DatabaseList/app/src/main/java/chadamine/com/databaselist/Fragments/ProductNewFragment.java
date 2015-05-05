package chadamine.com.databaselist.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.TintEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Database.DatabaseContract;
import chadamine.com.databaselist.Objects.Product;
import chadamine.com.databaselist.Objects.Photo;
import chadamine.com.databaselist.R;

/**
 * Created by chadamine on 4/10/2015.
 */
public class ProductNewFragment extends Fragment {

    private Product mProduct;
    private View mView;
    private List<View> mFields;
    private EditText mEditTextName;
    private Photo mPhoto;
    private List<Photo> mPhotos;
    private Context mContext;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public ProductNewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_product_new, container, false);
        mProduct = new Product(mContext);
        mFields = new ArrayList<>();
        mPhotos = new ArrayList<>();
        mContext = getActivity();


        mEditTextName = (EditText) mView.findViewById(R.id.edittext_newproduct_name);
        mFields.add(mEditTextName);

        Button btnSave = (Button) mView.findViewById(R.id.button_newproduct_save);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //mProduct.setName(mEditTextName.getText().toString());
                //saveFields();

                createProduct();
                //saveToDB();

                if(mProduct.hasName()) {
                    savePictures();
                    saveFields();
                    dbInsertFields();
                    getFragmentManager().popBackStack();
                }
                else
                    Toast.makeText(mContext,
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
                    Toast.makeText(mContext,
                            "Product must have a name to take a photo", Toast.LENGTH_LONG).show();
            }
        });

        return mView;
    }
    private void createProduct() {

        mProduct = new Product(mContext);
        mProduct.setName((mEditTextName).getText().toString());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Toast.makeText(mContext,
                "Product image saved as " + mProduct.getName()
                    + "\nin the following folder: " + mPhoto.getPhotoFolder(),
                Toast.LENGTH_LONG).show();
        // TODO: CLOSE KEYBOARD
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

        if(takePictureIntent.resolveActivity(mContext.getPackageManager()) != null) {
            mPhoto = new Photo(mContext, mProduct);
            mPhotos.add(mPhoto);

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhoto.getUri());
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void savePictures() {

        // TODO: check for mProductName changed,
        // TODO: adjust names of pictures in list as needed before save

        mProduct.setName(mEditTextName.getText().toString());

        for(Photo picture : mPhotos) {
            if(picture.getName() != mProduct.getName()) {
                // Rename picture file
                Toast.makeText(mContext, "names not equal", Toast.LENGTH_LONG).show();
                File photo = new File(picture.getPhotoFolder(), picture.getCurrentPhotoFullName());
                Toast.makeText(mContext, "current photo full name: "
                        + picture.getCurrentPhotoFullName(), Toast.LENGTH_LONG).show();

                if(photo != null) {
                    photo.renameTo(new File(picture.getPhotoFolder(), mProduct.getName()));
                    Toast.makeText(mContext, "photo not null", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(mContext, "photo not renamed", Toast.LENGTH_SHORT).show();
                }

                picture.setName(mProduct.getName());
                picture.dbInsertPictures(mContext);
            } else {
                picture.dbInsertPictures(mContext);
              }
        }
    }

    private void dbInsertFields() {
            mContext.getContentResolver()
                .insert(DatabaseContract.Products.CONTENT_URI, mProduct.getValues());
    }

    private void saveFields() {
        //mFields = new ArrayList<>();
        //mFields.add(mEditTextName);
        String name = mEditTextName.getText().toString();

        // FIXME: BETTER CHECKING FOR NAME SETTERS AND GETTERS
        if(name == null)
            name = "";
        try {
            mProduct.setName(name);
            mPhoto.setName(name);
        }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
    }
}

