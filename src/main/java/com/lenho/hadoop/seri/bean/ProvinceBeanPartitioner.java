package com.lenho.hadoop.seri.bean;

import com.lenho.hadoop.seri.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author langyonghe
 * @date 2021/3/16 17:13
 */
public class ProvinceBeanPartitioner extends Partitioner<FlowBean, Text> {

    @Override
    public int getPartition(FlowBean bean, Text text, int numPartitions) {
        String preNum = text.toString().substring(0, 3);

        int partition = 4;

        // 2 根据手机号归属地设置分区
        if ("136".equals(preNum)) {
            partition = 0;
        }else if ("137".equals(preNum)) {
            partition = 1;
        }else if ("138".equals(preNum)) {
            partition = 2;
        }else if ("139".equals(preNum)) {
            partition = 3;
        }

        return partition;

    }
}
