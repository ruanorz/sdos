package com.ruanorz.sdos.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ruanorz.sdos.R;
import com.ruanorz.sdos.deps.DaggerDeps;
import com.ruanorz.sdos.home.adapters.FruitAdapter;
import com.ruanorz.sdos.home.interfaces.IHomeFruitView;
import com.ruanorz.sdos.home.presenter.HomeFruitPresenter;
import com.ruanorz.sdos.models.FruitListResponse;
import com.ruanorz.sdos.networking.NetworkModule;
import com.ruanorz.sdos.networking.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ruano on 11/02/2018.
 */

public class HomeFruitFragment extends Fragment implements IHomeFruitView {

    private View mRootView;

    @BindView(R.id.fruit_list)
    RecyclerView fruit_list;
    private FruitAdapter adapter;
    List<FruitListResponse> fruits = new ArrayList<>();
    public HomeFruitPresenter presenter;
    public ProgressBar pb_wait;
    @Inject
    public Service service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_fruit_home, container, false);
        File cacheFile = new File(getActivity().getCacheDir(), "responses");
        DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build().inject(this);

        fruit_list = (RecyclerView) mRootView.findViewById(R.id.fruit_list);
        pb_wait = (ProgressBar) mRootView.findViewById(R.id.pb_wait);

        presenter = new HomeFruitPresenter(service, getActivity(), this);

        presenter.getFruitList();





        return mRootView;
    }


    public static Fragment newInstance() {
        return new HomeFruitFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.unsuscribeRxAndroid();

    }

    @Override
    public void showWait() {
        pb_wait.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        pb_wait.setVisibility(View.GONE);
    }

    @Override
    public void getFruitListSuccess(List<FruitListResponse> fruits) {
        this.fruits = fruits;
        Log.e("error", "----------------------> "+fruits.size());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        fruit_list.setLayoutManager(llm);
        adapter = new FruitAdapter(fruits, getActivity(), presenter);
        fruit_list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getFruitListKO() {

    }
}