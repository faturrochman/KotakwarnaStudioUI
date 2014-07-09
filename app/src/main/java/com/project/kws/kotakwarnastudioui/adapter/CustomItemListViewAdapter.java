package com.project.kws.kotakwarnastudioui.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.kws.kotakwarnastudioui.R;
import com.project.kws.kotakwarnastudioui.activity.Drawer_LeftMenu;

import java.util.ArrayList;

import lazylist.ImageLoader;

/**
 * Created by Fajar on 7/4/2014.
 */
public class CustomItemListViewAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> materialBeans;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public CustomItemListViewAdapter(Activity activity, ArrayList<String> materialBeans) {
        this.activity = activity;
        this.materialBeans = materialBeans;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return materialBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public ImageView iv_img_item;
        public TextView tv;
        public LinearLayout ll_view, ll_edit, ll_del;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (convertView==null){
            view = inflater.inflate(R.layout.listrow_item, null);
            holder = new ViewHolder();
            holder.iv_img_item = (ImageView) view.findViewById(R.id.iv_img_item);
            holder.tv = (TextView) view.findViewById(R.id.tv_text);
            holder.ll_view = (LinearLayout) view.findViewById(R.id.ll_view_item);
            holder.ll_edit = (LinearLayout) view.findViewById(R.id.ll_edit_item);
            holder.ll_del = (LinearLayout)view.findViewById(R.id.ll_del_item);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        String text = "<b><font size='20' color=#000000>"+materialBeans.get(position)+"</font></b><br/><br/><font size='10' color=#CCCCCC>Published at 29 June 2014</font>";
        String text2 = "<font size=10 color=#CCCCCC>Published at 29 June 2014</font>";
        holder.tv.setText(Html.fromHtml(text));
        ImageView imageView = holder.iv_img_item;
        imageLoader.DisplayImage("", imageView);

        holder.ll_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawer_LeftMenu drawer_leftMenu = (Drawer_LeftMenu) activity;
                drawer_leftMenu.onChangeFragment();
            }
        });
        holder.ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplicationContext(), "EDIT", Toast.LENGTH_SHORT).show();
            }
        });
        holder.ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplicationContext(), "DELETE", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
