package com.print.lib;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.print.lib.bt.SearchBluetoothActivity;
import com.print.printlib.PrintPlan;
import com.print.printlib.PrintService;
import com.print.printlib.bean.GoodEntity;
import com.print.printlib.bean.OrderEntity;
import com.print.printlib.bt.BluetoothController;
import com.print.printlib.print.PrintUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener, BluetoothController.PrinterInterface {
    TextView tv_info;
    boolean mBtEnable = true;
    BluetoothController bluetoothController;
    int PERMISSION_REQUEST_COARSE_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_add_print).setOnClickListener(this);
        findViewById(R.id.tv_print_test).setOnClickListener(this);
        tv_info = findViewById(R.id.tv_info);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }
        bluetoothController = new BluetoothController(this);
        bluetoothController.setPrinterInterface(this);
//        Intent intent = new Intent(this, TestService.class);
//        startService(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        bluetoothController.init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_print://连接打印机
                if (bluetoothController.getmAdapter().getState() == BluetoothAdapter.STATE_OFF) {//蓝牙被关闭时强制打开
                    bluetoothController.getmAdapter().enable();
                    ToastUtil.showToast(MainActivity.this, "蓝牙被关闭请打开...");
                } else {
                    startActivity(new Intent(MainActivity.this, SearchBluetoothActivity.class));
                }
                break;
            case R.id.tv_print_test:
                List<GoodEntity> goodsEntityList = new ArrayList<>();
                for (int i = 1; i < 4; i++) {
                    GoodEntity goodEntity = new GoodEntity();
                    goodEntity.setTradeName("蒸煮奶茶");
                    goodEntity.setNum(new Integer(i));
                    goodEntity.setPrice("13");
                    goodEntity.setRemark("哈哈哈哈哈哈");
                    goodsEntityList.add(goodEntity);
                }
                OrderEntity orderEntity = new OrderEntity("华夏游子", "这里是口号！！！", "11276352615233", new Date(System.currentTimeMillis()), "沈万三", "15787336521", "四川省成都市高新区天府三街XXXXXXX", "85度C", goodsEntityList, "", "233", "11", "3123", "12", "2", "https://github.com/guochen");
                PrintPlan.getInstance().init(getApplicationContext()).print(orderEntity);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void NoBT() {
        this.tv_info.setText("该设备没有蓝牙模块");
        this.mBtEnable = false;
    }


    @Override
    public void BT_NoOpen() {
        this.tv_info.setText("蓝牙未打开");
    }

    @Override
    public void BT_NoBind() {
        this.tv_info.setText("尚未绑定蓝牙设备");
    }

    @Override
    public void BT_Bind(String name, String address) {
        this.tv_info.setText("已绑定蓝牙：" + name);
        this.tv_info.setText(address);
    }


}
