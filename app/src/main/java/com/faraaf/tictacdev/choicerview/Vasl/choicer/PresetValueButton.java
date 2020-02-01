package com.faraaf.tictacdev.choicerview.Vasl.choicer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.faraaf.tictacdev.choicerview.R;

import java.util.ArrayList;


public class PresetValueButton extends RelativeLayout implements RadioCheckable {

    // Attribute Variables
    private String mValue;
    private String mUnit;

    // Variables
    private OnClickListener mOnClickListener;
    private OnTouchListener mOnTouchListener;
    private boolean mChecked;
    private ArrayList<OnCheckedChangeListener> mOnCheckedChangeListeners = new ArrayList<>();
    private TypedArray typedArray;
    private View rootView;
    LinearLayout parentView, itemsContainer;
    private ImageView iconMain, iconCheck;
    private TextView txtTitle;
    private TextView txtContent;
    private int iconMainColor;
    private int textTitleColor;
    private int textContentColor;
    private Drawable srcIconMain;
    private Drawable srcIconCheck;
    private String titleText;
    private String contentText;

    public PresetValueButton(Context context) {
        super(context);
        setupView(context);
    }

    public PresetValueButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(context);
        setIconMainColor(attrs);
        setTitleTextColor(attrs);
        setContentTextColor(attrs);
        setIconMainResource(attrs);
        setIconCheckResource(attrs);
        setTitleText(attrs);
        setContentText(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public PresetValueButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //parseAttributes(attrs);
        setupView(context);
        setIconMainColor(attrs);
        setTitleTextColor(attrs);
        setContentTextColor(attrs);
        setIconMainResource(attrs);
        setIconCheckResource(attrs);
        setTitleText(attrs);
        setContentText(attrs);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupView(Context context) {
        setSaveEnabled(true);
        rootView = inflate(context, R.layout.choicer_layout, this);
        parentView = rootView.findViewById(R.id.parentView);
        itemsContainer = rootView.findViewById(R.id.itemsContainer);
        iconMain = rootView.findViewById(R.id.iconMain);
        iconCheck = rootView.findViewById(R.id.iconCheck);
        txtTitle = rootView.findViewById(R.id.textTitle);
        txtContent = rootView.findViewById(R.id.textContent);
        setCustomTouchListener();
    }

    private void setTitleText(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.PresetValueButton);
        titleText = typedArray.getString(R.styleable.PresetValueButton_textTitle);
        txtTitle.setText(titleText);
        typedArray.recycle();
    }

    private void setContentText(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.PresetValueButton);
        contentText = typedArray.getString(R.styleable.PresetValueButton_contentTitle);
        txtContent.setText(contentText);
        typedArray.recycle();
    }

    private void setIconMainColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.PresetValueButton);
        iconMainColor = typedArray.getColor(R.styleable.PresetValueButton_iconMainColor, Color.GRAY);
        iconMain.setColorFilter(iconMainColor);
        typedArray.recycle();
    }

    private void setTitleTextColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.PresetValueButton);
        textTitleColor = typedArray.getColor(R.styleable.PresetValueButton_textTitleColor, Color.BLACK);
        txtTitle.setTextColor(textTitleColor);
        typedArray.recycle();
    }


    private void setContentTextColor(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.PresetValueButton);
        textContentColor = typedArray.getColor(R.styleable.PresetValueButton_textContentColor, Color.BLACK);
        txtContent.setTextColor(textContentColor);
        typedArray.recycle();
    }

    private void setIconMainResource(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.PresetValueButton);
        srcIconMain = typedArray.getDrawable(R.styleable.PresetValueButton_srcIconMain);
        iconMain.setImageDrawable(srcIconMain);
    }

    private void setIconCheckResource(AttributeSet set) {
        checkNullSet(set);
        typedArray = getContext().obtainStyledAttributes(set, R.styleable.PresetValueButton);
        srcIconCheck = typedArray.getDrawable(R.styleable.PresetValueButton_srcIconCheck);
        iconCheck.setImageDrawable(srcIconCheck);
    }


    private void checkNullSet(AttributeSet set) {
        if (set == null) {
            return;
        }
    }

    //================================================================================
    // Overriding default behavior
    //================================================================================

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mOnClickListener = l;
    }

    protected void setCustomTouchListener() {
        super.setOnTouchListener(new TouchListener());
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        mOnTouchListener = onTouchListener;
    }

    public OnTouchListener getOnTouchListener() {
        return mOnTouchListener;
    }

    private void onTouchDown(MotionEvent motionEvent) {
        setChecked(true);
    }

    private void onTouchUp(MotionEvent motionEvent) {
        // Handle user defined click listeners
        if (mOnClickListener != null) {
            mOnClickListener.onClick(this);
        }
    }

    public void setCheckedState() {
        iconCheck.setColorFilter(Color.rgb(30, 136, 229));
        iconMain.setColorFilter(Color.rgb(30, 136, 229));
        scaleViewChecked(parentView);
    }

    public void scaleViewChecked(View v) {
        Animation anim = new ScaleAnimation(1f, 1.04f, // Start and end values for the X axis scalingstartScale, endScale, // Start and end values for the Y axis scalingAnimation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1.04f);// Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(200);
        v.startAnimation(anim);
    }

    public void scaleViewUnchecked(View v) {
        Animation anim = new ScaleAnimation(1f, 1f, Animation.RELATIVE_TO_SELF, 1f);
        anim.setFillAfter(true);
        anim.setDuration(200);
        v.startAnimation(anim);
    }

    public void setNormalState() {
        iconCheck.setColorFilter(Color.GRAY);
        iconMain.setColorFilter(Color.GRAY);
        scaleViewUnchecked(parentView);
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getUnit() {
        return mUnit;
    }

    public void setUnit(String unit) {
        mUnit = unit;
    }

    //================================================================================
    // Checkable implementation
    //================================================================================

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            if (!mOnCheckedChangeListeners.isEmpty()) {
                for (int i = 0; i < mOnCheckedChangeListeners.size(); i++) {
                    mOnCheckedChangeListeners.get(i).onCheckedChanged(this, mChecked);
                }
            }
            if (mChecked) {
                setCheckedState();
            } else {
                setNormalState();
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    public void addOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListeners.add(onCheckedChangeListener);
    }

    @Override
    public void removeOnCheckChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListeners.remove(onCheckedChangeListener);
    }

    //================================================================================
    // Inner classes
    //================================================================================
    private final class TouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onTouchDown(event);
                    break;
                case MotionEvent.ACTION_UP:
                    onTouchUp(event);
                    break;
            }
            if (mOnTouchListener != null) {
                mOnTouchListener.onTouch(v, event);
            }
            return true;
        }
    }
}
