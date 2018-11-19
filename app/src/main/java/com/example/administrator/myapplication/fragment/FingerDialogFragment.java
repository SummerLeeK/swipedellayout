package com.example.administrator.myapplication.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Paint;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.utils.CryptoObjectHelper;

/**
 * Created by Administrator on 2018/9/10 0010.
 */

public class FingerDialogFragment extends DialogFragment {

    private View root;

    private CancellationSignal cancellationSignal;

    private TextView tips;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.dialog_finger_print, null);
        tips = root.findViewById(R.id.tips);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cancellationSignal = new CancellationSignal();
            cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                @Override
                public void onCancel() {
                    Toast.makeText(getActivity(), "cancel", Toast.LENGTH_SHORT).show();
                }
            });

            try {

                startFingerPrint();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return root;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startFingerPrint() throws Exception {

        FingerprintManager fingerprintManager = getFingerprintManager(getActivity());

        if (fingerprintManager != null && fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
            fingerprintManager.authenticate(new CryptoObjectHelper().buildCryptoObject(), cancellationSignal, 0,
                    new MyAuthenticationCallback(new Listerer() {
                        @Override
                        public void onAuthenticationError(int errMsgId, CharSequence errString) {
                            tips.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                            tips.setText(errString);
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            tips.setText("识别失败，请重试");
                        }

                        @Override
                        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                            tips.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                            tips.setText(helpString);
                        }

                        @Override
                        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                            tips.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                            tips.setText("识别成功");
                        }
                    }), null);
        }
    }


    @Override
    public void dismiss() {
        super.dismiss();
        cancelFingerPrint();
    }

    private void cancelFingerPrint() {
        FingerprintManager fingerprintManager = getFingerprintManager(getActivity());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (fingerprintManager != null && fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
                cancellationSignal.cancel();
            }
        }
    }


    public static FingerprintManager getFingerprintManager(Context context) {
        FingerprintManager fingerprintManager = null;
        try {
            fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
        } catch (Throwable e) {
            Log.e("TAG", "have not class FingerprintManager");
        }
        return fingerprintManager;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private static class MyAuthenticationCallback extends FingerprintManager.AuthenticationCallback {

        Listerer listerer;

        public MyAuthenticationCallback(Listerer listerer) {
            this.listerer = listerer;
        }

        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);

            listerer.onAuthenticationError(errMsgId, errString);
        }


        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();

            listerer.onAuthenticationFailed();
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            super.onAuthenticationHelp(helpMsgId, helpString);

            listerer.onAuthenticationHelp(helpMsgId, helpString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);

            listerer.onAuthenticationSucceeded(result);
        }
    }

    interface Listerer {
        void onAuthenticationError(int errMsgId, CharSequence errString);

        void onAuthenticationFailed();

        void onAuthenticationHelp(int helpMsgId, CharSequence helpString);


        void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result);
    }
}
