package mindjet.com.numbertool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import mindjet.com.numbertool.Adapter.VersionOptionsAdapter;
import mindjet.com.numbertool.Util.ToastUtil;

/**
 * @author Mindjet
 * @date 2016/9/19
 */
public class VersionActivity extends AppCompatActivity implements View.OnClickListener, AdapterView
        .OnItemClickListener {


    private TextView tv_back;
    private ListView lv_options;
    private VersionOptionsAdapter versionOptionsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        initUI();

    }

    private void initUI() {

        tv_back = (TextView) findViewById(R.id.tv_version_back2main);
        tv_back.setOnClickListener(this);

        lv_options = (ListView) findViewById(R.id.lv_version_options);
        versionOptionsAdapter = new VersionOptionsAdapter(this);
        lv_options.setAdapter(versionOptionsAdapter);
        lv_options.setOnItemClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tv_version_back2main:
                finish();
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        switch (i) {

            case 0:
                //check update
                ToastUtil.show(this, "check update", 0);
                break;

            case 1:
                //information of current version
                break;

            case 2:
                //feedback
                break;

        }

    }


    @Override
    public void onBackPressed() {
        finish();
    }

}
