package study.lee.aidlproject;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import study.lee.aidlproject.helper.Book;

public class AIDLService extends Service {

    public static final String TAG = "AIDLService";
    private List<Book> bookList = new ArrayList<>();
    private Handler handler = new Handler();
    private LogRunnable runnable;
    private Binder binder = new Binder();
    private IMyAidlInterface.Stub myAidlInterface = new IMyAidlInterface.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            if (book != null) {
                bookList.add(book);
                Log.e(TAG, "addBook\t" + book.toString() + "\t" + bookList.size());
            }
        }

        @Override
        public List<Book> getBooks() throws RemoteException {
            Log.e(TAG, "getBooks");
            return bookList;
        }
    };
    private NotificationManagerCompat mNotificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind");
        return myAidlInterface;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        myAidlInterface = null;
        Log.e(TAG, "onUnbind");
        return true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
        mNotificationManager = NotificationManagerCompat.from(this);
        createNotificationChannel();
        Notification notification = buildNotification();
        mNotificationManager.notify(0, notification);
        startForeground(0, notification);

        runnable = new LogRunnable();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        stopForeground(true);
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Timber";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel mChannel = null;

            mChannel = new NotificationChannel("AIDLTEST", name, importance);

            manager.createNotificationChannel(mChannel);
        }
    }

    private Notification buildNotification() {
        final String albumName = "lee";
        final String artistName = "marvel";
        String text = TextUtils.isEmpty(albumName)
                ? artistName : artistName + " - " + albumName;


        Intent nowPlayingIntent = new Intent(this, MainActivity.class);
        PendingIntent clickIntent = PendingIntent.getActivity(this, 0, nowPlayingIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "AIDLTEST")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(clickIntent)
                .setOngoing(true)
                .setContentTitle(albumName)
                .setContentText(text)
                .setWhen(System.currentTimeMillis());


        Notification n = builder.build();

        return n;
    }


    private class LogRunnable implements Runnable {

        @Override
        public void run() {
            Log.e(TAG, "LogRunnable is running");
            handler.postDelayed(runnable, 3000);
        }
    }
}
