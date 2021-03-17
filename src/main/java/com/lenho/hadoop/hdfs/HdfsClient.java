package com.lenho.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.net.URI;

/**
 * @author langyonghe
 * @date 2020/11/5 16:28
 */
public class HdfsClient {

    @Test
    public void testUploadFile() throws Exception{

        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "com.lenho.hadoop.mapreduce.hdfs://hadoop11:8020");
        FileSystem fileSystem = FileSystem.get(URI.create("com.lenho.hadoop.mapreduce.hdfs://hadoop11:8020"), configuration, "root");
//        fileSystem.copyFromLocalFile(new Path("C:\\Users\\langyonghe\\Desktop\\数据.txt"),new Path("/input")); //上传
        fileSystem.delete(new Path("/output"),true); //删除
//        fileSystem.copyToLocalFile(new Path("/config.ini"),new Path("d:/")); //存到本地
        //文件的的详情信息
//        RemoteIterator<LocatedFileStatus> lists = fileSystem.listFiles(new Path("/"), true);
//        while (lists.hasNext()){
//            LocatedFileStatus fileStatus = lists.next();
//            String name = fileStatus.getPath().getName();
//            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
//            for (BlockLocation e : blockLocations){
//                // 获取块存储的主机节点
//                String[] hosts = e.getHosts();
//                for (String host : hosts) {
//                    System.out.println(name+":  " + host);
//                }
//            }
//            System.out.println("======================================");
//        }
        fileSystem.close();
    }
}
