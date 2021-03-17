package com.lenho.hadoop.seri;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2020/11/9 10:42
 */
public class FlowCountMapper extends Mapper<LongWritable, Text,Text,FlowBean> {

    FlowBean v = new FlowBean();
    Text phone = new Text();


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String dataLine = value.toString();
        String[] split = dataLine.split("\t");
        phone.set(split[1]);
        v.setUpFlow(Long.parseLong(split[split.length-3]));
        v.setDownFlow( Long.parseLong(split[split.length-2]));
        context.write(phone,v);

    }
}
