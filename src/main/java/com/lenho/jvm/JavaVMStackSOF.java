package com.lenho.jvm;

/**
 * 栈的容量只由-Xss来设定
 * @author langyonghe
 * @date 2020/11/26 20:41
 */
public class JavaVMStackSOF {
    private int stackLength = 1;
    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try{
            javaVMStackSOF.stackLeak();
        }catch (Throwable e){
            System.out.println("stack length:" + javaVMStackSOF.stackLength);
            throw e;
        }
    }
}
