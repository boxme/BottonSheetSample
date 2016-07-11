package com.example.desmond.bottonsheetsample;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

/**
 * Created by desmond on 11/7/16.
 */
public class CustomBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private TextView mOffsetText;
    private TextView mStateText;

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_sheet_dialog_content_view, null);
        dialog.setContentView(contentView);
//        mOffsetText = (TextView) contentView.findViewById(R.id.offsetText);
//        mStateText = (TextView) contentView.findViewById(R.id.stateText);


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
//        mStateText.setText(MainActivity.getStateAsString(newState));
    }
}
