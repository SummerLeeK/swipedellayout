package study.lee.aidlproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;

import study.lee.aidlproject.helper.SizeUtils;

public class CustomToastUtil {

    private static Toast toast;


    public static void showShort(Context context, @LayoutRes int layout) {
        View toastView = LayoutInflater.from(context).inflate(layout, null);

        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);


        try {
            Field mTN = toast.getClass().getDeclaredField("mTN");
            mTN.setAccessible(true);
            Object mTNObj = mTN.get(toast);
            Field mParams = mTNObj.getClass().getDeclaredField("mParams");
            mParams.setAccessible(true);
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) mParams.get(mTNObj);

            params.width = -1;//-1表示全屏, 你也可以设置任意宽度.

            params.height = SizeUtils.getStatusBarHeight(context) + SizeUtils.dp2px(context, 56);// (int) dpToPx(context, T_HEIGHT);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        toast.setView(toastView);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.getView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//设置Toast可以布局到系统状态栏的下面
        toast.show();
    }
}
