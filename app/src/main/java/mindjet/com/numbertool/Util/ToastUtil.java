package mindjet.com.numbertool.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Mindjet
 * @date 2016/9/13
 */
public class ToastUtil {

    public static void show(Context context, String content, int length) {

        if (length == 0)
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, content, Toast.LENGTH_LONG).show();

    }

}
