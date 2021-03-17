package com.lenho.hadoop.seri;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * WritableComparable: 序列化、反序列化和排序
 * 因为MapReduce自定的集中基本类型不满足需求，定义对象就要实现writable和Comparable接口，分别用于序列化、反序列化和shuffle阶段的排序
 * @author langyonghe
 * @date 2020/11/9 10:25
 */
public class FlowBean implements WritableComparable<FlowBean> {

    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public FlowBean() {
        super();
    }

    public FlowBean(long upFlow, long downFlow) {
        super();
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    // 写序列化方法
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);

    }

    //反序列化方法(顺寻与序列化保持一致)
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFlow = dataInput.readLong();
    }


    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public int compareTo(FlowBean bean) {
        int result;
        if(sumFlow > bean.getSumFlow()) {
            result = -1;
        } else if(sumFlow < bean.getSumFlow()) {
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }
}
