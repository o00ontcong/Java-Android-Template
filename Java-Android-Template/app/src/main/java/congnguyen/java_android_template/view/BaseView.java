package congnguyen.java_android_template.view;

import android.content.Context;

public interface BaseView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showError(String message);

    void showErrorDialog(String errorMessage);

    void showErrorDialog(String errorMessage, final boolean isRedirectToLoginScreen);

    Context context();
}