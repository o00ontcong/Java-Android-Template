package congnguyen.java_android_template.presentation.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import congnguyen.java_android_template.R;
import congnguyen.java_android_template.common.Constants;
import congnguyen.java_android_template.fragment.BaseFragment;


public class HomeFragment extends BaseFragment {


    public static HomeFragment getInstance(String title, boolean backButton) {
        HomeFragment fragment = new HomeFragment();

        Bundle args = new Bundle();
        args.putString( Constants.TITLE_ARGUMENTS, title);
        fragment.setArguments(args);


        return fragment;
    }
    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;

    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}