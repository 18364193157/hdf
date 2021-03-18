package com.lenho.hadoop.join.reduce;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author langyonghe
 * @date 2021/3/17 16:50
 */
public class OrderPdReducer extends Reducer<OrderPdBean, NullWritable, OrderPdBean, NullWritable> {

    @Override
    protected void reduce(OrderPdBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        //第一条数据来自pd，之后全部来自order
        Iterator<NullWritable> iterator = values.iterator();

        //通过第一条数据获取pname
        iterator.next();
        String pname = key.getpName();

        //遍历剩下的数据，替换并写出
        while (iterator.hasNext()) {
            iterator.next();
            key.setpName(pname);
            context.write(key,NullWritable.get());
        }


    }
}
