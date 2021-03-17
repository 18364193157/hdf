package com.lenho.hadoop.groupcomparator;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author langyonghe
 * @date 2021/3/17 10:10
 */
public class OrderBean implements WritableComparable<OrderBean> {

    private int orderId;
    private double price;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderBean() {
        super();
    }
    
    public OrderBean(int orderId, double price) {
        super();
        this.orderId = orderId;
        this.price = price;
    }

    @Override
    public String toString() {
        return orderId + "\t" + price;
    }


    public int compareTo(OrderBean o) {

        int result;
        if(orderId > o.getOrderId()) {
            result = 1;
        }else if (orderId < o.getOrderId()) {
            result = -1;
        }else {
            result = price > o.getPrice() ? -1 : 1;
        }
        return result;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(orderId);
        dataOutput.writeDouble(price);
    }

    public void readFields(DataInput dataInput) throws IOException {
        orderId = dataInput.readInt();
        price = dataInput.readDouble();
    }
}
