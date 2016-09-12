package mindjet.com.numbertool;

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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.Biz.InfoItemBiz;
import mindjet.com.numbertool.View.ClearEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private InputMethodManager inputMethodManager;

    private InfoItemBiz infoItemBiz;
    private MyHandler handler;

    private ClearEditText et_input;
    private Button btn_search;
    private static ProgressBar pb;
    private static TextView tv_province, tv_city, tv_areacode, tv_zip, tv_company, tv_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //immersiveMode();
        initUI();

        handler = new MyHandler();
        infoItemBiz = new InfoItemBiz(this, "myTable", handler);

        inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    private void initUI() {

        et_input = (ClearEditText) findViewById(R.id.et_input);
        btn_search = (Button) findViewById(R.id.btn_search);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        tv_province = (TextView) findViewById(R.id.tv_province);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_areacode = (TextView) findViewById(R.id.tv_areacode);
        tv_zip = (TextView) findViewById(R.id.tv_zip);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_type = (TextView) findViewById(R.id.tv_type);

        btn_search.setOnClickListener(this);
        pb.setVisibility(View.GONE);

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
    private void event_btn_search(String s) {

        if (s.equals("")) {

            Toast.makeText(this, "号码不能为空", Toast.LENGTH_SHORT).show();

        } else {

            //hide the keyboard
            inputMethodManager.hideSoftInputFromWindow(et_input.getWindowToken(),0);
            pb.setVisibility(View.VISIBLE);

            infoItemBiz.getData(s);

        }

    }


    static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            setText((InfoItem) msg.obj);

        }


        private void setText(InfoItem info){

            pb.setVisibility(View.GONE);

            tv_province.setText(info.getProvince());
            tv_city.setText(info.getCity());
            tv_areacode.setText(info.getAreacode());
            tv_zip.setText(info.getZip());
            tv_company.setText(info.getCompany());
            tv_type.setText(info.getType());

        }

    }

}
