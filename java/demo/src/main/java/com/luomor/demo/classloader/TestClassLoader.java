package com.luomor.demo.classloader;

/**
 * Created by PeterZhang on 2017/10/29.
 */
public class TestClassLoader {
    public static void main(String[] args) throws Exception{
        MyClassLoader loader = new MyClassLoader(null,"d:/tmp/","landy");
        Class<?> c = loader.loadClass("Test");
        c.newInstance();
    }
}
