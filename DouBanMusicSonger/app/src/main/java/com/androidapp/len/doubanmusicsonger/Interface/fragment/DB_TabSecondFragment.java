package com.androidapp.len.doubanmusicsonger.Interface.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.androidapp.len.doubanmusicsonger.Adapter.DB_TabSecondAdapter;
import com.androidapp.len.doubanmusicsonger.Interface.activity.DB_SearchResultActivity;
import com.androidapp.len.doubanmusicsonger.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by len on 16/11/30.
 */

public class DB_TabSecondFragment extends Fragment implements View.OnClickListener {
    @Nullable
    private ExpandableListView expanListView;
    // adapter
    private DB_TabSecondAdapter expanAdapter;

    private Button searchBtn;
    private EditText serchEdit;

    private List<Map<String, Object>> listArr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        return inflater.inflate(R.layout.db_fragment_tabsecond, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        loadData();
        initBaceViews();
    }

    private void loadData() {
        // TODO Auto-generated method stub
        listArr = new ArrayList<Map<String, Object>>();
        // 第一个
        List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
        Map<String, String> map1_1 = new HashMap<String, String>();
        map1_1.put("name", "摇滚");
        map1_1.put("number", "19");
        list1.add(map1_1);

        Map<String, String> map2_1 = new HashMap<String, String>();
        map2_1.put("name", "民摇");
        map2_1.put("number", "18");
        list1.add(map2_1);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "流派");
        map.put("data", list1);

        listArr.add(map);

        loadData2();
    }

    private void loadData2() {
        // 第一个
        List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
        Map<String, String> map1_1 = new HashMap<String, String>();
        map1_1.put("name", "内地巅峰榜");
        map1_1.put("number", "5");
        list1.add(map1_1);
        // 第二个
        Map<String, String> map2_1 = new HashMap<String, String>();
        map2_1.put("name", "港台巅峰榜");
        map2_1.put("number", "6");
        list1.add(map2_1);

        Map<String, String> map3_1 = new HashMap<String, String>();
        map3_1.put("name", "欧美巅峰榜");
        map3_1.put("number", "3");
        list1.add(map3_1);

        Map<String, String> map4_1 = new HashMap<String, String>();
        map4_1.put("name", "韩国巅峰榜");
        map4_1.put("number", "16");
        list1.add(map4_1);

        Map<String, String> map5_1 = new HashMap<String, String>();
        map5_1.put("name", "日本巅峰榜");
        map5_1.put("number", "17");
        list1.add(map5_1);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "地区");
        map.put("data", list1);

        listArr.add(map);

        loadData3();
    }

    private void loadData3() {

        List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
        Map<String, String> map1_1 = new HashMap<String, String>();
        map1_1.put("name", "美国Billboard单曲榜");
        map1_1.put("number", "108");
        list1.add(map1_1);
        // 第二个
        Map<String, String> map2_1 = new HashMap<String, String>();
        map2_1.put("name", "英国uk单曲榜");
        map2_1.put("number", "107");
        list1.add(map2_1);

        Map<String, String> map3_1 = new HashMap<String, String>();
        map3_1.put("name", "itunes单曲榜");
        map3_1.put("number", "123");
        list1.add(map3_1);

        Map<String, String> map4_1 = new HashMap<String, String>();
        map4_1.put("name", "香港单曲榜");
        map4_1.put("number", "113");
        list1.add(map4_1);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "单曲榜");
        map.put("data", list1);

        listArr.add(map);
    }

    // 配置视图
    private void initBaceViews() {
        // TODO Auto-generated method stub
        expanListView = (ExpandableListView) this.getView().findViewById(R.id.tab_sec_expandaLV);
        expanAdapter = new DB_TabSecondAdapter(this.getActivity(), listArr);
        expanListView.setAdapter(expanAdapter);
        expanListView.setGroupIndicator(null);
        //展开
        int groupCount = expanListView.getCount();
        for (int i = 0; i < groupCount; i++) {
            expanListView.expandGroup(i);
        }
        ;
        //不能够点击
        expanListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });

        //btn
        searchBtn = (Button) this.getView().findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);
        serchEdit = (EditText) this.getView().findViewById(R.id.searchText);

        //设置点击行
        expanListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Log.i("DB_TabSecondFragment", "<<<<<<<<" + i + "   " + i1 + "   " + l);
                Map<String, Object>map =  listArr.get(i);
                List<Map<String,String>> list = (List<Map<String,String>>) map.get("data");
                String number = list.get(i1).get("number");
                String name = list.get(i1).get("name");
                Log.i("DB_TabSecondFragment", "<<<<<<<<" +number);
                inSearchResultActivity(number,name);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchBtn:
                searchBtnClicked();
                break;
        }
    }

    private void searchBtnClicked() {

        Log.i("DB_HotOneFregment", "<<<<<<<keyword");
        if (!serchEdit.getText().toString().equals("") && serchEdit.getText() != null) {

//            InputMethodManager imm = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            Intent intent = new Intent();
            intent.setClass(this.getActivity(), DB_SearchResultActivity.class);
            intent.putExtra("keyword", serchEdit.getText().toString());
            intent.putExtra("type",false);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(this.getActivity(), "搜索不能为空哦", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 350);
            toast.show();
        }
        serchEdit.setText("");

    }
    private  void inSearchResultActivity(String number,String name){
        Intent intent = new Intent();
        intent.setClass(this.getActivity(), DB_SearchResultActivity.class);
        intent.putExtra("number", number);
        intent.putExtra("type",true);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}
