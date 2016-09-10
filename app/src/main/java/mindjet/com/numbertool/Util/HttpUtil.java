package mindjet.com.numbertool.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Mindjet
 * @date 2016/9/10
 */
public class HttpUtil {

    public static String fetchInBackgound(String myUrl) {

        HttpURLConnection conn;
        StringBuilder builder = new StringBuilder("");
        InputStream in = null;
        InputStreamReader isr = null;

        try {

            URL url = new URL(myUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);

            in = conn.getInputStream();
            isr = new InputStreamReader(in);

            char[] buff = new char[1024];
            int len = 0;

            while ((len = isr.read(buff)) != -1) {

                builder.append(buff, 0, len);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                if (isr != null) isr.close();
                if (in != null) in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return builder.toString();

    }

}
