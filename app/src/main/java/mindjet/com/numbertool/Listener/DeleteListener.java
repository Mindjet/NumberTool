package mindjet.com.numbertool.Listener;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import mindjet.com.numbertool.Adapter.HistoryAdapter;
import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.DataBase.InfoItemDao;

/**
 * @author Mindjet
 * @date 2016/9/14
 */
public class DeleteListener implements View.OnClickListener {

    private String pNum;
    private InfoItemDao dao;
    private HistoryAdapter adapter;

    public DeleteListener(HistoryAdapter adapter, String pNum, InfoItemDao dao) {

        this.adapter = adapter;
        this.pNum = pNum;
        this.dao = dao;

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View view) {

        InfoItem infoItem = dao.search(pNum);
        if (infoItem != null) {

            System.out.println(infoItem);

            dao.delete(pNum);
            adapter.delInfoItem(infoItem);
        }

    }
}
