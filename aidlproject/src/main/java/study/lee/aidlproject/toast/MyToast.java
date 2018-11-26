//package study.lee.aidlproject.toast;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.os.Looper;
//import android.os.RemoteException;
//import android.support.annotation.IntDef;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.app.INotificationManager;
//import android.app.ITransientNotification;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//
//public class MyToast {
//
//    static final String TAG = "Toast";
//    static final boolean localLOGV = false;
//
//    /**
//     * @hide
//     */
//    @IntDef(prefix = {"LENGTH_"}, value = {
//            LENGTH_SHORT,
//            LENGTH_LONG
//    })
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface Duration {
//    }
//
//    /**
//     * Show the view or text notification for a short period of time.  This time
//     * could be user-definable.  This is the default.
//     *
//     * @see #setDuration
//     */
//    public static final int LENGTH_SHORT = 0;
//
//    /**
//     * Show the view or text notification for a long period of time.  This time
//     * could be user-definable.
//     *
//     * @see #setDuration
//     */
//    public static final int LENGTH_LONG = 1;
//
//    final Context mContext;
//    final MyTn mTN;
//    int mDuration;
//    View mNextView;
//
//    /**
//     * Construct an empty Toast object.  You must call {@link #setView} before you
//     * can call {@link #show}.
//     *
//     * @param context The context to use.  Usually your {@link android.app.Application}
//     *                or {@link android.app.Activity} object.
//     */
//    public MyToast(Context context) {
//        this(context, null);
//    }
//
//    /**
//     * Constructs an empty Toast object.  If looper is null, Looper.myLooper() is used.
//     *
//     * @hide
//     */
//    public MyToast(@NonNull Context context, @Nullable Looper looper) {
//        mContext = context;
//        mTN = new MyTn(context.getPackageName(), looper);
//        mTN.mY = context.getResources().getDimensionPixelSize(
//                com.android.internal.R.dimen.toast_y_offset);
//        mTN.mGravity = context.getResources().getInteger(
//                com.android.internal.R.integer.config_toastDefaultGravity);
//    }
//
//    /**
//     * Show the view for the specified duration.
//     */
//    public void show() {
//        if (mNextView == null) {
//            throw new RuntimeException("setView must have been called");
//        }
//
//        INotificationManager service = getService();
//        String pkg = mContext.getOpPackageName();
//        MyTn tn = mTN;
//        tn.mNextView = mNextView;
//
//        try {
//            service.enqueueToast(pkg, tn, mDuration);
//        } catch (RemoteException e) {
//            // Empty
//        }
//    }
//
//    /**
//     * Close the view if it's showing, or don't show it if it isn't showing yet.
//     * You do not normally have to call this.  Normally view will disappear on its own
//     * after the appropriate duration.
//     */
//    public void cancel() {
//        mTN.cancel();
//    }
//
//    /**
//     * Set the view to show.
//     *
//     * @see #getView
//     */
//    public void setView(View view) {
//        mNextView = view;
//    }
//
//    /**
//     * Return the view.
//     *
//     * @see #setView
//     */
//    public View getView() {
//        return mNextView;
//    }
//
//    /**
//     * Set how long to show the view for.
//     *
//     * @see #LENGTH_SHORT
//     * @see #LENGTH_LONG
//     */
//    public void setDuration(@Duration int duration) {
//        mDuration = duration;
//        mTN.mDuration = duration;
//    }
//
//    /**
//     * Return the duration.
//     *
//     * @see #setDuration
//     */
//    @Duration
//    public int getDuration() {
//        return mDuration;
//    }
//
//    /**
//     * Set the margins of the view.
//     *
//     * @param horizontalMargin The horizontal margin, in percentage of the
//     *                         container width, between the container's edges and the
//     *                         notification
//     * @param verticalMargin   The vertical margin, in percentage of the
//     *                         container height, between the container's edges and the
//     *                         notification
//     */
//    public void setMargin(float horizontalMargin, float verticalMargin) {
//        mTN.mHorizontalMargin = horizontalMargin;
//        mTN.mVerticalMargin = verticalMargin;
//    }
//
//    /**
//     * Return the horizontal margin.
//     */
//    public float getHorizontalMargin() {
//        return mTN.mHorizontalMargin;
//    }
//
//    /**
//     * Return the vertical margin.
//     */
//    public float getVerticalMargin() {
//        return mTN.mVerticalMargin;
//    }
//
//    /**
//     * Set the location at which the notification should appear on the screen.
//     *
//     * @see android.view.Gravity
//     * @see #getGravity
//     */
//    public void setGravity(int gravity, int xOffset, int yOffset) {
//        mTN.mGravity = gravity;
//        mTN.mX = xOffset;
//        mTN.mY = yOffset;
//    }
//
//    /**
//     * Get the location at which the notification should appear on the screen.
//     *
//     * @see android.view.Gravity
//     * @see #getGravity
//     */
//    public int getGravity() {
//        return mTN.mGravity;
//    }
//
//    /**
//     * Return the X offset in pixels to apply to the gravity's location.
//     */
//    public int getXOffset() {
//        return mTN.mX;
//    }
//
//    /**
//     * Return the Y offset in pixels to apply to the gravity's location.
//     */
//    public int getYOffset() {
//        return mTN.mY;
//    }
//
//    /**
//     * Gets the LayoutParams for the Toast window.
//     *
//     * @hide
//     */
//    public WindowManager.LayoutParams getWindowParams() {
//        return mTN.mParams;
//    }
//
//    /**
//     * Make a standard toast that just contains a text view.
//     *
//     * @param context  The context to use.  Usually your {@link android.app.Application}
//     *                 or {@link android.app.Activity} object.
//     * @param text     The text to show.  Can be formatted text.
//     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
//     *                 {@link #LENGTH_LONG}
//     */
//    public static MyToast makeText(Context context, CharSequence text, @Duration int duration) {
//        return makeText(context, null, text, duration);
//    }
//
//    /**
//     * Make a standard toast to display using the specified looper.
//     * If looper is null, Looper.myLooper() is used.
//     *
//     * @hide
//     */
//    public static MyToast makeText(@NonNull Context context, @Nullable Looper looper,
//                                 @NonNull CharSequence text, @Duration int duration) {
//        Toast result = new Toast(context, looper);
//
//        LayoutInflater inflate = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflate.inflate(com.android.internal.R.layout.transient_notification, null);
//        TextView tv = (TextView) v.findViewById(com.android.internal.R.id.message);
//        tv.setText(text);
//
//        result.mNextView = v;
//        result.mDuration = duration;
//
//        return result;
//    }
//
//    /**
//     * Make a standard toast that just contains a text view with the text from a resource.
//     *
//     * @param context  The context to use.  Usually your {@link android.app.Application}
//     *                 or {@link android.app.Activity} object.
//     * @param resId    The resource id of the string resource to use.  Can be formatted text.
//     * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or
//     *                 {@link #LENGTH_LONG}
//     * @throws Resources.NotFoundException if the resource can't be found.
//     */
//    public static MyToast makeText(Context context, @StringRes int resId, @Duration int duration)
//            throws Resources.NotFoundException {
//        return makeText(context, context.getResources().getText(resId), duration);
//    }
//
//    /**
//     * Update the text in a Toast that was previously created using one of the makeText() methods.
//     *
//     * @param resId The new text for the Toast.
//     */
//    public void setText(@StringRes int resId) {
//        setText(mContext.getText(resId));
//    }
//
//    /**
//     * Update the text in a Toast that was previously created using one of the makeText() methods.
//     *
//     * @param s The new text for the Toast.
//     */
//    public void setText(CharSequence s) {
//        if (mNextView == null) {
//            throw new RuntimeException("This Toast was not created with Toast.makeText()");
//        }
//        TextView tv = mNextView.findViewById(com.android.internal.R.id.message);
//        if (tv == null) {
//            throw new RuntimeException("This Toast was not created with Toast.makeText()");
//        }
//        tv.setText(s);
//    }
//
//    // =======================================================================================
//    // All the gunk below is the interaction with the Notification Service, which handles
//    // the proper ordering of these system-wide.
//    // =======================================================================================
//
//    private static INotificationManager sService;
//
//    static private INotificationManager getService() {
//        if (sService != null) {
//            return sService;
//        }
//        sService = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
//        return sService;
//    }
//}
