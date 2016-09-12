package mindjet.com.numbertool.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

/**
 * @author Mindjet
 * @date 2016/9/12
 */
public class ClearEditText extends EditText implements TextWatcher, OnFocusChangeListener {


    private Drawable cross;
    private boolean focused;


    public ClearEditText(Context context) {
        this(context, null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initUI();

    }

    private void initUI() {

        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.clearFocus();

        cross = this.getCompoundDrawables()[2];

        //hide the cross image in the first place
        this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

        this.setOnFocusChangeListener(this);
        this.addTextChangedListener(this);

    }

    /**
     * implement the function of the "cross" button.
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP) {

            int x = (int) event.getX();
            int width = this.getWidth();

            if (width - x <= this.getCompoundPaddingRight()) {

                if (!TextUtils.isEmpty(this.getText().toString())) {

                    this.setText("");

                }

            }

        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View view, boolean focused) {

        this.focused = focused;

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

        // Before showing the cross, the view must be focused,
        // or all the drawables will be set null at the very first time.
        if (focused) {

            if (TextUtils.isEmpty(text)) {

                this.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

            } else {

                this.setCompoundDrawablesWithIntrinsicBounds(null, null, cross, null);

            }

        }


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
