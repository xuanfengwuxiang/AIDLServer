package com.xuanfeng.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class AIDLService extends Service {

    private static final String TAG = "AIDLService";
    String content = "首先，看到我要敬礼";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIDemandManager;
    }

    //本地 Binder 对象，实现 AIDL接口中交互的方法
    IDemandManager.Stub mIDemandManager = new IDemandManager.Stub() {
        @Override
        public MessageBean getDemand() throws RemoteException {
            MessageBean demand = new MessageBean(content, 1);
            return demand;
        }

        @Override
        public void setDemandIn(MessageBean msg) throws RemoteException {
            Log.i(TAG, "程序员:" + msg.toString());
            content = msg.getContent();
        }

        @Override
        public void setDemandOut(MessageBean msg) throws RemoteException {
            Log.i(TAG, "程序员:" + msg.toString());//msg内容一定为空

            msg.setContent("我不想听解释，下班前把所有工作都搞好！");
            msg.setLevel(5);
        }

        @Override
        public void setDemandInOut(MessageBean msg) throws RemoteException {
            Log.i(TAG, "程序员:" + msg.toString());

            msg.setContent("把用户交互颜色都改成粉色");
            msg.setLevel(3);
        }
    };
}
