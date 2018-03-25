package com.print.printlib.printUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.print.printlib.R;
import com.print.printlib.bean.GoodEntity;
import com.print.printlib.bean.OrderEntity;
import com.print.printlib.print.GPrinterCommand;
import com.print.printlib.print.PrintPic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * 测试数据生成器
 * Created by guochen on 8/1/17.
 */

public class PrintOrderDataMaker implements PrintDataMaker {


    private String qr;
    private int width;
    private int height;
    Context btService;

    private OrderEntity orderEntity;

    public PrintOrderDataMaker(Context btService, String qr, int width, int height, OrderEntity entity) {
        this.qr = qr;
        this.width = width;
        this.height = height;
        this.btService = btService;
        this.orderEntity = entity;
    }


    @Override
    public List<byte[]> getPrintData(int type) {
        ArrayList<byte[]> data = new ArrayList<>();
        try {
            PrinterWriter printer = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
            PrintPic printPic = PrintPic.getInstance();
            printer.setAlignCenter();
            data.add(printer.getDataAndReset());

            /**
             * app名称
             */
            printer.setAlignCenter();
            printer.setEmphasizedOn();
            printer.setFontSize(3);
            printer.print(this.orderEntity.getAppName());
            printer.printLineFeed();
            printer.setEmphasizedOff();
            printer.printLineFeed();
            /**
             * app宣传语
             */
            printer.setFontSize(0);
            printer.setAlignCenter();
            printer.print(this.orderEntity.getAppslogan() + "\n");
            printer.printLineFeed();
            data.add(printer.getDataAndReset());
            /**
             * 分割线
             */
            printer.setAlignLeft();
            printer.printLine();
            printer.printLineFeed();
            /**
             * 订单信息（收货人）
             */
            printer.setAlignLeft();
            printer.setFontSize(0);
            printer.print("顾客名称: " + this.orderEntity.getCustomerName());
            printer.printLineFeed();
            printer.print("联系电话: " + this.orderEntity.getTel());
            printer.printLineFeed();
            printer.print("订单地址: " + this.orderEntity.getOrderAddress());
            printer.printLineFeed();
            printer.print("订单编号: " + this.orderEntity.getOrderID());
            printer.printLineFeed();
            printer.print("下单时间: " + new SimpleDateFormat("MM/dd HH:mm", Locale.getDefault()).format(this.orderEntity.getOrderDate()));
            printer.printLineFeed();
            printer.print("配送时间: 即可配送");
            printer.printLineFeed();
            printer.printLineFeed();
            /**
             * 商品列表
             */
            if (this.orderEntity.getOrderDetail() != null && orderEntity.getOrderDetail().size() > 0) {
                printer.setAlignCenter();
                printer.setFontSize(2);
                printer.print(this.orderEntity.getSaler() + "\n");
                printer.printLineFeed();
                printer.setAlignLeft();
                printer.setFontSize(0);
                printer.printInOneLine("名称", "单价", "数量", 0);
                printer.printLineFeed();
                printer.printLine();
                printer.printLineFeed();
                for (GoodEntity goodEntity : orderEntity.getOrderDetail()) {
                    printer.setFontSize(1);
                    printer.printInOneLine(goodEntity.getTradeName(), " "+goodEntity.getPrice(), "1", 0);
                    if(!goodEntity.getRemark().equals("")){
                        printer.printLineFeed();
                        printer.setFontSize(0);
                        printer.print(goodEntity.getRemark());
                    }
                    printer.printLineFeed();
                }
                printer.printLine();
                printer.printLineFeed();
                /**
                 * 总计
                 */
                printer.setAlignLeft();
                printer.setFontSize(0);
                printer.printInOneLine("配送费：", "", "￥" + orderEntity.getDeliverCost(), 0);
                printer.printLineFeed();
                printer.printInOneLine("税金：", "", "￥" + orderEntity.getTax(), 0);
                printer.printLineFeed();
                printer.printInOneLine("优惠金额：", "", "￥" + orderEntity.getDiscount(), 0);
                printer.printLineFeed();
                printer.printLineFeed();
                data.add(printer.getDataAndReset());
                printer.setFontSize(1);
                printer.printInOneLine("总价：", "", "￥" + orderEntity.getSum(), 0);
                printer.printLineFeed();


                printer.printLineFeed();
                printer.setAlignCenter();
                data.add(printer.getDataAndReset());
                ArrayList<byte[]> image1 = printer.getImageByte(this.orderEntity.getShareAPPURL());
                data.addAll(image1);
                printer.printLineFeed();
                printer.print("\n");
                printer.printLineFeed();
            }
            printer.feedPaperCutPartial();
            data.add(printer.getDataAndClose());
            return data;
        } catch (IOException e) {
            return new ArrayList<>();
        }

    }

    @Override
    public List<byte[]> getPrintImage(int type, Bitmap bitmap) {
        PrintPic printPic = PrintPic.getInstance();
        printPic.init(bitmap);
        if (null != bitmap) {
            if (bitmap.isRecycled()) {
                bitmap = null;
            } else {
                bitmap.recycle();
                bitmap = null;
            }
        }
        byte[] bytes = printPic.printDraw();
        ArrayList<byte[]> printBytes = new ArrayList<byte[]>();
        printBytes.add(GPrinterCommand.reset);
        printBytes.add(GPrinterCommand.print);
        printBytes.add(bytes);
        Log.e("BtService", "image bytes size is :" + bytes.length);
        printBytes.add(GPrinterCommand.print);
        return printBytes;
    }
}
