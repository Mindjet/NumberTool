package mindjet.com.numbertool.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Mindjet
 * @date 2016/9/13
 */
public class NetworkUtil {

    public static boolean isNetworkConnected(Context context){

        return isCelluarConnected(context)||isWifiConnected(context);

    }

    private static boolean isCelluarConnected(Context context){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return info!=null&&info.isConnected();

    }

    private static boolean isWifiConnected(Context context){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return info!=null&&info.isConnected();

    }


}
