package com.lenho.hadoop.seri.bean;

import com.lenho.hadoop.seri.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2020/11/9 10:53
 */
public class FlowCountBeanReducer extends Reducer<FlowBean,Text,Text,FlowBean> {



    @Override
    protected void reduce(FlowBean bean, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        // 循环输出，避免总流量相同情况
        for (Text text : values) {
            context.write(text, bean);
        }
    }
}
