package com.mshr.reminder.viewparts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.mshr.reminder.R;

/**
 * @author MSHR
 * 独自フォントをxmlから指定できるEditText
 */
public class OriginalFontEditText extends EditText {
  private String mFontNameString;
  
  public OriginalFontEditText(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initialize(context);
    setFontName(context, attrs);
    setFont();
  }

  public OriginalFontEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize(context);
    setFontName(context, attrs);
    setFont();
  }

  public OriginalFontEditText(Context context) {
    super(context);
    initialize(context);
    setFont();
  }
  
  /**
   * @param context
   * デフォルトのフォント名を決めておく
   */
  private void initialize(Context context){
    mFontNameString = context.getString(R.string.m_font_normal);
  }
  
  /**
   * @param context お約束
   * @param attrs   xmlで指定する属性
   * 属性からフォント名を取得する
   */
  private void setFontName(Context context, AttributeSet attrs) {
    TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.OriginalFontTextView);
    String attrValue = attr.getString(R.styleable.OriginalFontTextView_font);
    if (attrValue != null) {
      mFontNameString = attrValue;
    }
    attr.recycle();
  }
  
  /**
   * EditTextにフォントを設定する
   */
  private void setFont(){
    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), mFontNameString);
    setTypeface(typeface);
  }
}
