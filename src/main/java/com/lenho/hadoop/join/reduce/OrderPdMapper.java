package com.lenho.hadoop.join.reduce;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2021/3/17 16:36
 */
public class OrderPdMapper extends Mapper<LongWritable, Text, OrderPdBean, NullWritable> {

    private String filename;

    private OrderPdBean orderPdBean  = new OrderPdBean();

    /**
     * Called once at the beginning of the task.
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //获取切片文件名
        FileSplit fs = (FileSplit) context.getInputSplit();
        filename = fs.getPath().getName();

    }


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] str = value.toString().split("\t");
        if(StringUtils.equals("order.txt",filename)) {
            orderPdBean.setId(str[0]);
            orderPdBean.setPid(str[1]);
            orderPdBean.setNumber(Integer.parseInt(str[2]));
            orderPdBean.setpName("");
        }else if(StringUtils.equals("pd.txt",filename)) {
            orderPdBean.setId("");
            orderPdBean.setPid(str[0]);
            orderPdBean.setpName(str[1]);
            orderPdBean.setNumber(0);
        }
        context.write(orderPdBean,NullWritable.get());
    }
}
