package study.lee.aidlproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import study.lee.aidlproject.helper.Book;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    //由AIDL文件生成的Java类
    private IMyAidlInterface iMyAidlInterface = null;

    //标志当前与服务端连接状况的布尔值，false为未连接，true为连接中
    private boolean mBound = false;

    //包含Book对象的list
    private List<Book> mBooks;

    private TextView addBookTv;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            mBound = true;
            try {
                Log.e(TAG, "onServiceConnected\t" + iMyAidlInterface.getBooks().size());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
            Log.e(TAG, "onServiceDisconnected\t");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBookTv = findViewById(R.id.add_book_tv);

        addBookTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBound) {
                    Book book = new Book();
                    book.setAuthor("lee");
                    book.setName("marvel");
                    book.setPrice("19.9");
                    try {
                        iMyAidlInterface.addBook(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void bindService() {
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!mBound) {
            bindService();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
