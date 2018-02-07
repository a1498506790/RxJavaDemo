package com.mir.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mir.rxjavademo.bean.UserBean;
import com.mir.rxjavademo.mvp.login.LoginContract;
import com.mir.rxjavademo.mvp.login.LoginModel;
import com.mir.rxjavademo.mvp.login.LoginModelImpl;
import com.mir.rxjavademo.mvp.login.LoginPresenter;

public class MainActivity extends AppCompatActivity implements LoginContract.View{

    private AppCompatEditText mEdtMobile, mEdtPassword;
    private Button mBtnLogin;
    private ProgressBar mProgressBar;

    private LoginModel mModel;
    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtMobile = (AppCompatEditText) findViewById(R.id.edt_mobile);
        mEdtPassword = (AppCompatEditText) findViewById(R.id.edt_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mModel = new LoginModelImpl();
        new LoginPresenter(mModel, this);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        String mobile = mEdtMobile.getText().toString();
        String password = mEdtPassword.getText().toString();
        if (mPresenter.checkUserInfo(mobile, password)) {
            mPresenter.login(mobile, password);
        }else{
            Toast.makeText(this, "请输入手机号或密码", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hintProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        Log.e("test", "userBean : " + userBean.toString());
    }

    @Override
    public void loginFail(String msg) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(LoginPresenter presenter) {
        mPresenter = presenter;
    }
}
