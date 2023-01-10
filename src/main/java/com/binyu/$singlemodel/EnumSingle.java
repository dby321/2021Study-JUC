package com.binyu.$singlemodel;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.single
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 17:50
 * @Description:
 */
public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }
    public void dosomething(){
        System.out.println ("dosomething");
    }
}
class Main{
    public static void main(String[] args) {
        EnumSingle.INSTANCE.dosomething ();
    }
}
