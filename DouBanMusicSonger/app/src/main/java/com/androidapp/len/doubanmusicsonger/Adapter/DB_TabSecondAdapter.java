package com.androidapp.len.doubanmusicsonger.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.androidapp.len.doubanmusicsonger.R;

import java.util.List;
import java.util.Map;

/**
 * Created by len on 16/11/30.
 */

public class DB_TabSecondAdapter extends BaseExpandableListAdapter {

    private LayoutInflater inflater;
    private List<Map<String, Object>> listArr;

    public DB_TabSecondAdapter() {
        // TODO Auto-generated constructor stub
    }

    public DB_TabSecondAdapter(Context context, List<Map<String, Object>> listArr) {
        this.inflater = LayoutInflater.from(context);
        this.listArr = listArr;
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        return listArr.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return ((List<Map<String, String>>)listArr.get(groupPosition).get("data")).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // TODO Auto-generated method stub
        return getGroup(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return getChild(groupPosition, childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // TODO Auto-generated method stub
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        DB_SearchView searchView = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.db_expanitem_tabsecondgroup, null);
            searchView = new DB_SearchView();
            searchView.search_tv = (TextView) convertView.findViewById(R.id.group_tv);
            convertView.setTag(searchView);
        } else {
            searchView = (DB_SearchView) convertView.getTag();
        }
        searchView.search_tv.setText("  "+this.listArr.get(groupPosition).get("title").toString());
        return convertView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        // TODO Auto-generated method stub
        DB_SearchView searchView = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.db_expanitem_tabsecondchild, null);
            searchView = new DB_SearchView();
            searchView.search_tv = (TextView) convertView.findViewById(R.id.child_tv);
            convertView.setTag(searchView);
        } else {
            searchView = (DB_SearchView) convertView.getTag();
        }
        searchView.search_tv.setText("    "+((List<Map<String, String>>)this.listArr.get(groupPosition).get("data")).get(childPosition).get("name").toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

}
class DB_SearchView{
    TextView search_tv;
}
