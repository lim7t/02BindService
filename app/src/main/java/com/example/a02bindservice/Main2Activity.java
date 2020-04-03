package com.example.a02bindservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    private MyConn myconn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.e("lim", "Activity2 OnCreate 任务栈号为"+ this.getTaskId());
    }

    public void activity2Click(View v) {
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
        Log.v("lim", "跳转到Activity3");
    }



    public void bindClick2(View v) {
        Log.i("lim", "bindClick()");
        if (myconn == null) {
            myconn = new MyConn(); //创建连接服务的对象
        } else {
            Log.i("lim", "Activity2已绑定");
        }
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, myconn, BIND_AUTO_CREATE);//绑定服务
    }

    public void callClick2(View v) {
        Log.i("lim", "callClick()");
        if (myconn != null) {
            myconn.myBinder.methodInBinder(); //调用服务中的方法
        }
    }

    public void unbindClick2(View v) {
        Log.i("lim", "unbindClick()");
        if (myconn != null) {
            unbindService(myconn); //解绑服务
            myconn = null;
        } else {
            Log.i("lim", "Activity2无绑定");
        }
    }

    private class MyConn implements ServiceConnection {
        //创建MyConn类,用于实现连接服务
        // 当成功绑定服务时调用的方法,该方法获取MyService中的Ibinder对象
        private MyService.MyBinder myBinder;

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myBinder = (MyService.MyBinder) iBinder;
            Log.i("lim", "Activity2服务成功绑定, 内存地址为:" + myBinder.toString());
        }

        //当服务失去连接时调用的方法
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i("lim", "Activity2服务成功解绑");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lim", "Activity2 onDestroy");
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.v("lim", "Activity2 调用了onStart（）方法");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.v("lim", "Activity2 调用了onResume（）方法");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.v("lim", "Activity2 调用了onPause（）方法");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.v("lim", "Activity2 调用了onStop（）方法");
//    }
//
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.v("lim", "Activity2 调用了onRestart（）方法");
//    }
}
