package congnguyen.java_android_template.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import congnguyen.java_android_template.R;
import congnguyen.java_android_template.common.Constants;
import congnguyen.java_android_template.view.BaseView;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private Unbinder mUnbinder;
    private ProgressDialog mProgressDialog;
    private AlertDialog mErrorDialog;

    @CallSuper
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        mUnbinder = ButterKnife.bind(this);
    }

    @CallSuper @Override protected void onDestroy() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }

        if (mErrorDialog != null) {
            mErrorDialog.dismiss();
            mErrorDialog = null;
        }

        mUnbinder.unbind();
        super.onDestroy();
    }

    protected abstract int getLayoutResourceId();

    @Override public void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog = ProgressDialog.show(this, Constants.BLANK, "Đang xử lý...", true, false);
        } else {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        }
    }

    @Override public void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override public Context context() {
        return this;
    }

    @Override public void showErrorDialog(String errorMessage) {
        showErrorDialog(errorMessage, false);
    }

    @Override public void showErrorDialog(String errorMessage, final boolean isRedirectToLoginScreen) {
        if (mErrorDialog == null) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setMessage(errorMessage)
                    .setCancelable(false)
                    .setOnCancelListener( DialogInterface::dismiss)
                    .setPositiveButton( R.string.action_close, (dialog, which) -> {
                        dialog.dismiss();
                        if (isRedirectToLoginScreen) {

                        }
                    });

            mErrorDialog = dialogBuilder.create();
        }

        if (!mErrorDialog.isShowing()) {
            mErrorDialog.setMessage(errorMessage);
            mErrorDialog.show();
        }
    }

    @Override public void showError(String message) {
        // Do nothing
    }
}
