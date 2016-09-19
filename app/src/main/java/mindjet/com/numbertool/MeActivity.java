package mindjet.com.numbertool;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * @author Mindjet
 * @date 2016/9/19
 */
public class MeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_back;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        tv_back = (TextView) findViewById(R.id.tv_me_back2main);
        tv_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.tv_me_back2main)
            finish();

    }


    @Override
    public void onBackPressed() {
        finish();
    }
}
