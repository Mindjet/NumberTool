package mindjet.com.numbertool.Biz;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.Util.DecodeUtil;

/**
 * @author Mindjet
 * @date 2016/9/10
 */
public class NumberBiz {

    private Handler handler;
    private String pNum = null;

    public NumberBiz(Handler handler) {
        this.handler = handler;
    }


    public void getData(String pNum){

        this.pNum = pNum;

        new Thread(){
            @Override
            public void run() {
                fetch_send();
            }
        }.start();

    }


    private void fetch_send() {

        //http://apis.juhe.cn/mobile/get?phone=13429667914&key=您申请的KEY
        String myUrl = Constants.API_PREFIX + "?" + "phone=" + pNum + "&" + "key=" + Constants.APPKEY;
        String result = fetchInBackgound(myUrl);

        InfoItem infoItem = DecodeUtil.Json2InfoItem(result);
        Message message = new Message();
        message.obj = infoItem;
        handler.sendMessage(message);

    }

    private String fetchInBackgound(String myUrl) {

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
