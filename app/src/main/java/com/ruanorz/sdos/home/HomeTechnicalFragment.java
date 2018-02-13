package com.ruanorz.sdos.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruanorz.sdos.R;
import com.ruanorz.sdos.home.adapters.TaskAdapter;
import com.ruanorz.sdos.home.interfaces.IHomeTechnicalView;
import com.ruanorz.sdos.home.presenter.HomeTechnicalPresenter;
import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.utils.UserSession;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by ruano on 09/02/2018.
 */

public class HomeTechnicalFragment extends Fragment implements IHomeTechnicalView{

    private View mRootView;

    @BindView(R.id.task_list)
    RecyclerView task_list;
    private TaskAdapter adapter;
    ArrayList<Task> skills = new ArrayList<>();
    public HomeTechnicalPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_technical_home, container, false);

        task_list = (RecyclerView) mRootView.findViewById(R.id.task_list);

        presenter = new HomeTechnicalPresenter(getActivity(), this);

        presenter.getAllTaskForUser(UserSession.getInstance().getUserInfo());


        return mRootView;
    }


    public static Fragment newInstance() {
        return new HomeTechnicalFragment();
    }


    @Override
    public void getAllTaskForUserOK(ArrayList<Task> skills) {
        this.skills = skills;
        Log.e("error", "----------------------> "+skills.size());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        task_list.setLayoutManager(llm);
        adapter = new TaskAdapter(skills, getActivity(), presenter);
        task_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getAllTaskForUserKO() {

    }
}