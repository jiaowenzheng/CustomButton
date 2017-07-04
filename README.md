# CustomButton

支持圆角、背景颜色按下状态、背景图片按下状态、文字按下状态、选中状态、边框颜色及按下状态,再也不用写一堆xml了<br/>

## 效果

<img src="https://github.com/jiaowenzheng/CustomButton/raw/master/pic.png" width="450" height="800"/>  

<br/>

## 更新
 
 * 新增 noLeftStroke、noTopStroke、noRightStroke、noBottomStroke 四个属性.
 * 重新命名四个属性 normalSolidColor、pressedSolidColor、normalStrokeColor、pressedStrokeColor、roundLeftTopRadius、roundLeftBottomRadius、roundRightTopRadius、roundRightBottomRadius.
 * 增加一个实现导航标签事例 用xml实现.

## 属性

        normalSolidColor                               正常状态背景填充颜色
        pressedSolidColor                              按下状态背景填充颜色
        strokeColor                                    边框颜色
        normalStrokeColor                              边框正常状态填充颜色
        pressedStrokeColor                             边框按下状态填充姿色
        roundRadius                                    button四个角弧度
        roundLeftTopRadius                             button左上角弧度
        roundLeftBottomRadius                          button左下角弧度
        roundRightTopRadius                            button右上角弧度
        roundRightBottomRadius                         button右下角弧度
        normalDrawable                                 正常状态背景图片
        pressedDrawable                                按下状态背景图片
        isSelected                                     是否支持button选中状态 与setSelected()配合使用
        normalTextColor                                正常状态文字的颜色
        selectedTextColor                              选中状态下文字的颜色
        strokeWidth                                    边框的宽度
        noLeftStroke                                   无左描边线
        noRightStroke                                  无右描边线
        noTopStroke                                    无上描边线
        noBottomStroke                                 无下搭边线
        
        
## 示例

 * xml中设置
       
        xmlns:app="http://schemas.android.com/apk/res-auto"
        
        <com.button.library.CustomButton
               android:layout_width="match_parent"
               android:layout_height="wrap_content"
               android:layout_marginTop="10dp"
               android:text="45弧度圆角按下效果文字变色Button"
               android:textColor="@color/color_ffffff"
               app:normalSolid="@color/color_ff0000"
               app:normalTextColor="@color/color_3f51b5"
               app:pressedSolid="@color/color_00ff00"
               app:roundButtonRadius="@dimen/radius_45"
               app:selectedTextColor="@color/color_ffffff" />
               
 * 代码中设置方法：
    
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
        public void setBackGround(int normalSolid, int pressedSolid, int normalStroke, int pressedStroke, int roundButtonRadius, boolean isEnableSelected);
        
  
        public void setBackGround(int normalSolid, int pressedSolid, int stroke, int roundButtonRadius, boolean isEnableSelected)    
               
     
        public void setBackGround(int normalSolid, int pressedSolid, int roundButtonRadius, boolean isEnableSelected)    
        
        /**
         * 设置Button文字颜色
         *
         * @param normalTextColor       正常状态颜色
         * @param selectedTextColor     选中状态颜色
         */
        public void setTextColor(int normalTextColor, int selectedTextColor) 
                  
        /**
         * 设置button状态不可用
         *
         * @param enabled  true 可用 false 不可用
         * @param color    不可用状态下的颜色
         */
        public void setEnabled(boolean enabled,int color)             