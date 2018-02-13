package com.ruanorz.sdos.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruanorz.sdos.BaseApp;
import com.ruanorz.sdos.R;
import com.ruanorz.sdos.home.adapters.TabsAdapter;
import com.ruanorz.sdos.home.interfaces.IHomeView;
import com.ruanorz.sdos.home.presenter.HomePresenter;
import com.ruanorz.sdos.models.User;
import com.ruanorz.sdos.networking.Service;
import com.ruanorz.sdos.utils.Constants;
import com.ruanorz.sdos.utils.Intents;
import com.ruanorz.sdos.utils.UserSession;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseApp implements IHomeView {

    @Nullable
    @BindView(R.id.home_tabs)
    TabLayout home_tabs;
    @Nullable
    @BindView(R.id.home_viewpager)
    ViewPager home_viewpager;
    @Nullable
    @BindView(R.id.tv_user)
    TextView tv_user;
    @Nullable
    @BindView(R.id.tv_full_user_name)
    TextView tv_full_user_name;
    @Nullable
    @BindView(R.id.tv_user_admin)
    TextView tv_user_admin;
    @BindView(R.id.exit_user)
    ImageView exit_user;

    @Inject
    public Service service;

    private HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        presenter = new HomePresenter(getApplicationContext(), this);

        renderView();

        Intent intent = getIntent();
        boolean message = false;
        if (intent!=null) {
            message = intent.getBooleanExtra(Constants.IS_ADMINISTRATOR, false);
        }

        Log.e("error", "usuario administrador: "+message);


        home_viewpager.setAdapter(new TabsAdapter(getSupportFragmentManager(), message));
        home_tabs.setupWithViewPager(home_viewpager);
    }

    private void renderView(){

        presenter.getUserLogedInInfo();

    }

    @Override
    public void userInfoOK(User user) {

        tv_user.setText(getFirstLetters(user.getName()));
        tv_full_user_name.setText(user.getName());
        if (user.isAdministrator()){
            tv_user_admin.setText(getString(R.string.user_role_administrator));
        }else {
            tv_user_admin.setText(getString(R.string.user_role_technical));
        }

    }

    @Override
    public void userInfoKO() {

    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    public String getFirstLetters(String text)
    {
        String firstLetters = "";
        text = text.replaceAll("[.,]", ""); // Replace dots, etc (optional)
        for(String s : text.split(" "))
        {
            firstLetters += s.charAt(0);
        }
        return firstLetters.substring(0,2);

    }

    @OnClick(R.id.exit_user)
    public void onExitUserClicked(){
        UserSession.getInstance().deleteUserSession();
        Intents.goToLogin(this);
        finish();
    }
}
