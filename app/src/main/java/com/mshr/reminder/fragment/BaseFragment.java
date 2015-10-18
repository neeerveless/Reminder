package com.mshr.reminder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author MSHR
 * Fragmentを使う時はextendsしたら良いと思う
 * Contextはよく使うし
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
  protected Context mContext;
  protected View    mRootView;

  private OnClickAtFragmentListener onClickAtFragmentListener = null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = getActivity().getApplicationContext();
  }
  
  public void setOnClickAtFragmentListener(OnClickAtFragmentListener l) {
    if (mRootView != null) {
      this.onClickAtFragmentListener = l;
      mRootView.setOnClickListener(this);
    }
  }

  @Override
  public void onClick(View v) {
    onClickAtFragmentListener.onClickAtFragment(this);
  }
  
  /**
   * @author MSHR
   * FragmentのためのOnClickListener
   * FragmentってViewみたいにも扱えるし、OnClickListenerぐらいあっても良くない？
   */
  public interface OnClickAtFragmentListener {
    public void onClickAtFragment(BaseFragment f);
  }
}
