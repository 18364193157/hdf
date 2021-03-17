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
        context.write(key,NullWritable.get());
    }
}
