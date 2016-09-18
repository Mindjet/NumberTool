package mindjet.com.numbertool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.DataBase.InfoItemDao;
import mindjet.com.numbertool.Listener.DeleteListener;
import mindjet.com.numbertool.R;

/**
 * @author Mindjet
 * @date 2016/9/14
 */
public class HistoryAdapter extends BaseAdapter {

    private List<InfoItem> infoItemList;
    private InfoItemDao infoItemDao;
    private LayoutInflater inflater;

    public HistoryAdapter(Context context, InfoItemDao dao) {

        infoItemList = new ArrayList<>();
        infoItemDao = dao;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return infoItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return infoItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();

            view = inflater.inflate(R.layout.widget_history_item, viewGroup, false);
            holder.number = (TextView) view.findViewById(R.id.tv_history_item_number);
            holder.date = (TextView) view.findViewById(R.id.tv_history_item_time);
            holder.delete = (ImageView) view.findViewById(R.id.iv_history_item_delete);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }

        holder.number.setText(infoItemList.get(i).getNumber());
        holder.date.setText(infoItemList.get(i).getDate());
        holder.delete.setOnClickListener(new DeleteListener(this, infoItemList.get(i).getNumber(), infoItemDao));

        return view;
    }

    public void addInfoItem(InfoItem infoItem) {

        infoItemList.add(infoItem);
        notifyDataSetChanged();

    }

    public void addFromDB() {

        infoItemList = infoItemDao.getAll();
        notifyDataSetChanged();

    }

    public void delInfoItem(String pNum) {

        for (InfoItem info :
                infoItemList) {
            if (info.getNumber().equals(pNum)){
                infoItemList.remove(info);
                break;
            }
        }

        notifyDataSetChanged();

    }

    public void delInfoItemList(){

        infoItemList.clear();
        notifyDataSetChanged();

    }

    public List<InfoItem> getInfoItemList() {
        return this.infoItemList;
    }


    class ViewHolder {

        public TextView number;
        public TextView date;
        public ImageView delete;

    }


}
