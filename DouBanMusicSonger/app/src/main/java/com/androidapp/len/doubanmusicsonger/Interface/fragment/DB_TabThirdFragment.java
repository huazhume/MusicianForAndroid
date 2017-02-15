package com.androidapp.len.doubanmusicsonger.Interface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.androidapp.len.doubanmusicsonger.Adapter.DB_TabThirdItemAdapter;
import com.androidapp.len.doubanmusicsonger.Interface.activity.DB_LoginActivity;
import com.androidapp.len.doubanmusicsonger.R;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by len on 16/11/30.
 */

public class DB_TabThirdFragment extends Fragment implements View.OnClickListener {
    private ListView itemListView;
    private List<Map<String, String>> listArr;
    private GridView gridView;
    private UMAuthListener umAutListener;
    private Boolean isLogin = false;


    private Button qqBtn;
    private Button loginBtn;
    private EditText usernameET;
    private EditText passwordET;

    private UMShareAPI mShareAPI;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), DB_LoginActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //  initBaceViews(view);
        WindowManager manager = this.getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        View view = null;
        if (isLogin) {
            view = inflater.inflate(R.layout.db_fragment_tabthird, null);
            initBaceViews(view);
        } else {
            view = inflater.inflate(R.layout.db_activity_login, null);
        }
        view.setLayoutParams(new RelativeLayout.LayoutParams(outMetrics.widthPixels, outMetrics.heightPixels));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isLogin) {
            configLoginsuccess();
        } else {
            configNoLogin();
        }
    }

    //尚未登录
    private void configNoLogin() {
        qqBtn = (Button) this.getView().findViewById(R.id.logon_qq);
        loginBtn = (Button) this.getView().findViewById(R.id.loginBtn);
        usernameET = (EditText) this.getView().findViewById(R.id.usernameText);
        passwordET = (EditText) this.getView().findViewById(R.id.passwordText);
        qqBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    //登录成功之后
    private void configLoginsuccess() {
        loadData();
        initAdapter();
    }

    private void initAdapter() {
        DB_TabThirdItemAdapter adapter = new DB_TabThirdItemAdapter(this.getActivity(), listArr);
        itemListView.setAdapter(adapter);
    }

    private void loadData() {
        listArr = new ArrayList<>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("title", "简介");
        map1.put("info", "");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("title", "认证");
        map2.put("info", "");

        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("title", "更多");
        map3.put("info", "基本信息");
        listArr.add(map1);
        listArr.add(map2);
        listArr.add(map3);
    }

    private void initBaceViews(View view) {
        itemListView = (ListView) view.findViewById(R.id.tab_thirditemlistview);
        // gridView = (GridView) view.findViewById(R.id.tab_thirdgridview);
    }


    @Override
    public void onClick(View view) {

        Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<点击啦");

        switch (view.getId()) {
            case R.id.loginBtn:
                login();
                break;
            case R.id.logon_qq:
                threepartlogin();
                break;
        }

    }

    private void login() {
        Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<点击啦1" + usernameET.getText() + "word" + passwordET.getText());

        if (usernameET.getText().equals("xiaojiayu") && passwordET.getText().equals("123456")) {
            isLogin = true;
            Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<点击啦2");
            // 创建管理者
            FragmentManager fragmentManager = this.getActivity().getSupportFragmentManager();
            // 初始化对象
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack("thirdFragment");
            transaction.commit();

        } else {
            Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<点击啦3");
            Toast toast = Toast.makeText(this.getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 350);
            toast.show();
        }
    }

    private void threepartlogin() {
        mShareAPI = UMShareAPI.get(this.getActivity());
        Config.DEBUG = true;
        Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<map1");
//        mShareAPI.doOauthVerify(this.getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
//            @Override
//            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//                Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<map2" + map + share_media);
//               // getUserinfo();
//
//            }
//
//            @Override
//            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//                Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<map3");
//            }
//
//            @Override
//            public void onCancel(SHARE_MEDIA share_media, int i) {
//                Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<map4");
//            }
//        });
        getUserinfo();
    }

    private void getUserinfo() {

        mShareAPI.getPlatformInfo(this.getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.i("DB_TabThirdFragment", "<<<<<<<<<<<<<<map22" + map + share_media);
                loginin();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    private void loginin() {
        WindowManager manager = this.getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.db_fragment_tabthird, null);
        // view.setLayoutParams(new RelativeLayout.LayoutParams(outMetrics.widthPixels, outMetrics.heightPixels));
//        RelativeLayout.LayoutParams ls = new RelativeLayout.LayoutParams(outMetrics.widthPixels, outMetrics.heightPixels);
//        LinearLayout ll = (LinearLayout) this.getView().findViewById(R.id.login_ll);
//        ll.addView(ll,ls);
    }

}
