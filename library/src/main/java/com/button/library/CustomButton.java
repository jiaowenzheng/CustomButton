package com.button.library;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import static com.button.library.R.attr.isSelected;
import static com.button.library.R.attr.normalStrokeColor;


/**
 * Button(圆角Button带点击效果,正常Button带点击效果)
 *
 * @author gqiu
 */
public class CustomButton extends Button {

    private StateListDrawable selector;
    private int radius;
    private int strokeWidth = 2;

    public CustomButton(Context context) {
        super(context);

    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributeSet(context, attrs);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributeSet(context, attrs);
    }


    private void setAttributeSet(Context context, AttributeSet attrs) {

        if (!isInEditMode()) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.customButton);
            int normalSolid = a.getColor(R.styleable.customButton_normalSolidColor, Color.TRANSPARENT);
            int pressedSolid = a.getColor(R.styleable.customButton_pressedSolidColor, Color.TRANSPARENT);
            int strokeColor = a.getColor(R.styleable.customButton_strokeColor, Color.TRANSPARENT);
            int leftTopRadius = a.getDimensionPixelSize(R.styleable.customButton_roundLeftTopRadius, 0);
            int leftBottomRadius = a.getDimensionPixelSize(R.styleable.customButton_roundLeftBottomRadius, 0);
            int rightTopRadius = a.getDimensionPixelSize(R.styleable.customButton_roundRightTopRadius, 0);
            int rightBottomRadius = a.getDimensionPixelSize(R.styleable.customButton_roundRightBottomRadius, 0);
            int normalTextColor = a.getColor(R.styleable.customButton_normalTextColor, 0);
            int selectedTextColor = a.getColor(R.styleable.customButton_selectedTextColor, 0);
            int normalStrokeColor = a.getColor(R.styleable.customButton_normalStrokeColor, Color.TRANSPARENT);
            int pressedStrokeColor = a.getColor(R.styleable.customButton_pressedStrokeColor, Color.TRANSPARENT);
            boolean isSelected = a.getBoolean(R.styleable.customButton_isSelected, false);
            boolean noLeftStroke = a.getBoolean(R.styleable.customButton_noLeftStroke,false);
            boolean noRightStroke = a.getBoolean(R.styleable.customButton_noRightStroke,false);
            boolean noTopStroke = a.getBoolean(R.styleable.customButton_noTopStroke,false);
            boolean noBottomStroke = a.getBoolean(R.styleable.customButton_noBottomStroke,false);
            Drawable normalDrawable = a.getDrawable(R.styleable.customButton_normalDrawable);
            Drawable pressedDrawable = a.getDrawable(R.styleable.customButton_pressedDrawable);
            strokeWidth = a.getDimensionPixelSize(R.styleable.customButton_strokeWidth, 2);
            radius = a.getDimensionPixelSize(R.styleable.customButton_roundRadius, 0);

            a.recycle();

