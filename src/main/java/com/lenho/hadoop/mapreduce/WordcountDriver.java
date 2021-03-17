package com.lenho.hadoop.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author langyonghe
 * @date 2020/11/6 14:40
 */
public class WordcountDriver {

    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS", "com.lenho.hadoop.mapreduce.hdfs://192.168.130.11:8020");
//        configuration.set("com.lenho.hadoop.mapreduce.framework.name","yarn");
//        configuration.set("mapreduce.app-submission.cross-platform","true");
//        configuration.set("yarn.resourcemanager.hostname","192.168.130.12");


        Job wordCountJob = Job.getInstance(configuration, "word count");
        wordCountJob.setJar("D:\\hdfslearning\\target\\hdfslearning-1.0-SNAPSHOT.jar");
        wordCountJob.setMapperClass(WordcountMapper.class);
        wordCountJob.setReducerClass(WordcountReducer.class);

        //设置map输出
        wordCountJob.setMapOutputKeyClass(Text.class);
        wordCountJob.setMapOutputValueClass(IntWritable.class);

        // 设置最终输出kv类型
        wordCountJob.setOutputKeyClass(Text.class);
        wordCountJob.setOutputValueClass(IntWritable.class);

        //设置输入和输出路径
        FileInputFormat.setInputPaths(wordCountJob,new Path(args[0]));
        FileOutputFormat.setOutputPath(wordCountJob,new Path(args[1]));

        // 如果不设置InputFormat，它默认用的是TextInputFormat.class
//        wordCountJob.setInputFormatClass(CombineTextInputFormat.class);

        //提交
        boolean result = wordCountJob.waitForCompletion(Boolean.TRUE);
        Runtime.getRuntime().exit(result ? 0 : 1);

    }
}
