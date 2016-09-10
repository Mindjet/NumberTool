package mindjet.com.numbertool.Biz;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.DataBase.InfoItemDao;
import mindjet.com.numbertool.Util.DecodeUtil;
import mindjet.com.numbertool.Util.HttpUtil;

/**
 * @author Mindjet
 * @date 2016/9/10
 */
public class InfoItemBiz {

    private Handler handler;
    private String pNum = null;
    private InfoItemDao dao;

    public InfoItemBiz(Context context, String tableName, Handler handler) {

        this.handler = handler;
        dao = new InfoItemDao(context, tableName);

    }

    public void getData(String pNum) {

        this.pNum = pNum;

        new Thread() {
            @Override
            public void run() {
                performRequest();
            }
        }.start();

    }


    private void performRequest() {

        String myUrl = Constants.API_PREFIX + "?" + "phone=" + pNum + "&" + "key=" + Constants.APPKEY;
        InfoItem infoItem;

        if (dao.isDuplicate(pNum)){         //if the number is duplicate in the database

            System.out.println("数据库已存在相同记录");
            infoItem = dao.search(pNum);

        }else {                             //if the number does not exist in the database

            String result = HttpUtil.fetchInBackgound(myUrl);
            infoItem = DecodeUtil.Json2InfoItem(result, pNum);
            dao.insert(infoItem);

        }

        Message message = new Message();
        message.obj = infoItem;
        handler.sendMessage(message);

    }

}
