package congnguyen.java_android_template.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import congnguyen.java_android_template.R;
import congnguyen.java_android_template.activity.BaseActivity;
import congnguyen.java_android_template.common.Constants;
import congnguyen.java_android_template.util.Utils;
import congnguyen.java_android_template.view.BaseView;
import congnguyen.java_android_template.view.CoolToast;

public abstract class BaseFragment extends Fragment implements BaseView {
    private Unbinder mUnbinder;
    protected BaseActivity mParentActivity;
    @BindView(R.id.btnmenu) protected ImageView btnMenu;

    protected abstract int getLayoutResourceId();

    public void onFragmentResume() {
        // For overriding
    }

    public FragmentManager fragmentManager() {
        return getFragmentManager();
    }

    @CallSuper
    @Override public void onAttach(Context context) {
        super.onAttach(context);
        mParentActivity = (BaseActivity) context;
    }

    @Nullable @Override public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mMainView = inflater.inflate(getLayoutResourceId(), container, false);
        mUnbinder = ButterKnife.bind(this, mMainView);
        return mMainView;
    }

    @Override public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    @Override public FragmentActivity context() {
        return getActivity();
    }

    @Override public void showLoadingDialog() {
        mParentActivity.showLoadingDialog();
    }

    @Override public void hideLoadingDialog() {
        mParentActivity.hideLoadingDialog();
    }

    @Override public void showErrorDialog(String errorMessage) {
        mParentActivity.showErrorDialog(errorMessage);
    }

    @Override public void showErrorDialog(String errorMessage, boolean isRedirectToLoginScreen) {
        mParentActivity.showErrorDialog(errorMessage, isRedirectToLoginScreen);
    }

    @Override public void showError(String message) {
        mParentActivity.showError(message);
    }
}