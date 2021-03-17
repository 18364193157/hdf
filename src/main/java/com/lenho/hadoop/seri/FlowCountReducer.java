package com.lenho.hadoop.seri;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author langyonghe
 * @date 2020/11/9 10:53
 */
public class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean> {



    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long upFlowSum = 0L;
        long downFlowSum = 0L;

        for(FlowBean flowBean : values){
            upFlowSum += flowBean.getUpFlow();
            downFlowSum += flowBean.getDownFlow();
        }

        FlowBean flowBean = new FlowBean(upFlowSum,downFlowSum);
        context.write(key,flowBean);
    }
}
