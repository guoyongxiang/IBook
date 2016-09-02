package com.rwcc.ibook.util;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by BILL on 2016/9/2.
 */
public class GlideUtil {

    private static final String TAG = "GlideUtils";

    /**
     * 圆形类图片加载  例如头像等类
     *
     * @param imageView 头像view
     * @param url       头像url
     */
    @BindingAdapter("circleImageUrl")
    public static void circleImageLoad(final ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).asBitmap().fitCenter().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
        Log.d(TAG, "circleImageUrl: " + url);
    }

    /**
     * 图片正常加载图片
     *
     * @param imageView 图片view
     * @param url       图片url
     */
    @BindingAdapter("imageUrl")
    public static void imageLoad(final ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
        Log.d(TAG, "imageUrl: " + url);
    }
}
