package chadamine.com.databaselist.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import chadamine.com.databaselist.Objects.Photo;

/**
 * Created by chadamine on 5/18/2015.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Photo> mPhotos;
    private List<Integer> mThumbIds;

    public ImageAdapter(Context c) {
        mContext = c;
        mThumbIds = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageURI(mPhotos.get(position).getUri());
        imageView.setImageBitmap(mPhotos.get(position).getThumb());

        return imageView;
    }

    public void addItem(Photo p) {
        mPhotos.add(p);
    }
}
