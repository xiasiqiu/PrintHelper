package com.print.printlib.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/3/23.
 */

public class GoodEntity implements Parcelable {

    /**
     * 菜品名称
     */
    private String tradeName;
    /**
     * 菜品单价
     */
    private String price;
    /**
     * 菜品数量
     */
    private Integer num;
    /**
     * 备注
     */
    private String remark;

    public GoodEntity() {
    }

    public GoodEntity(String tradeName, String price, Integer num, String remark) {
        this.tradeName = tradeName;
        this.price = price;
        this.num = num;
        this.remark = remark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected GoodEntity(Parcel in) {
        tradeName = in.readString();
        if (in.readByte() == 0) {
            num = null;
        } else {
            num = in.readInt();
        }
        price = in.readString();
        remark = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tradeName);
        if (null == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(num);
        }
        dest.writeString(price);
        dest.writeString(remark);
    }

    public static final Creator<GoodEntity> CREATOR = new Creator<GoodEntity>() {
        @Override
        public GoodEntity createFromParcel(Parcel in) {
            return new GoodEntity(in);
        }

        @Override
        public GoodEntity[] newArray(int size) {
            return new GoodEntity[size];
        }
    };

    public String getTradeName() {
        return tradeName;
    }

    public String getPrice() {
        return price;
    }

    public Integer getNum() {
        return num;
    }

    public String getRemark() {
        return remark;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
