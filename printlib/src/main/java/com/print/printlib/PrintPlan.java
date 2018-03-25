package com.print.printlib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.print.printlib.bean.OrderEntity;
import com.print.printlib.bt.BluetoothController;
import com.print.printlib.bt.BtInterface;
import com.print.printlib.print.PrintUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/3/24.
 */

public class PrintPlan {
    public static String btAddress;
    public static String btName;
    public static Context mContext;

    public static PrintPlan getInstance() {
        return BtPrinterHolder.INSTANCE;
    }

    private static class BtPrinterHolder {
        @SuppressLint("StaticFieldLeak")
        private static final PrintPlan INSTANCE = new PrintPlan();
    }

    /**
     * 初始化
     *
     * @param context 调用的Activity实例
     */
    @SuppressWarnings("unused")
    public PrintPlan init(Context context) {
        PrintPlan.mContext = context;
        btAddress = PrintUtil.getDefaultBluethoothDeviceAddress(mContext);
        btName = PrintUtil.getDefaultBluetoothDeviceName(mContext);
        return this;
    }


    /**
     * 打印
     *
     * @param orderEntity
     */
    public void print(OrderEntity orderEntity) {
        Intent intent = new Intent(mContext, PrintService.class);
        intent.setAction(PrintUtil.ACTION_PRINT_TEST);
        intent.putExtra(PrintUtil.ACTION_PRINT_ENTITY, orderEntity);
        mContext.startService(intent);
    }


}
