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
 * @date 2016/9/18
 */
public class SlideMenuAdapter extends BaseAdapter {

    private String[] data = {"清空数据库", "版本信息", "关于我"};

    private LayoutInflater inflater;

    public SlideMenuAdapter(Context context) {

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

        if (view == null)
            view = inflater.inflate(R.layout.widget_slidemenu_item, viewGroup, false);

        TextView tv_text = (TextView) view.findViewById(R.id.tv_sildemenu_item_text);
        tv_text.setText(data[i]);

        return view;

    }
}
