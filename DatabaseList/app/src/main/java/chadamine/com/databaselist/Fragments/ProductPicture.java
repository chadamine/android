package chadamine.com.databaselist.Fragments;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import chadamine.com.databaselist.Objects.Product;

/**
 * Created by chadamine on 4/29/2015.
 */
public class ProductPicture extends Product {

    private String mImageName;
    private String mDirImage;


    static final String DATE_FORMAT = "yyyy_MM_Ddd_HH_mm_ss";
    static final String DIR_PRODUCT_IMAGES = "/DatabaseListPictures/Products/";
    static final String IMAGE_EXTENSION_PNG = ".png";

    public ProductPicture(Product product) {
        super(product);

        mDirImage = Environment.getExternalStorageDirectory() + DIR_PRODUCT_IMAGES;
    }
    public String getNewPictureName() {
        String date = new SimpleDateFormat(DATE_FORMAT).format(new Date());

        mImageName =
                this.getName() + "_"
                        + date
                        + IMAGE_EXTENSION_PNG;

        return mImageName;
    }

    public Uri getUri() {
        Uri imageUri = Uri.fromFile(new File(getPictureFolder(), getNewPictureName()));

        return imageUri;
    }

    public String getPictureFolder() {
        return mDirImage;
    }

    public String getCurrentPictureName() {
        return mImageName;
    }
}
