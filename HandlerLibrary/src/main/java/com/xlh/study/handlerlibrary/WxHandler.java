package com.xlh.study.handlerlibrary;

/**
 * @author: Watler Xu
 * time:2020/4/21
 * description:
 * version:0.0.1
 */
public class WxHandler {

    WxMessageQueue mQueue;

    public WxHandler() {
        WxLooper looper = WxLooper.myLooper();

        if (looper == null) {
            throw new RuntimeException(
                    "Can't create WxHandler inside thread that has not called com.xlh.study.handlerlibrary.WxLooper.prepare()");
        }

        mQueue = looper.mQueue;
    }

    public void sendMessage(WxMessage message) {
        sendMessageDelayed(message, 0);
    }

    public final boolean sendMessageDelayed(WxMessage msg, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return sendMessageAtTime(msg, System.currentTimeMillis() + delayMillis);
    }

    public boolean sendMessageAtTime(WxMessage msg, long uptimeMillis) {
        WxMessageQueue queue = mQueue;
        return enqueueMessage(queue, msg, uptimeMillis);
    }

    private boolean enqueueMessage(WxMessageQueue queue, WxMessage msg, long uptimeMillis) {
        msg.target = this;
        return queue.enqueueMessage(msg, uptimeMillis);
    }

    public void handleMessage(WxMessage msg) {


    }
}
