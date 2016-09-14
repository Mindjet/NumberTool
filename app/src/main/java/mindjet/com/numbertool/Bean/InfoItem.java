package mindjet.com.numbertool.Bean;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Objects;

/**
 * @author Mindjet
 * @date 2016/9/10
 */
public class InfoItem {

    private String number = "";
    private String province = "";
    private String city = "";
    private String areacode = "";
    private String zip = "";
    private String company = "";
    private String type = "";
    private String date = "";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object obj) {
        return Objects.equals(this.getNumber(), ((InfoItem) obj).getNumber());
    }

    @Override
    public String toString() {
        return "[number=" + number + ", province=" + province + ", city=" + city + ", areacode=" + areacode + ", " +
                "zip=" + zip + ", company=" + company + ", type=" + type + ", date=" + date + "]";
    }
}
