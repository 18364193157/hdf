package com.lenho.hadoop.seri.bean;

import com.lenho.hadoop.seri.FlowBean;
import com.lenho.hadoop.seri.FlowCountMapper;
import com.lenho.hadoop.seri.FlowCountReducer;
import com.lenho.hadoop.seri.ProvincePartitioner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author langyonghe
 * @date 2020/11/9 11:00
 */
public class FlowsumBeanDriver {

    public static void main(String[] args) throws Exception{

        Job flowSumBeanJob = Job.getInstance(new Configuration(), "flow bean");
        flowSumBeanJob.setJar("D:\\hdfslearning\\target\\hdfslearning-1.0-SNAPSHOT.jar");
//        flowSumBeanJob.setJarByClass(FlowsumDriver.class);
        flowSumBeanJob.setMapperClass(FlowCountBeanMapper.class);
        flowSumBeanJob.setReducerClass(FlowCountBeanReducer.class);

        flowSumBeanJob.setMapOutputKeyClass(FlowBean.class);
        flowSumBeanJob.setMapOutputValueClass(Text.class);

        flowSumBeanJob.setOutputKeyClass(Text.class);
        flowSumBeanJob.setOutputValueClass(FlowBean.class);

        //区内排序
        flowSumBeanJob.setPartitionerClass(ProvinceBeanPartitioner.class);
        flowSumBeanJob.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(flowSumBeanJob,new Path(args[0]));
        FileOutputFormat.setOutputPath(flowSumBeanJob,new Path(args[1]));

        boolean result = flowSumBeanJob.waitForCompletion(Boolean.TRUE);
        Runtime.getRuntime().exit(result ? 0 : 1);
    }
}
