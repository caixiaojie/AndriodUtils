package com.yjhs.andriodutils.util;

import android.content.Context;

import com.github.mikephil.charting.components.MarkerView;

/**
 * author: Administrator
 * date: 2018/11/6 0006
 * desc:
 */
public class CustomMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public CustomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
    }
}
