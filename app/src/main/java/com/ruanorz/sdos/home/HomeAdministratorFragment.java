package com.ruanorz.sdos.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruanorz.sdos.R;
import com.ruanorz.sdos.utils.Intents;

import butterknife.BindView;

public class HomeAdministratorFragment extends Fragment {

    @BindView(R.id.cv_add_task)
    CardView cv_add_task;
    @BindView(R.id.tv_add_task)
    CardView tv_add_task;

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_administrator_home, container, false);


        cv_add_task = (CardView) mRootView.findViewById(R.id.cv_add_task);
        cv_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intents.goToAddTask(getActivity());

            }
        });
        return mRootView;
    }


    public static Fragment newInstance() {
        return new HomeAdministratorFragment();
    }




}