package com.example.b00sti.tripchallenge.utils.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.skeleton.android_utils.util.ViewUtils;

/**
 * Created by b00sti on 27.11.2016
 */

public class DialogCreator {

    Activity ctx;
    AlertDialog.Builder builder;
    private DialogInterface.OnClickListener positiveButtonListener;
    private DialogInterface.OnClickListener negativeButtonListener;
    private View messageView;

    private View titleView;
    private int messageViewId = R.layout.dialog_message;
    private int titleViewId = R.layout.dialog_title;
    private String positiveButtonText;
    private String negativeButtonText;

    private boolean isBasicTitleView = true;
    private boolean isBasicMessageView = true;

    private String title = "";
    private String message = "";

    public DialogCreator(Activity ctx) {
        this.ctx = ctx;
        positiveButtonText = ctx.getResources().getString(android.R.string.ok);
        negativeButtonText = ctx.getResources().getString(android.R.string.cancel);
    }

    private View getView(View view, int id) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        return View.inflate(ctx, id, null);
    }

    private String getString(int id) {
        return ctx.getResources().getString(id);
    }

    private void init() {
        builder = new AlertDialog.Builder(ctx);

        if (positiveButtonListener != null) {
            builder.setPositiveButton(positiveButtonText, positiveButtonListener);
        }
        if (negativeButtonListener != null) {
            builder.setNegativeButton(negativeButtonText, negativeButtonListener);
        }

        if (titleView == null) {
            titleView = getView(titleView, titleViewId);
        }

        if (messageView == null) {
            messageView = getView(messageView, messageViewId);
        }

        builder.setCustomTitle(titleView);
        builder.setView(messageView);

        if (isBasicTitleView) {
            TextView textView = (TextView) titleView.findViewById(R.id.title_textView);
            textView.setText(title);
        }

        if (isBasicMessageView) {
            TextView textView = (TextView) messageView.findViewById(R.id.dialog_textView);
            textView.setText(message);
        }
    }

    public void show() {
        init();
        ViewUtils.hideKeyboard(ctx);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);
        dialog.show();
        int buttonsColor = ContextCompat.getColor(ctx, R.color.colorLightText);
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(buttonsColor);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(buttonsColor);
    }

    public void show(String title, String message, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        this.title = title;
        this.message = message;
        this.positiveButtonListener = positiveButtonListener;
        this.negativeButtonListener = negativeButtonListener;
        show();
    }

    public void show(int title, int message, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        this.title = getString(title);
        this.message = getString(message);
        this.positiveButtonListener = positiveButtonListener;
        this.negativeButtonListener = negativeButtonListener;
        show();
    }

    public void show(String title, int message, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        this.title = title;
        this.message = getString(message);
        this.positiveButtonListener = positiveButtonListener;
        this.negativeButtonListener = negativeButtonListener;
        show();
    }

    public void show(int title, String message, DialogInterface.OnClickListener positiveButtonListener, DialogInterface.OnClickListener negativeButtonListener) {
        this.title = getString(title);
        this.message = message;
        this.positiveButtonListener = positiveButtonListener;
        this.negativeButtonListener = negativeButtonListener;
        show();
    }

    public DialogInterface.OnClickListener getPositiveButtonListener() {
        return positiveButtonListener;
    }

    public void setPositiveButtonListener(DialogInterface.OnClickListener positiveButtonListener) {
        this.positiveButtonListener = positiveButtonListener;
    }

    public DialogInterface.OnClickListener getNegativeButtonListener() {
        return negativeButtonListener;
    }

    public void setNegativeButtonListener(DialogInterface.OnClickListener negativeButtonListener) {
        this.negativeButtonListener = negativeButtonListener;
    }

    public View getMessageView() {
        messageView = getView(messageView, messageViewId);
        return messageView;
    }

    public void setMessageView(View messageView) {
        isBasicMessageView = false;
        this.messageView = messageView;
    }

    public View getTitleView() {
        titleView = getView(titleView, titleViewId);
        return titleView;
    }

    public void setTitleView(View titleView) {
        isBasicTitleView = false;
        this.titleView = titleView;
    }

    public int getMessageViewId() {
        return messageViewId;
    }

    public void setMessageViewId(int messageViewId) {
        isBasicMessageView = false;
        this.messageViewId = messageViewId;
    }

    public int getTitleViewId() {
        return titleViewId;
    }

    public void setTitleViewId(int titleViewId) {
        isBasicTitleView = false;
        this.titleViewId = titleViewId;
    }

    public String getPositiveButtonText() {
        return positiveButtonText;
    }

    public void setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
    }

    public String getNegativeButtonText() {
        return negativeButtonText;
    }

    public void setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
