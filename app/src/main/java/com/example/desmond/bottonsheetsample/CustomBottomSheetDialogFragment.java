package com.example.desmond.bottonsheetsample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by desmond on 11/7/16.
 */
public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private TextView mOffsetText;
    private TextView mStateText;

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            setStateText(newState);
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            setOffsetText(slideOffset);
        }
    };

    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_dialog_content_view, null);
        dialog.setContentView(contentView);
        mOffsetText = (TextView) contentView.findViewById(R.id.dialog_offset);
        mStateText = (TextView) contentView.findViewById(R.id.dialog_state);

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
                FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                bottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
            }
        });
    }

    private void setOffsetText(final float slideOffset) {
        ViewCompat.postOnAnimation(mOffsetText, new Runnable() {
            @Override
            public void run() {
                mOffsetText.setText("Offset: " + slideOffset);
            }
        });
    }

    private void setStateText(int newState) {
        mStateText.setText(MainActivity.getStateAsString(newState));
    }
}
