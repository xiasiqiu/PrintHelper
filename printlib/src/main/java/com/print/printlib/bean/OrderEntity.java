package com.print.printlib.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class OrderEntity implements Parcelable {
    /**
     * app名称
     */
    private String appName;
    /**
     * app宣传语
     */
    private String appslogan;
    /**
     * 订单ID
     */
    private String orderID;
    /**
     * 下单时间
     */
    private Date orderDate;
    /**
     * 客户名称
     */
    private String customerName;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 订单地址
     */
    private String orderAddress;
    /**
     * 商家名称
     */
    private String saler;
    /**
     * 商品列表
     */
    private List<GoodEntity> orderDetail;
    /**
     * 配送时间
     */
    private String deliverDatetime;
    /**
     * 小计
     */
    private String sum;
    /**
     * 税费
     */
    private String tax;
    /**
     * 合计
     */
    private String total;
    /**
     * 配送费
     */
    private String deliverCost;
    /**
     * 折扣
     */
    private String discount;
    /**
     * 二维码链接
     */
    private String shareAPPURL;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.appName);
        dest.writeString(this.appslogan);
        dest.writeString(this.orderID);
        dest.writeLong(this.orderDate.getTime());
        dest.writeString(this.customerName);
        dest.writeString(this.tel);
        dest.writeString(this.orderAddress);
        dest.writeString(this.saler);
        dest.writeTypedList(this.orderDetail);
        dest.writeString(this.deliverDatetime);
        dest.writeString(this.sum);
        dest.writeString(this.tax);
        dest.writeString(this.total);
        dest.writeString(this.deliverCost);
        dest.writeString(this.discount);
        dest.writeString(this.shareAPPURL);
    }

    public OrderEntity() {
    }

    public OrderEntity(String appName, String appslogan, String orderID, Date orderDate, String customerName, String tel, String orderAddress, String saler, List<GoodEntity> orderDetail, String deliverDatetime, String sum, String tax, String total, String deliverCost, String discount, String shareAPPURL) {
        this.appName = appName;
        this.appslogan = appslogan;
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.tel = tel;
        this.orderAddress = orderAddress;
        this.saler = saler;
        this.orderDetail = orderDetail;
        this.deliverDatetime = deliverDatetime;
        this.sum = sum;
        this.tax = tax;
        this.total = total;
        this.deliverCost = deliverCost;
        this.discount = discount;
        this.shareAPPURL = shareAPPURL;
    }

    public void setShareAPPURL(String shareAPPURL) {
        this.shareAPPURL = shareAPPURL;
    }

    protected OrderEntity(Parcel in) {
        this.appName = in.readString();
        this.appslogan = in.readString();
        this.orderID = in.readString();
        this.orderDate = new Date(in.readLong());
        this.customerName = in.readString();
        this.tel = in.readString();
        this.orderAddress = in.readString();
        this.saler = in.readString();
        this.orderDetail = in.createTypedArrayList(GoodEntity.CREATOR);
        this.deliverDatetime = in.readString();
        this.sum = in.readString();
        this.tax = in.readString();
        this.total = in.readString();
        this.deliverCost = in.readString();
        this.discount = in.readString();
        this.shareAPPURL = in.readString();
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel source) {
            return new OrderEntity(source);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };

    public String getAppName() {
        return appName;
    }

    public String getAppslogan() {
        return appslogan;
    }

    public String getOrderID() {
        return orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getTel() {
        return tel;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getSaler() {
        return saler;
    }

    public List<GoodEntity> getOrderDetail() {
        return orderDetail;
    }

    public String getDeliverDatetime() {
        return deliverDatetime;
    }

    public String getSum() {
        return sum;
    }

    public String getTax() {
        return tax;
    }

    public String getTotal() {
        return total;
    }

    public String getDeliverCost() {
        return deliverCost;
    }

    public String getDiscount() {
        return discount;
    }

    public String getShareAPPURL() {
        return shareAPPURL;
    }


}
