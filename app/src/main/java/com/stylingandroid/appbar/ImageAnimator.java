package com.stylingandroid.appbar;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

class ImageAnimator {
    private final SectionsPagerAdapter pagerAdapter;
    private final ImageView outgoing;
    private final ImageView imageView;

    private int actualStart;
    private int start;
    private int end;
    private float positionFactor;

    public ImageAnimator(SectionsPagerAdapter pagerAdapter, ImageView imageView, ImageView outgoing) {
        this.pagerAdapter = pagerAdapter;
        this.imageView = imageView;
        this.outgoing = outgoing;
    }

    public void start(int startPosition, int finalPosition) {
        actualStart = startPosition;
        start = Math.min(startPosition, finalPosition);
        end = Math.max(startPosition, finalPosition);
        @DrawableRes int incomingId = pagerAdapter.getImageId(finalPosition);
        positionFactor = 1f / (end - start);
        outgoing.setImageDrawable(imageView.getDrawable());
        outgoing.setVisibility(View.VISIBLE);
        outgoing.setAlpha(1f);
        imageView.setImageResource(incomingId);
    }

    public void end(int finalPosition) {
        @DrawableRes int incomingId = pagerAdapter.getImageId(finalPosition);
        imageView.setTranslationX(0f);
        if (finalPosition == actualStart) {
            imageView.setImageDrawable(outgoing.getDrawable());
        } else {
            imageView.setImageResource(incomingId);
            outgoing.setVisibility(View.GONE);
            imageView.setAlpha(1f);
        }
    }

    public void forward(int position, float positionOffset) {
        float offset = getOffset(position, positionOffset);
        imageView.setAlpha(offset);
    }

    public void backwards(int position, float positionOffset) {
        float offset = getOffset(position, positionOffset);
        imageView.setAlpha(1 - offset);
    }

    private float getOffset(int position, float positionOffset) {
        int positions = position - start;
        return Math.abs(positions * positionFactor + positionOffset * positionFactor);
    }

    public boolean isWithin(int position) {
        return position >= start && position < end;
    }
}
