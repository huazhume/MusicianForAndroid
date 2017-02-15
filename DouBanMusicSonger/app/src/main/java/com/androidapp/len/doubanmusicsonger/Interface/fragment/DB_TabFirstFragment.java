package com.androidapp.len.doubanmusicsonger.Interface.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.androidapp.len.doubanmusicsonger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by len on 16/11/30.
 */

public class DB_TabFirstFragment extends Fragment implements View.OnClickListener {
    // 得到fragmentManager
    private FragmentManager manager;
    // 得到ViewPager
    private ViewPager mPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    // baseViews
    private ImageView tagBottomImageView1;
    private ImageView tagBottomImageView2;

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;

    private Button hotOneBtn;
    private Button hotSongerBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.db_fragment_tabfirst,null);
        //initBaseView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initFragment();
        initmAdapter();
        initBaseView();

    }

    private void initBaseView() {
        // TODO Auto-generated method stub

        WindowManager manager = this.getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        SCREEN_WIDTH = outMetrics.widthPixels;
        SCREEN_HEIGHT = outMetrics.heightPixels;

        // 手动添加 TagImageView
        RelativeLayout layout = (RelativeLayout) this.getView().findViewById(R.id.tab_firstRl);
        tagBottomImageView1 = new ImageView(this.getActivity());
        tagBottomImageView1.setBackgroundResource(R.mipmap.tag_bottomimage);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(20, 14);
        lp.leftMargin = (int) (SCREEN_WIDTH / 2.0) - 80 - 10;
        lp.topMargin = 2 * (40 + 40) - 6;
        layout.addView(tagBottomImageView1, lp);

        tagBottomImageView2 = new ImageView(this.getActivity());
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(20, 14);
        lp2.leftMargin = (int) (SCREEN_WIDTH / 2.0) + 80 - 10;
        lp2.topMargin = 2 * (40 + 40) - 6;
        layout.addView(tagBottomImageView2, lp2);

        // 获取Btn
        hotOneBtn = (Button) this.getView().findViewById(R.id.hotOneBtn);
        hotSongerBtn = (Button) this.getView().findViewById(R.id.hotSongBtn);

        hotOneBtn.setOnClickListener(this);
        hotSongerBtn.setOnClickListener(this);

        // 得到paper
        mPager = (ViewPager) getView().findViewById(R.id.view_pager);
        mPager.setAdapter(mAdapter);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                changeTagsViewBtnProperty(arg0);
                changeTagsViewImageProperty(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i("M_TabFirstFragment", "<<<<<<<<<<<<<<" + "wocao1");
    }

    // initFragment
    public void initFragment() {

        DB_HotOneFragment firstChildFragment = new DB_HotOneFragment();
        DB_HotSongerFragment secondChildFragment = new DB_HotSongerFragment();
        mFragments = new ArrayList<Fragment>();
        // 加入数组
        mFragments.add(firstChildFragment);
        mFragments.add(secondChildFragment);
    }

    // initmAdapter
    public void initmAdapter() {
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                // TODO Auto-generated method stub
                return mFragments.get(arg0);
            }
        };
    }

    // 改变tagBtn的属性
    public void changeTagsViewBtnProperty(int arg0) {
        if (arg0 == 0) {
            hotOneBtn.setTextColor(Color.rgb(0, 0, 0));
            hotSongerBtn.setTextColor(Color.rgb(158, 158, 158));
        } else {

            hotSongerBtn.setTextColor(Color.rgb(0, 0, 0));
            hotOneBtn.setTextColor(Color.rgb(158, 158, 158));
        }

    }

    public void changeTagsViewImageProperty(int arg0) {
        if (arg0 == 0) {
            tagBottomImageView1.setBackgroundResource(R.mipmap.tag_bottomimage);
            tagBottomImageView2.setBackgroundResource(0);
        } else {
            tagBottomImageView1.setBackgroundResource(0);
            tagBottomImageView2.setBackgroundResource(R.mipmap.tag_bottomimage);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        int index = 222;
        switch (v.getId()) {
            case R.id.hotOneBtn:
                index = 0;
                break;
            case R.id.hotSongBtn:
                index = 1;
                break;

            default:
                break;
        }
        if(index < 2){
            mPager.setCurrentItem(index);
            changeTagsViewBtnProperty(index);
            changeTagsViewImageProperty(index);
        }
    }
}
