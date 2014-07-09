package com.project.kws.kotakwarnastudioui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.project.kws.kotakwarnastudioui.R;
import com.project.kws.kotakwarnastudioui.activity.Drawer_LeftMenu;
import com.project.kws.kotakwarnastudioui.adapter.CustomItemListViewAdapter;

import java.util.ArrayList;

/**
 * Created by Fajar on 7/4/2014.
 */
public class ItemListView extends Fragment {

    public static final String TAG = "itemlistview";


    private View viewHierarchy;
    private ListView lv_itemlistview;
    private Button b_add_item;
    private LinearLayout ll_search_item, ll_add_item;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHierarchy = inflater.inflate(R.layout.f_item_listview, container, false);
        lv_itemlistview = (ListView) viewHierarchy.findViewById(R.id.lv_itemlistview);

        ll_search_item = (LinearLayout) viewHierarchy.findViewById(R.id.ll_search_item);
        ll_search_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawer_LeftMenu drawer_leftMenu = (Drawer_LeftMenu) getActivity();
                drawer_leftMenu.onChangeActivity();
            }
        });

        ll_add_item = (LinearLayout) viewHierarchy.findViewById(R.id.ll_add_item);
        ll_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "ADD NEW POST", Toast.LENGTH_SHORT).show();
            }
        });

        initListView();
        return viewHierarchy;
    }

    private void initListView(){

        ArrayList<String> strings = new ArrayList<String>();
        for(int i=0; i<5; i++){
            strings.add("BEETWN - Extreme Sports Magazine Concept");
        }

        CustomItemListViewAdapter adapter = new CustomItemListViewAdapter(getActivity(), strings);
        lv_itemlistview.setAdapter(adapter);

    }
}
