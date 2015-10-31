package com.stylingandroid.appbar;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

public class PageChangeListener implements ViewPager.OnPageChangeListener {

    private final ImageAnimator imageAnimator;

    private int currentPosition = 0;
    private int finalPosition = 0;

    private boolean isScrolling = false;

    PageChangeListener(ImageAnimator imageAnimator) {
        this.imageAnimator = imageAnimator;
    }

    public static PageChangeListener newInstance(SectionsPagerAdapter pagerAdapter, ImageView imageView, ImageView outgoing) {
        ImageAnimator imageAnimator = new ImageAnimator(pagerAdapter, imageView, outgoing);
        return new PageChangeListener(imageAnimator);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (isFinishedScrolling(position, positionOffset)) {
            finishScroll(position);
        }
        if (isStartingScrollToPrevious(position, positionOffset)) {
            startScroll(position);
        } else if (isStartingScrollToNext(position, positionOffset)) {
            startScroll(position + 1);
        }
        if (isScrollingToNext(position, positionOffset)) {
            imageAnimator.forward(position, positionOffset);
        } else if (isScrollingToPrevious(position, positionOffset)) {
            imageAnimator.backwards(position, positionOffset);
        }
    }

    public boolean isFinishedScrolling(int position, float positionOffset) {
        return isScrolling && (positionOffset == 0f && position == finalPosition) || !imageAnimator.isWithin(position);
    }

    private boolean isScrollingToNext(int position, float positionOffset) {
        return isScrolling && position >= currentPosition && positionOffset != 0f;
    }

    private boolean isScrollingToPrevious(int position, float positionOffset) {
        return isScrolling && position != currentPosition && positionOffset != 0f;
    }

    private boolean isStartingScrollToNext(int position, float positionOffset) {
        return !isScrolling && position == currentPosition && positionOffset != 0f;
    }

    private boolean isStartingScrollToPrevious(int position, float positionOffset) {
        return !isScrolling && position != currentPosition && positionOffset != 0f;
    }

    private void startScroll(int position) {
        isScrolling = true;
        finalPosition = position;
        imageAnimator.start(currentPosition, position);
    }

    private void finishScroll(int position) {
        if (isScrolling) {
            currentPosition = position;
            isScrolling = false;
            imageAnimator.end(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //NO-OP
    }

    @Override
    public void onPageSelected(int position) {
        if (!isScrolling) {
            isScrolling = true;
            finalPosition = position;
            imageAnimator.start(currentPosition, position);
        }
    }
}
