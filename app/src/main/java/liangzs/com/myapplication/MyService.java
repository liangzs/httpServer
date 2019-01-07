package liangzs.com.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

import liangzs.com.myapplication.nanohttpd.protocols.http.HttpServer;

/**
 * @author liangzs
 * @Date 2019/1/7
 */
public class MyService extends Service {
    private static final String TAG = "MyService";
    private HttpServer httpServer;
    private MyBinder binder = new MyBinder();

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "start httpService-->onCreate");
        super.onCreate();
        httpServer = new HttpServer(8080);
        try {
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        if (httpServer != null) {
            httpServer.stop();
        }
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "start service onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyBinder extends Binder {

        MyService getService() {
            return MyService.this;
        }

    }

}
