package com.lenho.hadoop.groupcomparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2021/3/17 11:12
 */
public class OrderDriver {

    private static final String JAR_PATH = "D:\\hdfslearning\\target\\hdfslearning-1.0-SNAPSHOT.jar";

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Job job = Job.getInstance(new Configuration());

        job.setJar(JAR_PATH);
        job.setJarByClass(OrderDriver.class);
        job.setMapperClass(OrderMapper.class);
        job.setReducerClass(OrderReducer.class);
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        job.setGroupingComparatorClass(OrderGroupingComparator.class);
        boolean b = job.waitForCompletion(true);
        System.out.println(b ? 0 : 1);




    }
}
