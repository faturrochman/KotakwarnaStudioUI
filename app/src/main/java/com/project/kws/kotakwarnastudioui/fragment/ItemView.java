package com.project.kws.kotakwarnastudioui.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.kws.kotakwarnastudioui.R;
import com.project.kws.kotakwarnastudioui.activity.Drawer_LeftMenu;

/**
 * Created by Fajar on 7/5/2014.
 */
public class ItemView extends Fragment {

    public static final String TAG = "itemview";

    private View viewHierarchy;
    private LinearLayout ll_detail_item;
    private LinearLayout ll_item_right, ll_item_left, ll_search_item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHierarchy = inflater.inflate(R.layout.f_item_view, container, false);
        ll_detail_item = (LinearLayout) viewHierarchy.findViewById(R.id.ll_detail_item);
        ll_item_right = (LinearLayout) viewHierarchy.findViewById(R.id.ll_item_right);
        ll_item_left = (LinearLayout) viewHierarchy.findViewById(R.id.ll_item_left);

        ll_search_item = (LinearLayout) viewHierarchy.findViewById(R.id.ll_search_item);
        ll_search_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawer_LeftMenu drawer_leftMenu = (Drawer_LeftMenu) getActivity();
                drawer_leftMenu.onChangeActivity();
            }
        });

        ll_item_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawer_LeftMenu drawer_leftMenu = (Drawer_LeftMenu) getActivity();
                drawer_leftMenu.onChangeFragmentLeftRightCursor(false);
            }
        });

        ll_item_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawer_LeftMenu drawer_leftMenu = (Drawer_LeftMenu) getActivity();
                drawer_leftMenu.onChangeFragmentLeftRightCursor(true);
            }
        });

        TextView tv_view3 = new TextView(getActivity().getApplicationContext());
        tv_view3.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        tv_view3.setText("\nCollaboratively administrate empowered markets via plug-and-play" +
                "networks. Dynamically procrastinate B2C users after installed base" +
                "benefits. Dramatically visualize customer directed convergence without" +
                "cross-media value. Quickly maximize timely deliverables for real-time" +
                "schemas. Dramatically maintain clicks-and-mortar solutions without" +
                "functional solutions." +
                "\n" +
                "Collaboratively administrate empoweredmarkets via plug-and-play" +
                "networks. Dynamically procrastinate B2C users");
        tv_view3.setTextColor(Color.BLACK);
        tv_view3.setTypeface(Typeface.DEFAULT);
        tv_view3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        ll_detail_item.addView(tv_view3, 0);

        TextView tv_view2 = new TextView(getActivity().getApplicationContext());
        tv_view2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        tv_view2.setText("\nPublished at 29 June 2014");
        tv_view2.setTextColor(Color.GRAY);
        tv_view2.setTypeface(Typeface.DEFAULT);
        tv_view2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        ll_detail_item.addView(tv_view2, 0);

        TextView tv_view1 = new TextView(getActivity().getApplicationContext());
        tv_view1.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        tv_view1.setText("BEETWN - Extreme Sports\nMagazine Concept");
        tv_view1.setTextColor(Color.BLACK);
        tv_view1.setTypeface(Typeface.DEFAULT_BOLD);
        tv_view1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        ll_detail_item.addView(tv_view1, 0);


        return viewHierarchy;
    }
}
