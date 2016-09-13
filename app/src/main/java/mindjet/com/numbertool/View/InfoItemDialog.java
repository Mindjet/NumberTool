package mindjet.com.numbertool.View;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import mindjet.com.numbertool.Bean.InfoItem;
import mindjet.com.numbertool.R;

/**
 * @author Mindjet
 * @date 2016/9/13
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class InfoItemDialog extends DialogFragment {

    private TextView tv_province, tv_city, tv_areacode, tv_zip, tv_company, tv_type;
    private ProgressBar pb;
    private View convertView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.widget_detail, container, false);
            tv_province = (TextView) convertView.findViewById(R.id.tv_province);
            tv_city = (TextView) convertView.findViewById(R.id.tv_city);
            tv_areacode = (TextView) convertView.findViewById(R.id.tv_areacode);
            tv_zip = (TextView) convertView.findViewById(R.id.tv_zip);
            tv_company = (TextView) convertView.findViewById(R.id.tv_company);
            tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            pb = (ProgressBar) convertView.findViewById(R.id.progressBar);

        } else {

            //if we use cached view, its relation with its parent must be removed.
            ViewGroup parent = (ViewGroup) convertView.getParent();
            if (parent != null) {

                parent.removeView(convertView);

            }

        }

        return convertView;
    }

    public void updateTextViews(InfoItem infoItem) {

        tv_province.setText(getString(R.string.item_province, infoItem.getProvince()));
        tv_city.setText(getString(R.string.item_city, infoItem.getCity()));
        tv_areacode.setText(getString(R.string.item_areacode, infoItem.getAreacode()));
        tv_zip.setText(getString(R.string.item_zip, infoItem.getZip()));
        tv_company.setText(getString(R.string.item_company, infoItem.getCompany()));
        tv_type.setText(getString(R.string.item_type, infoItem.getType()));
        pb.setVisibility(View.GONE);

    }


}
