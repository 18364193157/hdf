package com.lenho.hadoop.join.reduce;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author langyonghe
 * @date 2021/3/17 16:14
 */
public class OrderPdBean implements WritableComparable<OrderPdBean> {

    private String id;
    private String pid;
    private int number;
    private String pName;


    @Override
    public int compareTo(OrderPdBean o) {
        int result;
        if(Integer.parseInt(pid) > Integer.parseInt(o.getPid())) {
            result = 1;
        }else if(Integer.parseInt(pid) < Integer.parseInt(o.getPid())) {
            result = -1;
        }else {
            result = o.getpName().compareTo(this.pName);
        }
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(number);
        out.writeUTF(pName);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id = in.readUTF();
        this.pid = in.readUTF();
        this.number = in.readInt();
        this.pName = in.readUTF();
    }


    @Override
    public String toString() {
        return id + "\t" + pName + "\t" + number;
    }













    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

}
