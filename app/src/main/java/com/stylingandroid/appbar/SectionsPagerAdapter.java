package com.stylingandroid.appbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String[] TITLES = {"SECTION 1", "SECTION 2", "SECTION 3"};

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SimpleFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position >= 0 && position < TITLES.length) {
            return TITLES[position];
        }
        return null;
    }
}
