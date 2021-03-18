package com.lenho.hadoop.groupcomparator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2021/3/17 11:10
 */
public class OrderReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable> {


    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {


        //取出每个订单的前2
//        for(int i = 0; i < 2; i++){
//            context.write(key,NullWritable.get());
//        }

        //遍历所有
//        for (NullWritable e : values) {
//            context.write(key,NullWritable.get());
//        }

        //每个订单的第一个  因为有了辅助排序OrderGroupingComparator，只要订单ID相同就认为是相同key，reduce方法 is called once for each key.
        context.write(key,NullWritable.get());
    }
}
