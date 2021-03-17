package com.lenho.jvm.gc;

/**
 * -Xms20M -Xmx20M -Xmn10M -XX:PrintGCDetails -XX:SurvivorRatio=8
 * 分配堆内存大小为20M，不可扩展，打印GC详细日志，同时设置Eden区与一个Survivor区的比例是8：1
 *
 * @author langyonghe
 * @date 2020/12/28 17:09
 */
public class TestAllocation {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws InterruptedException {
        /**
         * 分配3个2M大小和一个4M大小的对象 ，发生一次MinorGC新生代的GC
         * 当给allocation4分配内存的时候，发现内存不足，进行一次MinorGC，因为Survivor空间仅为1M，无法存放3个2M的对象，所以只能通过
         * 分配担保机制，直接移动到老年代
         *
         * 这样的话allocation4存在Eden区占用4M，3个2M的对象在老年区占用6M
         */
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        allocation4 = new byte[4*_1MB];
    }
}
