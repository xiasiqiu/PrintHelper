package com.print.printlib.printUtil;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Print
 * * Created by liugruirong on 2017/8/3.
 */

public interface PrintDataMaker {
    List<byte[]> getPrintData(int type);
    List<byte[]> getPrintImage(int type, Bitmap bitmap);
}
