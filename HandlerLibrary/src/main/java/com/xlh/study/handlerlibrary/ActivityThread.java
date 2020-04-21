package com.xlh.study.handlerlibrary;



/**
 * @author: Watler Xu
 * time:2020/4/21
 * description:
 * version:0.0.1
 */
public class ActivityThread {

    final H mH = new H();

    public void attach(boolean b){
        Activity mainActivity = new MainActivity();
        mainActivity.onCreate();

        WxMessage message = new WxMessage();
        message.obj = mainActivity;
        mH.sendMessage(message);
    }

    private class H extends WxHandler{
        @Override
        public void handleMessage(WxMessage msg) {
            super.handleMessage(msg);

            Activity mainActivity  = (Activity)msg.obj;
            mainActivity.onResume();
        }
    }

}
