package com.nem3sis.mvpnullchecktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private Button mButton;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter();
        mButton = (Button) findViewById(R.id.main_button_test);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onButtonClick();
            }
        });
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
