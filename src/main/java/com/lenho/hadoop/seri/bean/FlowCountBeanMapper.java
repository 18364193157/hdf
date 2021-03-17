package com.lenho.hadoop.seri.bean;

import com.lenho.hadoop.seri.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2020/11/9 10:42
 */
public class FlowCountBeanMapper extends Mapper<LongWritable, Text, FlowBean,Text> {

    FlowBean bean = new FlowBean();
    Text v = new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String dataLine = value.toString();
        String[] split = dataLine.split("\t");
        v.set(split[1]);
        bean.setUpFlow(Long.parseLong(split[split.length-3]));
        bean.setDownFlow(Long.parseLong(split[split.length-2]));
        bean.setSumFlow(Long.parseLong(split[split.length-3]) + Long.parseLong(split[split.length-2]));
        context.write(bean,v);

    }
}
