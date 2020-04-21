package com.xlh.study.handlerlibrary;

/**
 * @author: Watler Xu
 * time:2020/4/21
 * description:
 * version:0.0.1
 */
public class MainActivity extends Activity{

    private TextView mTv;

    private WxHandler mWxHandler = new WxHandler(){
        @Override
        public void handleMessage(WxMessage msg) {
            super.handleMessage(msg);
            mTv.setText((String)msg.obj);
            System.out.println("MainActivity---线程名称："+Thread.currentThread());
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MainActivity  onCreate 执行了");

        mTv = finViewById(R.id.text_view);

        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程名称："+Thread.currentThread());

                WxMessage message = new WxMessage();
                message.obj = "后台数据";
                mWxHandler.sendMessage(message);

            }
        }.start();

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("MainActivity  onResume 执行了");
    }
}
