package study.lee.aidlproject;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.ByteString;
import study.lee.aidlproject.helper.Book;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    //由AIDL文件生成的Java类
    private IMyAidlInterface iMyAidlInterface = null;

    //标志当前与服务端连接状况的布尔值，false为未连接，true为连接中
    private boolean mBound = false;
    private ActivityManager activityManager;
    //包含Book对象的list
    private List<Book> mBooks;

    private EditText et;
    private TextView addBookTv;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(), (String) msg.obj, Toast.LENGTH_SHORT).show();
        }
    };

    private ClientSocketThread socketThread;
    private ServerSocketThread serverSocketThread;
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

        findViewById(R.id.intent).setOnClickListener(this);

        et = findViewById(R.id.et);

        serverSocketThread = new ServerSocketThread();
        serverSocketThread.start();

    }


    private void bindService() {
        Intent intent = new Intent(this, AIDLService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }


    @Override
    protected void onStart() {
        super.onStart();
//        if (!mBound) {
//            bindService();
//        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.e("", Thread.currentThread().getName());
            }
        });


        new ClientSocketThread().start();


    }


    public class ClientSocketThread extends Thread {
        private Socket socket;

        @Override
        public void run() {
            super.run();
            sendMessage();
        }

        void sendMessage() {
            try {
                socket = new Socket("192.168.0.167", 8080);

                Log.e("clientSocket", "sendMsg 1");
                if (socket != null && socket.isConnected()) {

//                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                    outputStream.writeUTF(et.getText().toString());
//                    outputStream.write("hello".getBytes("utf-8"));
                    Log.e("clientSocket", "sendMsg 2");
                    outputStream.flush();
                    outputStream.close();

                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private class ServerSocketThread extends Thread {
        private ServerSocket serverSocket;


        void init() {
            try {

                serverSocket = new ServerSocket(8080);

                while (true) {
                    Socket acceptSocket = serverSocket.accept();

                    new ServerThread(acceptSocket).start();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            super.run();
            init();
        }
    }

    private class ServerThread extends Thread {
        private Socket acceptSocket;

        public ServerThread(Socket serverSocket) {
            this.acceptSocket = serverSocket;
        }

        @Override
        public void run() {
            super.run();

            try {
                DataInputStream dataInputStream = new DataInputStream(acceptSocket.getInputStream());

                String msg = dataInputStream.readUTF();

                if (msg != null) {
                    Log.e("ServerSocket", "receive msg\t" + msg);
                    Looper.prepare();
                    Message message = Message.obtain();
                    message.obj = new String(msg);
                    handler.sendMessage(message);
                    Looper.loop();
                }
                dataInputStream.close();
                acceptSocket.shutdownInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
