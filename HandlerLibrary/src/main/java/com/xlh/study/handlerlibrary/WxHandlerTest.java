package com.xlh.study.handlerlibrary;

/**
 * @author: Watler Xu
 * time:2020/4/21
 * description:
 * version:0.0.1
 */
public class WxHandlerTest {
    public static void main(String[] args) {
        WxLooper.prepare();

        ActivityThread thread = new ActivityThread();
        thread.attach(false);

        WxLooper.loop();
    }
}
