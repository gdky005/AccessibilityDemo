package gdky005.accesibilitydemo;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Android 辅助功能
 * <p>
 * Created by WangQing on 2016/10/25.
 */

public class WQAccessibilityService extends AccessibilityService {

    private static final String TAG = "WQAccessibilityService";
    private static final String TEXTVIEW = TextView.class.getCanonicalName();
    private static final String BUTTON = Button.class.getCanonicalName();
    private static final String EDITTEXT = EditText.class.getCanonicalName();
    private static final String LISTVIEW = ListView.class.getCanonicalName();

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                Log.i(TAG, "onAccessibilityEvent:  找到搜索按钮了，而且我要点击下");

//                AccessibilityNodeInfo node = (AccessibilityNodeInfo) msg.obj;
//                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                node.performAction()


//                AccessibilityNodeInfo node = (AccessibilityNodeInfo) msg.obj;
//                Bundle arguments = new Bundle();
//                arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "989786786");
//                node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);


                AccessibilityNodeInfo node = (AccessibilityNodeInfo) msg.obj;
                node.performAction(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD);
            }

        }
    };


    /**
     * 通过这个函数可以接收系统发送来的AccessibilityEvent，接收来的AccessibilityEvent是经过过滤的，过滤是在配置工作时设置的。
     * <p>
     * 这是异步通知
     *
     * @param event
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        nodeInfo(event);
    }

    private void nodeInfo(AccessibilityEvent event) {
        AccessibilityNodeInfo nodeInfo = event.getSource();
        if (nodeInfo != null) {
            if (getRootInActiveWindow() == null)
                return;
//            checkName(TEXTVIEW, "搜索");


//            checkName(EDITTEXT, "com.tencent.mm:id/fo");


            checkName(LISTVIEW, "com.tencent.mm:id/bfr");


//            //通过文字找到当前的节点
//            List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText("搜索");
//            for (int i = 0; i < nodes.size(); i++) {
//                AccessibilityNodeInfo node = nodes.get(i);
//                // 执行按钮点击行为
//                if (node.getClassName().equals("android.widget.TextView") && node.isEnabled()) {
//                    Log.i(TAG, "onAccessibilityEvent:  找到搜索按钮了，而且我要点击下");
//
//                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//                }
//            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void checkName(String type, String keyWorld) {
        //通过文字找到当前的节点
//        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(keyWorld);
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByViewId(keyWorld);
        for (int i = 0; i < nodes.size(); i++) {
            AccessibilityNodeInfo node = nodes.get(i);
            if (node.getClassName().equals(type) && node.isEnabled()) {

                handler.removeMessages(0);

                Message msg = handler.obtainMessage();
                msg.what = 0;
                msg.obj = node;

                handler.sendMessageDelayed(msg, 3000);

//                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);

//
            }
        }
    }

    /**
     * 这个在系统想要中断AccessibilityService返给的响应时会调用。在整个生命周期里会被调用多次。
     */
    @Override
    public void onInterrupt() {

    }


    @SuppressLint("NewApi")
    private void findAndPerformAction(String text) {
        // 查找当前窗口中包含“安装”文字的按钮
        if (getRootInActiveWindow() == null)
            return;
        //通过文字找到当前的节点
        List<AccessibilityNodeInfo> nodes = getRootInActiveWindow().findAccessibilityNodeInfosByText(text);
        for (int i = 0; i < nodes.size(); i++) {
            AccessibilityNodeInfo node = nodes.get(i);
            // 执行按钮点击行为
            if (node.getClassName().equals("android.widget.Button") && node.isEnabled()) {
                node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        }
    }


    /**
     * 在系统将要关闭这个AccessibilityService会被调用。在这个方法中进行一些释放资源的工作。
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
