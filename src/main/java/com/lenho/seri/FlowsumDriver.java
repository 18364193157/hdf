package com.lenho.seri;

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

        args = new String[] { "C:/Users/langyonghe/Desktop/input", "C:/Users/langyonghe/Desktop/output" };

        Job flowSumJob = Job.getInstance(new Configuration(), "flow sum");

        flowSumJob.setJarByClass(FlowsumDriver.class);
        flowSumJob.setMapperClass(FlowCountMapper.class);
        flowSumJob.setReducerClass(FlowCountReducer.class);

        flowSumJob.setMapOutputKeyClass(Text.class);
        flowSumJob.setMapOutputValueClass(FlowBean.class);

        flowSumJob.setOutputKeyClass(Text.class);
        flowSumJob.setOutputValueClass(FlowBean.class);

        FileInputFormat.setInputPaths(flowSumJob,new Path(args[0]));
        FileOutputFormat.setOutputPath(flowSumJob,new Path(args[1]));

        boolean result = flowSumJob.waitForCompletion(Boolean.TRUE);
        Runtime.getRuntime().exit(result ? 0 : 1);
    }
}
