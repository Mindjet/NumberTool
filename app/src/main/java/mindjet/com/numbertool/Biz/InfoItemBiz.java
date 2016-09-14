package mindjet.com.numbertool.Biz;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import mindjet.com.numbertool.Adapter.HistoryAdapter;
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
    private HistoryAdapter adapter;

    public InfoItemBiz(Context context, String tableName, Handler handler, HistoryAdapter adapter) {

        this.handler = handler;
        this.adapter = adapter;
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
        int fromDB;

        InfoItem infoItem;

        if (dao.isDuplicate(pNum)) {         //the number exists in the database.

            infoItem = dao.search(pNum);
            fromDB = 1;

        } else {                             //the number does not exist in the database.

            String result = HttpUtil.fetchInBackgound(myUrl);
            infoItem = DecodeUtil.Json2InfoItem(result, pNum);
            fromDB = 0;

            //add item to the database
            dao.insert(infoItem);

            //Do not notifyDataSetChanged here, it must be executed in the main thread.

        }

        Message message = new Message();
        message.what = fromDB;
        message.obj = infoItem;
        handler.sendMessage(message);

    }

}
