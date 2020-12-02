package com.lenho.jvm.gc;

/**
 * GCRoots,在不可达时，也不会被立即回收，而是要经过两次被标记
 * @author langyonghe
 * @date 2020/11/27 19:31
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize() method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //第一次自救（gc时，该对象实现了finalize()方法，则执行该方法）
        SAVE_HOOK = null;
        System.gc();
        //因为finalize的执行线程优先级很低，需要暂停0.5s等待
        Thread.sleep(500);
        if(null != SAVE_HOOK){
            System.out.println("i am still alive");
        }else {
            System.out.println("i am died");
        }

//   与上述代码相同，但是SAVE_HOOK这次是die，因为一个对象只能调用一次finalize()方法的机会
        SAVE_HOOK = null;
        System.gc();
        //因为finalize的执行线程优先级很低，需要暂停0.5s等待
        Thread.sleep(500);
        if(null != SAVE_HOOK){
            System.out.println("i am still alive");
        }else {
            System.out.println("i am died");
        }


    }
}
