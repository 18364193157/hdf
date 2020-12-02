package com.lenho.jvm;

import java.util.ArrayList;

/**
 * @author langyonghe
 * @date 2020/11/26 19:30
 */
public class HeapOOM {
    static class OOMObject{}

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}

/**
 * 过多线程导致内存溢出：
 *     在不减少线程数或者更换64位虚拟机的情况下，可通过减少最大堆和减少栈容量来换去更多的线程
 */
class JavaVMStackOOM{
    private void donStop(){
        while (true){ }
    }

    public void stackLeakByThread(){
        while (true){
            new Thread(new Runnable() {
                public void run() {
                    donStop();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThread();
    }
}