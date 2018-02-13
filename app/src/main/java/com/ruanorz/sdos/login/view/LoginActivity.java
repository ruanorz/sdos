package com.ruanorz.sdos.login.view;

import android.os.CountDownTimer;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ruanorz.sdos.R;
import com.ruanorz.sdos.login.interfaces.ILoginView;
import com.ruanorz.sdos.login.presenter.LoginPresenter;
import com.ruanorz.sdos.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.animation.SpringForce.DAMPING_RATIO_HIGH_BOUNCY;
import static android.support.animation.SpringForce.STIFFNESS_LOW;

public class LoginActivity extends AppCompatActivity implements ILoginView{

    @BindView(R.id.et_username)
    public EditText et_username;
    @BindView(R.id.et_pass)
    public EditText et_pass;
    @BindView(R.id.btn_login)
    public RelativeLayout btn_login;


    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        btn_login.setX(btn_login.getWidth()*-1);
        btn_login_anim();
        presenter = new LoginPresenter(getApplicationContext(), this);

    }

    @OnClick(R.id.btn_login)
    public void bntLoginClicked(){

        presenter.login(et_username.getText().toString(), et_pass.getText().toString());

   }

    @Override
    public void loginOK(boolean userIsAdministrator) {
        Log.e("error", "LOGIN OK - "+userIsAdministrator);


    }

    @Override
    public void loginKO() {
        Log.e("error", "LOGIN KO");
        Toast.makeText(this, getString(R.string.fail_login),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void finishActivity() {
        finish();
    }

    public void btn_login_anim(){
        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

//                rl_parche.setVisibility(VISIBLE);

                SpringAnimation anim =
                        new SpringAnimation(btn_login, DynamicAnimation.TRANSLATION_X, btn_login.getWidth()- UIUtils.convertDpToPx(100));

                anim.getSpring().setDampingRatio(DAMPING_RATIO_HIGH_BOUNCY);
                anim.getSpring().setStiffness(STIFFNESS_LOW);

                anim.start();

            }

        }.start();
    }
}
