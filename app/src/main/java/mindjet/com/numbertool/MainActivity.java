package mindjet.com.numbertool;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import mindjet.com.numbertool.Adapter.HistoryAdapter;
import mindjet.com.numbertool.Adapter.SlideMenuAdapter;
import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.Biz.Constants;
import mindjet.com.numbertool.Biz.InfoItemBiz;
import mindjet.com.numbertool.DataBase.InfoItemDao;
import mindjet.com.numbertool.Util.AnimUtil;
import mindjet.com.numbertool.Util.NetworkUtil;
import mindjet.com.numbertool.Util.RegUtil;
import mindjet.com.numbertool.Util.ToastUtil;
import mindjet.com.numbertool.View.ClearEditText;
import mindjet.com.numbertool.View.InfoItemDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private InputMethodManager inputMethodManager;
    private FragmentManager fragmentManager;

    private InfoItemBiz infoItemBiz;
    private InfoItemDao infoItemDao;
    private MyHandler handler;
    private static HistoryAdapter historyAdapter;
    private SlideMenuAdapter slideMenuAdapter;

    private DrawerLayout drawerLayout;
    private ClearEditText et_input;
    private Button btn_search;
    private ImageView iv_toggle_drawer;
    private ListView lv_slidemenu, lv_history;
    private static InfoItemDialog dialog;

    private boolean isDrawerOpen = false;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //immersiveMode();

        handler = new MyHandler();

        infoItemDao = new InfoItemDao(this, Constants.TABLE_NAME);
        historyAdapter = new HistoryAdapter(this, infoItemDao);
        slideMenuAdapter = new SlideMenuAdapter(this);
        infoItemBiz = new InfoItemBiz(this, Constants.TABLE_NAME, handler);

        inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        fragmentManager = getFragmentManager();

        initUI();

    }

    private void initUI() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);     //drawer layout
        lv_slidemenu = (ListView) findViewById(R.id.lv_slidemenu);
        iv_toggle_drawer = (ImageView) findViewById(R.id.iv_toggle_drawer);
        et_input = (ClearEditText) findViewById(R.id.et_input);
        btn_search = (Button) findViewById(R.id.btn_search);
        lv_history = (ListView) findViewById(R.id.lv_history);
        dialog = new InfoItemDialog();

        iv_toggle_drawer.setOnClickListener(this);
        btn_search.setOnClickListener(this);

        lv_history.setAdapter(historyAdapter);
        lv_history.setOnItemClickListener(this);
        historyAdapter.addFromDB();     //initialize the data for adapter at the first time.

        lv_slidemenu.setAdapter(slideMenuAdapter);
        lv_slidemenu.setOnItemClickListener(this);

    }

    private void immersiveMode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams
                    .FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_search:
                event_btn_search(et_input.getText().toString());
                break;

            case R.id.iv_toggle_drawer:
                event_iv_toggle_drawer();
                break;

        }

    }

    private void event_iv_toggle_drawer() {

        if (!isDrawerOpen) {

            drawerLayout.openDrawer(lv_slidemenu);

            //hide the keyboard
            inputMethodManager.hideSoftInputFromWindow(lv_slidemenu.getWindowToken(), 0);

            isDrawerOpen = true;
        } else {
            drawerLayout.closeDrawer(lv_slidemenu);
            isDrawerOpen = false;
        }
    }

    //triggered when the search button is clicked
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void event_btn_search(String s) {

        if (s.equals("")) {

            ToastUtil.show(this, "请输入手机号码", 0);

            //shake the edittext to remind user.
            AnimUtil.vibrate(et_input, 2, 4);

        } else if (!RegUtil.isPhoneNumber(s)) {

            ToastUtil.show(this, "错误的手机号码格式", 0);

        } else {

            //hide the keyboard
            inputMethodManager.hideSoftInputFromWindow(et_input.getWindowToken(), 0);

            //if network is available or there is corresponding data in database.
            if (NetworkUtil.isNetworkConnected(this) || infoItemDao.isDuplicate(s)) {

                //show the dialog of details
                dialog.show(fragmentManager, "SHOW_DIALOG");

                //start fetching data from network or database
                infoItemBiz.getData(s);

            } else {

                ToastUtil.show(this, "请打开Wi-Fi或者蜂窝数据", 0);

            }

        }


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        int lv_id = adapterView.getId();

        if (lv_id == R.id.lv_history) {

            dialog.show(fragmentManager, "SHOW_DIALOG_HISTORY");
            InfoItem infoItem = historyAdapter.getInfoItemList().get(position);

            //updating the text must be executed in main thread
            Message message = new Message();
            message.what = Constants.MSG_FROM_DB;
            message.obj = infoItem;
            handler.sendMessage(message);

        } else if (lv_id == R.id.lv_slidemenu) {

            Intent intent;

            switch (position) {

                case 0:
                    //clear database
                    ToastUtil.show(this, "数据库已清空", 0);

                    infoItemDao.clear();
                    Message message = Message.obtain();
                    message.what = Constants.MSG_CLEAR_DB;
                    handler.sendMessage(message);

                    //clean up the edittext
                    et_input.setText("");

                    break;

                case 1:
                    //show version information
                    intent = new Intent(this, VersionActivity.class);
                    startActivity(intent);
                    break;

                case 2:
                    //show information about me
                    intent = new Intent(this,MeActivity.class);
                    startActivity(intent);
                    break;

            }

        }


    }

    static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            // if the message is about the dialog and about the history listview
            if (msg.what == Constants.MSG_FROM_DB || msg.what == Constants.MSG_FROM_NETWORK) {

                dialog.updateTextViews((InfoItem) msg.obj);

                //If the data is not from database, it's supposed to be added to the listview.
                if (msg.what == Constants.MSG_FROM_NETWORK) {
                    historyAdapter.addInfoItem((InfoItem) msg.obj);
                }

                // if the message is about clearing the database
            } else if (msg.what == Constants.MSG_CLEAR_DB) {

                historyAdapter.delInfoItemList();

            }

        }

    }

}
