package com.example.desmond.bottonsheetsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior mBottomSheetBehaviour;
    private TextView mStateView;
    private TextView mOffsetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mStateView = (TextView) findViewById(R.id.drag_me);
        mOffsetView = (TextView) findViewById(R.id.offset_text);

        View bottomViewBehaviourView = findViewById(R.id.bottom_sheet_behaviour_view);
        mBottomSheetBehaviour = BottomSheetBehavior.from(bottomViewBehaviourView);
        setStateText(mBottomSheetBehaviour.getState());
        mBottomSheetBehaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                setStateText(newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                setOffsetText(slideOffset);
            }
        });
    }

    private void setStateText(int newState) {
        mStateView.setText(getStateAsString(newState));
    }

    private String getStateAsString(int newState) {
        switch (newState) {
            case BottomSheetBehavior.STATE_COLLAPSED:
                return "Collapsed";
            case BottomSheetBehavior.STATE_DRAGGING:
                return "Dragging";
            case BottomSheetBehavior.STATE_EXPANDED:
                return "Expanded";
            case BottomSheetBehavior.STATE_HIDDEN:
                return "Hidden";
            case BottomSheetBehavior.STATE_SETTLING:
                return "Settling";
            default:
                throw new IllegalArgumentException("Wrong state: " + newState);
        }
    }

    private void setOffsetText(float slideOffset) {
        mOffsetView.setText("Offset: " + slideOffset);
    }

    @Override
    public void onBackPressed() {
        if (mBottomSheetBehaviour.getState() != BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return;
        }
        super.onBackPressed();
    }
}
