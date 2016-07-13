package com.example.suimonkul.vsesamoe.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.suimonkul.vsesamoe.fragment.AbstractTabsFragment;
import com.example.suimonkul.vsesamoe.fragment.CookingFragment;
import com.example.suimonkul.vsesamoe.fragment.ItNewsFragment;
import com.example.suimonkul.vsesamoe.fragment.SportFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Suimonkul on 16.06.2016.
 */

public class TabLayoutAdapter extends FragmentPagerAdapter {

    Map<Integer, AbstractTabsFragment> tabs;
    private Context context;

    public TabLayoutAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabMap();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabMap() {
        tabs = new HashMap<>();
        tabs.put(0, ItNewsFragment.getInstance(context));
        tabs.put(1, CookingFragment.getInstance(context));
        tabs.put(2, SportFragment.getInstance(context));
    }
}
