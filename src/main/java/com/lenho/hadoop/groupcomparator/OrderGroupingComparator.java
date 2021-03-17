package com.lenho.hadoop.groupcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author langyonghe
 * @date 2021/3/17 11:06
 */
public class OrderGroupingComparator extends WritableComparator {

    /**
     * 提前把类目创建好，然后调用
     */
    protected OrderGroupingComparator() {
        super(OrderBean.class, true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;

        int result;
        if (aBean.getOrderId() > bBean.getOrderId()) {
            result = 1;
        } else if (aBean.getOrderId() < bBean.getOrderId()) {
            result = -1;
        } else {
            result = 0;
        }
        return result;

    }
}
