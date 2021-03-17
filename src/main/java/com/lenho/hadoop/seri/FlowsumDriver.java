package com.lenho.hadoop.seri;

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
public class FlowsumDriver {

    public static void main(String[] args) throws Exception{

        Job flowSumJob = Job.getInstance(new Configuration(), "flow sum");
        flowSumJob.setJar("D:\\hdfslearning\\target\\hdfslearning-1.0-SNAPSHOT.jar");
//        flowSumJob.setJarByClass(FlowsumDriver.class);
        flowSumJob.setMapperClass(FlowCountMapper.class);
        flowSumJob.setReducerClass(FlowCountReducer.class);

        flowSumJob.setMapOutputKeyClass(Text.class);
        flowSumJob.setMapOutputValueClass(FlowBean.class);

        flowSumJob.setOutputKeyClass(Text.class);
        flowSumJob.setOutputValueClass(FlowBean.class);

        flowSumJob.setPartitionerClass(ProvincePartitioner.class);
        flowSumJob.setNumReduceTasks(5);

        FileInputFormat.setInputPaths(flowSumJob,new Path(args[0]));
        FileOutputFormat.setOutputPath(flowSumJob,new Path(args[1]));

        boolean result = flowSumJob.waitForCompletion(Boolean.TRUE);
        Runtime.getRuntime().exit(result ? 0 : 1);
    }
}
