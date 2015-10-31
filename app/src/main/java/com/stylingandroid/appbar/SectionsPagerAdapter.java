package com.stylingandroid.appbar;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final Section[] SECTIONS = {
            new Section("SECTION 1", R.drawable.beach_huts),
            new Section("SECTION 2", R.drawable.hoveton),
            new Section("SECTION 3", R.drawable.needles)
    };

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SimpleFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return SECTIONS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getSection(position).getTitle();
    }

    @DrawableRes
    public int getImageId(int position) {
        return getSection(position).getDrawableId();
    }

    private Section getSection(int position) {
        return SECTIONS[position];
    }

    private static final class Section {
        private final String title;
        @DrawableRes
        private final int drawableId;

        private Section(String title, int drawableId) {
            this.title = title;
            this.drawableId = drawableId;
        }

        public String getTitle() {
            return title;
        }

        public int getDrawableId() {
            return drawableId;
        }
    }
}
