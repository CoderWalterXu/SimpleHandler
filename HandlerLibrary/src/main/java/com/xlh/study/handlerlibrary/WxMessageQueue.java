package com.xlh.study.handlerlibrary;

/**
 * @author: Watler Xu
 * time:2020/4/21
 * description:
 * version:0.0.1
 */
public class WxMessageQueue {

    private WxMessage mMessages;

    public boolean enqueueMessage(WxMessage msg,long when){
        synchronized (this){
            msg.when = when;
            WxMessage p = mMessages;
            if (p == null || when == 0 || when < p.when) {
                // New head, wake up the event queue if blocked.
                msg.next = p;
                mMessages = msg;
            } else {
                WxMessage prev;
                for (;;) {
                    prev = p;
                    p = p.next;
                    if (p == null || when < p.when) {
                        break;
                    }
                }
                msg.next = p; // invariant: p == prev.next
                prev.next = msg;
            }
        }
        return true;
    }

    public WxMessage next(){
        int pendingIdleHandlerCount = -1; // -1 only during first iteration
        for (;;) {

            synchronized (this) {
                // Try to retrieve the next message. Return if found.
                final long now = System.currentTimeMillis();
                WxMessage prevMsg = null;
                WxMessage msg = mMessages;
                if (msg != null && msg.target == null) {
                    // Stalled by a barrier. Find the next asynchronous message
                    // in the queue.
                    do {
                        prevMsg = msg;
                        msg = msg.next;
                    } while (msg != null);
                }
                if (msg != null) {
                    if (now < msg.when) {
                        // Next message is not ready. Set a timeout to wake up
                        // when it is ready.
                    } else {
                        if (prevMsg != null) {
                            prevMsg.next = msg.next;
                        } else {
                            mMessages = msg.next;
                        }
                        msg.next = null;
                        return msg;
                    }
                } else {
                }

                if (pendingIdleHandlerCount <= 0) {
                    continue;
                }
            }
        }
    }

}
