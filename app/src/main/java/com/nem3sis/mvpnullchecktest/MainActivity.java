package com.nem3sis.mvpnullchecktest;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private Button mButton;
    private TextView mTextView;
    private MainPresenter mPresenter;

    private final String TAG = MainActivity.class.getSimpleName();

    //state to save
    private boolean requested = false;
    private String requestedKey = "requested";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter();
        mButton = (Button) findViewById(R.id.main_button_test);
        mTextView = (TextView) findViewById(R.id.main_tw);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onButtonClick();
                requested = true;
            }
        });

        if(savedInstanceState!= null){
            onRestoreState(savedInstanceState);
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(requestedKey, requested);
    }

    private void onRestoreState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreState: ");
        if(savedInstanceState.containsKey(requestedKey)){
            requested = savedInstanceState.getBoolean(requestedKey);
            if(requested){
                Log.d(TAG, "onRestoreState: request suspended found, resuming..");
                mPresenter.onButtonClick();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onDetachView();
    }

    @Override
    public void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
      mTextView.setText(message + String.valueOf(SystemClock.currentThreadTimeMillis()));
    }
}
