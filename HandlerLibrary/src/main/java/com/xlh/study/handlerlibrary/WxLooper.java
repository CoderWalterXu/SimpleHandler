package com.xlh.study.handlerlibrary;

/**
 * @author: Watler Xu
 * time:2020/4/21
 * description:
 * version:0.0.1
 */
public class WxLooper {

    static final ThreadLocal<WxLooper> sThreadLocal = new ThreadLocal<>();

    public static void prepare(){
        sThreadLocal.set(new WxLooper());
    }

    WxMessageQueue mQueue;

    public WxLooper(){
        mQueue = new WxMessageQueue();
    }

    static WxLooper myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        WxLooper looper = myLooper();
        for(;;){
            WxMessageQueue queue = looper.mQueue;

            WxMessage message = queue.next();

            if(message == null){
                return;
            }

            message.target.handleMessage(message);
        }
    }
}