            selector = new StateListDrawable();
            if (normalDrawable != null && pressedDrawable != null) {

                if (isSelected) {
                    selector.addState(new int[]{android.R.attr.state_selected}, pressedDrawable);
                } else {
                    selector.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
                }

                selector.addState(new int[]{}, normalDrawable);
                setBackgroundDrawable(selector);
            } else {

                //先设置按下状态，再设置正常状态，否则失效.
                //设置按下状态drawable 先设置
                setPressedState(leftTopRadius,leftBottomRadius,rightBottomRadius,rightTopRadius,
                        strokeColor,pressedStrokeColor,pressedSolid,noLeftStroke,noRightStroke,
                        noTopStroke,noBottomStroke,isSelected);

                //设置正常状态drawable
                setNormalState(leftTopRadius,leftBottomRadius,rightBottomRadius,rightTopRadius,
                        strokeColor,normalStrokeColor,normalSolid,noLeftStroke,noRightStroke,noTopStroke,noBottomStroke);

                //设置selector
                setBackgroundDrawable(selector);

                if (normalTextColor != 0 && selectedTextColor != 0) {
                    //设置state_selected状态时，和正常状态时文字的颜色
                    ColorStateList textColorSelect = null;
                    if (isSelected) { //是否可以选中
                        int[][] states = new int[2][1];
                        states[0] = new int[]{android.R.attr.state_selected};
                        states[1] = new int[]{};
                        textColorSelect = new ColorStateList(states, new int[]{selectedTextColor, normalTextColor});
                    }else{
                        int[][] states = new int[3][1];
                        states[0] = new int[]{android.R.attr.state_selected};
                        states[1] = new int[]{android.R.attr.state_pressed};
                        states[2] = new int[]{};
                        textColorSelect = new ColorStateList(states, new int[]{selectedTextColor,selectedTextColor,normalTextColor});
                    }

                    setTextColor(textColorSelect);
                }
            }
        }
    }


    /**
     *
     * 设置正常状态下drawable
     *
     * @param leftTopRadius            左上角角度
     * @param leftBottomRadius         左下角角度
     * @param rightBottomRadius        右下角角度
     * @param rightTopRadius           右上角角度
     * @param strokeColor              描边颜色
     * @param normalStrokeColor        正常状态下描边颜色
     * @param normalSolid              正常状态下填充颜色
     * @param noLeftStroke             无左描边
     * @param noRightStroke            无右描边
     * @param noTopStroke              无上描边
     * @param noBottomStroke           无底描边
     */
    private void setNormalState(int leftTopRadius,int leftBottomRadius, int rightBottomRadius,int rightTopRadius,
                               int strokeColor,int normalStrokeColor,int normalSolid,boolean noLeftStroke,
                               boolean noRightStroke,boolean noTopStroke,boolean noBottomStroke){

        GradientDrawable normalGD = new GradientDrawable();
        //设置正常状态下填充色
        normalGD.setColor(normalSolid);
        //设置圆角
        setRadius(normalGD,leftTopRadius,leftBottomRadius,rightBottomRadius,rightTopRadius);
        //设置描边与描边颜色
        setStrokeWidthWithColor(normalGD,strokeColor,normalStrokeColor);
        //normal drawable
        LayerDrawable normalLayerDrawable = new LayerDrawable(new Drawable[]{normalGD});
        //设置正常状态下描边边距
        setStrokeMargin(normalLayerDrawable,0,noLeftStroke,noRightStroke,noTopStroke,noBottomStroke);
        //设置正常状态下的drawable
        selector.addState(new int[]{}, normalLayerDrawable);
    }

    /**
     * 设置按下状态drawable
     *
     * @param leftTopRadius            左上角角度
     * @param leftBottomRadius         左下角角度
     * @param rightBottomRadius        右下角角度
     * @param rightTopRadius           右上角角度
     * @param strokeColor              描边颜色
     * @param pressedStokeColor        按下状太描边颜色
     * @param pressedSolid             按下状态填充色
     * @param noLeftStroke             无左描边
     * @param noRightStroke            无右描边
     * @param noTopStroke              无上描边
     * @param noBottomStroke           无底描边
     * @param isSelected               是否可以选择状态
     */
    private void setPressedState(int leftTopRadius,int leftBottomRadius,int rightBottomRadius,int rightTopRadius,
                                int strokeColor,int pressedStokeColor,int pressedSolid,boolean noLeftStroke,
                                boolean noRightStroke,boolean noTopStroke,boolean noBottomStroke,boolean isSelected){

        GradientDrawable pressedGD = new GradientDrawable();

        if (pressedSolid != Color.TRANSPARENT || pressedStokeColor != Color.TRANSPARENT) {
            //设置按下填充色
            pressedGD.setColor(pressedSolid);
            //设置圆角
            setRadius(pressedGD,leftTopRadius,leftBottomRadius,rightBottomRadius,rightTopRadius);
            //设置描边与描边颜色
            setStrokeWidthWithColor(pressedGD,strokeColor,pressedStokeColor);
            //设置选中状态下描边边距
            LayerDrawable pressedLayerDrawable = new LayerDrawable(new Drawable[]{pressedGD});
            setStrokeMargin(pressedLayerDrawable,0,noLeftStroke,noRightStroke,noTopStroke,noBottomStroke);
            //设置按下状态
            if (isSelected) {
                selector.addState(new int[]{android.R.attr.state_selected}, pressedLayerDrawable);
            } else {
                selector.addState(new int[]{android.R.attr.state_pressed}, pressedLayerDrawable);
            }
        }
    }

    /**
     *
     * 设置角度
     *
     * @param drawable
     * @param leftTopRadius
     * @param leftBottomRadius
     * @param rightBottomRadius
     * @param rightTopRadius
     */
    private void setRadius(GradientDrawable drawable,int leftTopRadius,int leftBottomRadius,
                          int rightBottomRadius,int rightTopRadius){
        if (radius != 0) {
            drawable.setCornerRadius(radius);
        } else if (leftTopRadius != 0 || leftBottomRadius != 0 || rightTopRadius != 0 || rightBottomRadius != 0) {
            drawable.setCornerRadii(new float[]{leftTopRadius, leftTopRadius, rightTopRadius,
                    rightTopRadius, rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius});
        }

    }

    /**
     *
     * 设置边距
     *
     * @param drawable
     * @param strokeColor
     * @param stateStokeColor
     */
    private void setStrokeWidthWithColor(GradientDrawable drawable,int strokeColor,int stateStokeColor){
        if (stateStokeColor != Color.TRANSPARENT) {
            drawable.setStroke(strokeWidth, stateStokeColor);
        } else {
            drawable.setStroke(strokeWidth, strokeColor);
        }
    }


    /**
     *
     * 设置 button 四个边距
     *
     * @param layerDrawable    LayerDrawable
     * @param index            下标
     * @param left             左边距
     * @param right            右边距
     * @param top              上边距
     * @param bottom           下边距
     */
    private void setStrokeMargin(LayerDrawable layerDrawable, int index , boolean left, boolean right, boolean top, boolean bottom){

        int leftMargin = left ? -strokeWidth : 0;
        int rightMargin = right ? -strokeWidth : 0;
        int topMargin = top ? -strokeWidth : 0;
        int bottomMargin = bottom ? -strokeWidth: 0;

        layerDrawable.setLayerInset(index,leftMargin,topMargin,rightMargin,bottomMargin);
    }

    /**
     * 设置Button背景
     *
     * @param normalSolid         正常状态背景填充颜色
     * @param pressedSolid        按下状态背景填充颜色
     * @param normalStroke        正常状态边框颜色填充
     * @param pressedStroke       按下状态边框颜色填充
     * @param roundButtonRadius   圆角弧度
     * @param isEnableSelected    是否打开选中状态
     */
    public void setBackGround(int normalSolid, int pressedSolid, int normalStroke, int pressedStroke, int roundButtonRadius, boolean isEnableSelected) {
        normalSolid = getResources().getColor(normalSolid);
        pressedSolid = getResources().getColor(pressedSolid);
        normalStroke = getResources().getColor(normalStroke);
        pressedStroke = getResources().getColor(pressedStroke);

        selector = new StateListDrawable();
        GradientDrawable normalGD = new GradientDrawable();
        normalGD.setColor(normalSolid);
        GradientDrawable pressedGD = new GradientDrawable();
        pressedGD.setColor(pressedSolid);

        if (roundButtonRadius < 0) {
            normalGD.setCornerRadius(radius);
            pressedGD.setCornerRadius(radius);
        } else {
            radius = roundButtonRadius;
            normalGD.setCornerRadius(radius);
            pressedGD.setCornerRadius(radius);
        }


        if (normalStroke != 0 && pressedStroke != 0) {
            normalGD.setStroke(strokeWidth, normalStroke);
            pressedGD.setStroke(strokeWidth, pressedStroke);
        }


        if (isEnableSelected) {
            selector.addState(new int[]{android.R.attr.state_selected}, pressedGD);
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedGD);
        } else {
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedGD);
        }

        selector.addState(new int[]{}, normalGD);
        setBackgroundDrawable(selector);
    }


    /**
     * 设置Button背景
     *
     * @param normalSolid         正常状态背景填充颜色
     * @param pressedSolid        按下状态背景填充颜色
     * @param stroke              边框
     * @param roundButtonRadius   圆角弧度
     * @param isEnableSelected    是否打开选中状态
     */
    public void setBackGround(int normalSolid, int pressedSolid, int stroke, int roundButtonRadius, boolean isEnableSelected) {
        normalSolid = getResources().getColor(normalSolid);
        pressedSolid = getResources().getColor(pressedSolid);
        stroke = getResources().getColor(stroke);

        selector = new StateListDrawable();
        GradientDrawable normalGD = new GradientDrawable();
        normalGD.setColor(normalSolid);
        GradientDrawable pressedGD = new GradientDrawable();
        pressedGD.setColor(pressedSolid);

        if (roundButtonRadius < 0) {
            normalGD.setCornerRadius(radius);
            pressedGD.setCornerRadius(radius);
        } else {
            radius = roundButtonRadius;
            normalGD.setCornerRadius(radius);
            pressedGD.setCornerRadius(radius);
        }


        if (stroke != 0) {
            normalGD.setStroke(strokeWidth, stroke);
            pressedGD.setStroke(strokeWidth, stroke);
        }


        if (isEnableSelected) {
            selector.addState(new int[]{android.R.attr.state_selected}, pressedGD);
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedGD);
        } else {
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedGD);
        }

        selector.addState(new int[]{}, normalGD);
        setBackgroundDrawable(selector);
    }

    /**
     * 设置Button背景
     *
     * @param normalSolid         正常状态背景填充颜色
     * @param pressedSolid        按下状态背景填充颜色
     * @param roundButtonRadius   圆角弧度
     * @param isEnableSelected    是否打开选中状态
     */
    public void setBackGround(int normalSolid, int pressedSolid, int roundButtonRadius, boolean isEnableSelected) {
        normalSolid = getResources().getColor(normalSolid);
        pressedSolid = getResources().getColor(pressedSolid);

        selector = new StateListDrawable();
        GradientDrawable normalGD = new GradientDrawable();
        normalGD.setColor(normalSolid);
        GradientDrawable pressedGD = new GradientDrawable();
        pressedGD.setColor(pressedSolid);

        if (roundButtonRadius < 0) {
            normalGD.setCornerRadius(radius);
            pressedGD.setCornerRadius(radius);
        } else {
            radius = roundButtonRadius;
            normalGD.setCornerRadius(radius);
            pressedGD.setCornerRadius(radius);
        }


        if (isEnableSelected) {
            selector.addState(new int[]{android.R.attr.state_selected}, pressedGD);
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedGD);
        } else {
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedGD);
        }

        selector.addState(new int[]{}, normalGD);
        setBackgroundDrawable(selector);
    }


    /**
     * 设置Button文字颜色
     *
     * @param normalTextColor       正常状态颜色
     * @param selectedTextColor     选中状态颜色
     */
    public void setTextColor(int normalTextColor, int selectedTextColor) {
        normalTextColor = getResources().getColor(normalTextColor);
        selectedTextColor = getResources().getColor(selectedTextColor);

        int[][] states = new int[3][1];
        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{android.R.attr.state_pressed};
        states[2] = new int[]{};
        ColorStateList textColorSelect = new ColorStateList(states, new int[]{selectedTextColor, selectedTextColor, normalTextColor});
        setTextColor(textColorSelect);
    }


    @Override
    public void setEnabled(boolean enabled) {
        if (enabled) {
            setBackgroundDrawable(selector);
        } else {
            if (radius != 0) {
                GradientDrawable tempBackGround = new GradientDrawable();
                tempBackGround.setColor(getResources().getColor(R.color.gray_4));
                tempBackGround.setCornerRadius(radius);
                setBackgroundDrawable(tempBackGround);
            } else {
                setBackgroundColor(getResources().getColor(R.color.gray_4));
            }

        }

        super.setEnabled(enabled);
    }

    /**
     * 设置button状态不可用
     *
     * @param enabled  true 可用 false 不可用
     * @param color    不可用状态下的颜色
     */
    public void setEnabled(boolean enabled,int color) {
        if (enabled) {
            setBackgroundDrawable(selector);
        } else {
            if (radius != 0) {
                GradientDrawable tempBackGround = new GradientDrawable();
                tempBackGround.setColor(getResources().getColor(color));
                tempBackGround.setCornerRadius(radius);
                setBackgroundDrawable(tempBackGround);
            } else {
                setBackgroundColor(getResources().getColor(color));
            }

        }

        super.setEnabled(enabled);
    }
}
