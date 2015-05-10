package chadamine.com.databaselist.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
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
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Database.DatabaseSchema;
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
    private static final int WRITE_REQUEST_CODE = 10;


    public ProductNewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        mView = inflater.inflate(R.layout.fragment_product_new, container, false);
        mProduct = new Product(mContext);
        mFields = new ArrayList<>();
        mPhotos = new ArrayList<>();

        mEditTextName = (EditText) mView.findViewById(R.id.edittext_newproduct_name);
        mFields.add(mEditTextName);

        setSaveButton();
        setPictureButton();

        return mView;
    }

    private void setSaveButton() {
        Button btnSave = (Button) mView.findViewById(R.id.button_newproduct_save);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //mProduct.setName((mEditTextName).getText().toString());
                if (mProduct.hasName()) {
                    savePictures();
                    saveFields();
                    dbInsertFields();
                    getFragmentManager().popBackStack();
                } else
                    Toast.makeText(mContext,
                            "Product must have a name to save", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setPictureButton() {
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

        if (requestCode == WRITE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                ParcelFileDescriptor parcelFileDescriptor = null;

                try {
                    parcelFileDescriptor = mContext.getContentResolver()
                            .openFileDescriptor(uri, "w");
                } catch (FileNotFoundException e) {
                    Toast.makeText(mContext, "file not found", Toast.LENGTH_SHORT).show();
                }
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                File photoFile = new File(uri.getPath());

                try {
                    parcelFileDescriptor.close();
                } catch (IOException e) {
                    Toast.makeText(mContext, "could not open file", Toast.LENGTH_SHORT).show();
                }

                for(Photo photo : mPhotos) {
                    photoFile.renameTo(new File(photo.getPhotoFolder() + mProduct.getPhotoDir(),
                            mPhoto.getCurrentPhotoFullName()));

                    photo.setName(mProduct.getName());

                    photoFile.renameTo(new File(photo.getPhotoFolder() + mProduct.getPhotoDir(),
                            mPhoto.getCurrentPhotoFullName()));

                    photo.dbInsertPictures(mContext);

                }
            }
        }
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

        Intent intent = new Intent(Intent.ACTION_EDIT);
        //intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, WRITE_REQUEST_CODE);




/*
        for(Photo photo : mPhotos) {
            Toast.makeText(mContext,
                    "\nphoto getName: " + photo.getName()
                    +"\nproduct.getname: " + mProduct.getName(), Toast.LENGTH_LONG).show();
            //if(photo.getName() != mProduct.getName()) {
                // Rename photo file

                File photoFile = new File(photo.getPhotoFolder() + mProduct.getPhotoDir(),
                        photo.getCurrentPhotoFullName());
            photoFile.setWritable(true);
                Toast.makeText(mContext,
                        "current photo full name: " + photo.getCurrentPhotoFullName()
                        + "\ncurrent photo path: " + photo.getPhotoFolder() + mProduct.getPhotoDir()
                        + "\nphotoFile path: " + photoFile.getAbsolutePath()
                        + "\nphotoFile writability: " + photoFile.canWrite(),
                        Toast.LENGTH_LONG).show();

            if (photo != null) {
                photo.setName(mProduct.getName());

                photoFile.renameTo(new File(photo.getPhotoFolder() + mProduct.getPhotoDir(),
                        mPhoto.getCurrentPhotoFullName()));
                    Toast.makeText(mContext,
                            "photo new name: " + new File(photo.getPhotoFolder()
                                    + mProduct.getPhotoDir(), mPhoto.getCurrentPhotoFullName()).getAbsolutePath(),
                            Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(mContext, "photo not renamed", Toast.LENGTH_SHORT).show();
                }

                //photo.dbInsertPictures(mContext);

            */
/*} else {
                //photo.dbInsertPictures(mContext);
            }*//*


            photo.dbInsertPictures(mContext);
*/

        //}
    }

    private void dbInsertFields() {
        mProduct.setName((mEditTextName).getText().toString());
        mContext.getContentResolver()
                .insert(DatabaseSchema.Products.CONTENT_URI, mProduct.getValues());
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
        }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
    }
}

