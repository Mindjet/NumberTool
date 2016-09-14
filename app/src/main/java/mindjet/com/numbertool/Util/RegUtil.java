package mindjet.com.numbertool.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mindjet
 * @date 2016/9/14
 */
public class RegUtil {


    public static boolean isPhoneNumber(String pNum){

        Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(pNum);

        return matcher.matches();

    }


}
