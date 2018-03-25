package com.print.printlib;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.print.printlib.bean.OrderEntity;
import com.print.printlib.print.PrintQueue;
import com.print.printlib.print.PrintUtil;
import com.print.printlib.printUtil.PrintOrderDataMaker;
import com.print.printlib.printUtil.PrinterWriter;
import com.print.printlib.printUtil.PrinterWriter58mm;
import com.print.printlib.printUtil.QRCodeUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/24.
 */

public class PrintService extends IntentService {
    private OrderEntity entity;

    public PrintService() {
        super("PrintService");
    }

    public PrintService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        if (intent.getParcelableExtra(PrintUtil.ACTION_PRINT_ENTITY) != null) {
            entity = intent.getParcelableExtra(PrintUtil.ACTION_PRINT_ENTITY);
            entity.setShareAPPURL(QRCodeUtil.createQRImage(entity.getShareAPPURL(), 300, 300, null));
            print(entity);
        } else {
            Log.i("打印机", "传递数据为null");
        }

    }

    private void print(OrderEntity entity) {
        PrintOrderDataMaker printOrderDataMaker = new PrintOrderDataMaker(this, "", PrinterWriter58mm.TYPE_58, PrinterWriter.HEIGHT_PARTING_DEFAULT, entity);
        ArrayList<byte[]> printData = (ArrayList<byte[]>) printOrderDataMaker.getPrintData(PrinterWriter58mm.TYPE_58);
        PrintQueue.getQueue(getApplicationContext()).add(printData);
    }
}
