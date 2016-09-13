package mindjet.com.numbertool.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Mindjet
 * @date 2016/9/13
 */
public class ToastUtil {

    private static Toast mToast;

    public static void show(Context context, String content, int length) {


        if (mToast==null){

            mToast = Toast.makeText(context, content, Toast.LENGTH_SHORT);

        }else{

            mToast.setText(content);
            mToast.setDuration(Toast.LENGTH_SHORT);

        }

        mToast.show();


    }

}
