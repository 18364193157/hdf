package com.lenho.hadoop.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2020/11/6 14:39
 *
 */
public class WordcountMapper extends Mapper<LongWritable,Text,Text, IntWritable> {

    /**
     *
     * @param key 行号 框架把文件数据拆成一行一行的，然后再输出出去
     * @param value  一行的数据
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //获取第一行数据
        String dataLine = value.toString();
        //分割
        String[] splitWords = dataLine.split(" ");
        //拆分的数据写入
        for(String word : splitWords){
           context.write(new Text(word),new IntWritable(1));
        }
    }
}
