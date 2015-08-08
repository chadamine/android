package chadamine.com.databaselist.BaseFragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import chadamine.com.databaselist.R;

public class BaseOverviewFragment extends Fragment {

    public @LayoutRes int mLayout;
    public BaseOverviewFragment() {}

    public static BaseOverviewFragment newInstance(Bundle args) {
        BaseOverviewFragment f = new BaseOverviewFragment();
        if(args != null)
            f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflateView(mLayout, inflater, container);
    }

    protected View inflateView(@LayoutRes int resId, LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(resId, container, false);

        FrameLayout layout = (FrameLayout) view.findViewById(R.id.frame_base_list);

        //prepareView();
        //trimFragmentManager();

        return view;
    }
}
