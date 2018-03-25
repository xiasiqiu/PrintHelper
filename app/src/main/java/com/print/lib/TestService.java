package com.print.lib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.print.printlib.PrintPlan;
import com.print.printlib.bean.GoodEntity;
import com.print.printlib.bean.OrderEntity;
import com.print.printlib.bt.BluetoothController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/24.
 */

public class TestService extends Service implements  BluetoothController.PrinterInterface {
    private MyThread myThread;
    private boolean flag;
    BluetoothController bluetoothController;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        flag=true;
        bluetoothController = new BluetoothController(this);
        bluetoothController.setPrinterInterface(this);
        bluetoothController.init();

        this.myThread = new MyThread();
        this.myThread.start();
        super.onCreate();
    }

    @Override
    public void NoBT() {
        Toast.makeText(this,"NOBT",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void BT_NoOpen() {
        Toast.makeText(this,"BT_NoOpen",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void BT_NoBind() {
        Toast.makeText(this,"BT_NoBind",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void BT_Bind(String name, String address) {
        Toast.makeText(this,"BT_Bind",Toast.LENGTH_SHORT).show();

    }

    private class MyThread extends Thread {
        @Override
        public void run() {
            int j = 0;
            while (flag) {
                List<GoodEntity> goodsEntityList = new ArrayList<>();
                for (int i = 1; i < 4; i++) {
                    GoodEntity goodEntity = new GoodEntity();
                    goodEntity.setTradeName("蒸煮奶茶" + i);
                    goodEntity.setNum(Integer.valueOf(i));
                    goodEntity.setPrice("13");
                    goodEntity.setRemark("哈哈哈哈哈哈");
                    goodsEntityList.add(goodEntity);
                }
                j++;
                if (j == 2) {
                    flag = false;
                }
                OrderEntity orderEntity = new OrderEntity("华夏游子" + j, "这里是口号！！！", "11276352615233", new Date(System.currentTimeMillis()), "沈万三", "15787336521", "四川省成都市高新区天府三街XXXXXXX", "85度C", goodsEntityList, "", "233", "11", "3123", "12", "2", "https://github.com/guochen");
                PrintPlan.getInstance().init(TestService.this).print(orderEntity);
            }
        }
    }

    @Override
    public void onDestroy() {
        this.flag = false;
        super.onDestroy();
    }
}
