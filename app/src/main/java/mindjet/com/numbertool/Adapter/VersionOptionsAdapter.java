package mindjet.com.numbertool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import mindjet.com.numbertool.R;

/**
 * @author Mindjet
 * @date 2016/9/19
 */
public class VersionOptionsAdapter extends BaseAdapter {

    private String[] data = {"检查更新","版本说明","意见反馈"};
    private LayoutInflater inflater;

    public VersionOptionsAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view==null){

            view = inflater.inflate(R.layout.widget_versioninfo_item,viewGroup,false);
            holder = new ViewHolder();

            holder.tv_option = (TextView) view.findViewById(R.id.tv_versioninfo_option);

            view.setTag(holder);

        }else {

            holder = (ViewHolder) view.getTag();

        }

        holder.tv_option.setText(data[i]);

        return view;
    }


    class ViewHolder{

        public TextView tv_option;

    }

}
