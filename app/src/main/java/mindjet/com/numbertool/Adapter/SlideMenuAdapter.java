package mindjet.com.numbertool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mindjet.com.numbertool.R;

/**
 * @author Mindjet
 * @date 2016/9/18
 */
public class SlideMenuAdapter extends BaseAdapter {

    private String[] text_data = {"清空数据库", "版本信息", "关于我"};
    private int[] image_data = {R.mipmap.clear, R.mipmap.version, R.mipmap.about};

    private LayoutInflater inflater;

    public SlideMenuAdapter(Context context) {

        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return text_data.length;
    }

    @Override
    public Object getItem(int i) {
        return text_data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view==null){

            view = inflater.inflate(R.layout.widget_slidemenu_item,viewGroup,false);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.tv_slidemenu_item_text);
            holder.iv = (ImageView) view.findViewById(R.id.iv_slidemenu_item_image);

            view.setTag(holder);

        }else {

            holder = (ViewHolder) view.getTag();

        }

        holder.tv.setText(text_data[i]);
        holder.iv.setImageResource(image_data[i]);

        return view;

    }

    class ViewHolder {

        public TextView tv;
        private ImageView iv;

    }

}
