package mindjet.com.numbertool.Util;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * @author Mindjet
 * @date 2016/9/12
 */
public class AnimUtil {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void vibrate(View view, int magnitude, int time){

        Animation animation = new TranslateAnimation(0,2*magnitude,0,0);
        animation.setInterpolator(new CycleInterpolator(time));
        animation.setDuration(500);

        view.startAnimation(animation);

    }

}
