package congnguyen.java_android_template.view;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import congnguyen.java_android_template.R;

public class CoolToast {

    private Context context;
    /**
     * Toast duration
     */
    public static final int SHORT = Toast.LENGTH_SHORT;
    private static final int BOTTOM = Gravity.BOTTOM;

    /**
     * Index of every toast color
     */
    public static final int SUCCESS = 0;
    public static final int DANGER = 1;

    private int[] stylesRound;
    private int position;
    private int icon = -1;

    public CoolToast(Context context) {
        this.context = context;
        stylesRound = new int[]{R.drawable.round_success, R.drawable.round_light};
        this.position = BOTTOM;
    }

    private void reset() {
        icon = -1;
        this.position = BOTTOM;
    }

    public void make(String text, int style, int duration) {

        Toast coolToast = new Toast(context);

        ViewGroup toastRoot = ((AppCompatActivity) context).findViewById(R.id.toastRoot);
        View view = LayoutInflater.from(context).inflate(R.layout.cool_toast, toastRoot, false);

        TextView toastText = view.findViewById(R.id.toastText);
        ImageView toastImg = view.findViewById( R.id.toastImg);
        LinearLayout rootLayout = view.findViewById(R.id.toastBg);
        rootLayout.setBackgroundResource(stylesRound[style]);
        toastText.setText(text);
        if (icon == -1) {
            if (style == SUCCESS)
                toastImg.setBackgroundResource(R.drawable.ic_done_white_24dp);
            else
                toastImg.setBackgroundResource(R.drawable.ic_info_white_24dp);
        } else {
            toastImg.setBackgroundResource(icon);
        }

        coolToast.setView(view);
        coolToast.setDuration(duration);
        coolToast.setGravity(position, 0, 100);
        coolToast.show();

        reset();
    }
}