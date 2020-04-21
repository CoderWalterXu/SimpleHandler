package com.xlh.study.handlerlibrary;

/**
 * @author: Watler Xu
 * time:2020/4/21
 * description:
 * version:0.0.1
 */
class TextView {

    private Thread mThread;

    public TextView(){
        mThread = Thread.currentThread();
    }

    public void setText(CharSequence text){
        checkThread();

        System.out.println("更新UI成功："+text);
    }

    private void checkThread() {
        if(mThread!=Thread.currentThread()){
            throw new RuntimeException(
                    "Only the original thread that created a view hierarchy can touch its views."
            );
        }

    }

}
