package com.lenho.hadoop.join.reduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2021/3/17 17:02
 */
public class OrderPdDriver {


    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Job job = Job.getInstance(new Configuration());

        job.setJar("D:\\hdfslearning\\target\\hdfslearning-1.0-SNAPSHOT.jar");
        job.setMapperClass(OrderPdMapper.class);
        job.setReducerClass(OrderPdReducer.class);
        job.setMapOutputKeyClass(OrderPdBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(OrderPdBean.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        boolean b = job.waitForCompletion(true);
        System.out.println(b ? 0 : 1);
    }
}
