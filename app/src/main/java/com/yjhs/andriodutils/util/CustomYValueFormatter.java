package com.yjhs.andriodutils.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * author: Administrator
 * date: 2018/11/6 0006
 * desc:
 */
public class CustomYValueFormatter implements IAxisValueFormatter {

    public CustomYValueFormatter(boolean b) {

    }

    private DecimalFormat mFormat;

    public CustomYValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + " $";
    }
}
