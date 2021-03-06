package mindjet.com.numbertool.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import mindjet.com.numbertool.Bean.InfoItem;

/**
 * @author Mindjet
 * @date 2016/9/10
 */
public class DecodeUtil {


    public static InfoItem Json2InfoItem(String json, String number) {

//example

//    {
//            "resultcode":"200",
//            "reason":"Return Successd!",
//            "result":{
//                "province":"浙江",
//                "city":"杭州",
//                "areacode":"0571",
//                "zip":"310000",
//                "company":"中国移动",
//                "card":"移动动感地带卡"
//             }
//    }


        InfoItem info = new InfoItem();

        info.setNumber(number);

        try {

            JSONObject object = new JSONObject(json);
            String resultcode = object.getString("resultcode");

            if (resultcode.equals("200")) {

                SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm");
                Date date = new Date(System.currentTimeMillis());
                String mDate = format.format(date);

                JSONObject detail = object.getJSONObject("result");
                info.setProvince(detail.getString("province"));
                info.setCity(detail.getString("city"));
                info.setAreacode(detail.getString("areacode"));
                info.setZip(detail.getString("zip"));
                info.setCompany(detail.getString("company"));
                info.setType(detail.getString("card"));
                info.setDate(mDate);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return info;
    }

}
