package mindjet.com.numbertool;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.idescout.sql.SqlScoutServer;

import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.Biz.InfoItemBiz;
import mindjet.com.numbertool.Util.AnimUtil;
import mindjet.com.numbertool.View.ClearEditText;
import mindjet.com.numbertool.View.InfoItemDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private InputMethodManager inputMethodManager;
    private FragmentManager fragmentManager;

    private InfoItemBiz infoItemBiz;
    private MyHandler handler;

    private ClearEditText et_input;
    private Button btn_search;
    private static InfoItemDialog dialog;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqlScoutServer.create(this,getPackageName());

        //immersiveMode();
        initUI();

        handler = new MyHandler();
        infoItemBiz = new InfoItemBiz(this, "myTable", handler);

        inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        fragmentManager = getFragmentManager();

    }

    private void initUI() {

        et_input = (ClearEditText) findViewById(R.id.et_input);
        btn_search = (Button) findViewById(R.id.btn_search);
        dialog = new InfoItemDialog();

        btn_search.setOnClickListener(this);

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

        }

    }

    //triggered when the search button is clicked
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void event_btn_search(String s) {

        if (s.equals("")) {

            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();

            //shake the edittext to remind user.
            AnimUtil.vibrate(et_input, 2, 4);

        } else {

            //hide the keyboard
            inputMethodManager.hideSoftInputFromWindow(et_input.getWindowToken(), 0);

            //show the dialog of details
            dialog.show(fragmentManager, "SHOW_DIALOG");

            //start fetching data from network or database
            infoItemBiz.getData(s);

        }


    }

    static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            dialog.updateTextViews((InfoItem) msg.obj);

        }


    }

}
