package com.ruanorz.sdos.home.adapters;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ruanorz.sdos.home.HomeAdministratorFragment;
import com.ruanorz.sdos.home.HomeFruitFragment;
import com.ruanorz.sdos.home.HomeTechnicalFragment;

public class TabsAdapter extends FragmentPagerAdapter {
    public static final int TAB_COUNT = 2;
    public boolean userIsAdministrator = false;


    public TabsAdapter(FragmentManager fm, boolean userIsAdministrator) {
        super(fm);
        this.userIsAdministrator = userIsAdministrator;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                if (userIsAdministrator){
                    return HomeAdministratorFragment.newInstance();
                }else {
                    return HomeTechnicalFragment.newInstance();
                }
            case 1: // Fragment # 0 - This will show FirstFragment different title

                return HomeFruitFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (userIsAdministrator) {
            if (position == 0) {
                return "Actions";
            } else {
                return "Fruits";
            }
        }else {
            if (position == 0) {
                return "Tasks";
            } else {
                return "Fruits";
            }
        }
    }
}