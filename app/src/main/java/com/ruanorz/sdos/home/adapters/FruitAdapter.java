package com.ruanorz.sdos.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruanorz.sdos.R;
import com.ruanorz.sdos.home.presenter.HomeFruitPresenter;
import com.ruanorz.sdos.models.FruitListResponse;

import java.util.List;

/**
 * Created by ruano on 12/02/2018.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<FruitListResponse> fruits;
    static private Context mContext;
    public HomeFruitPresenter presenter;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_fruit_1;
        public TextView tv_fruit_2;
        public TextView tv_fruit_3;
        public TextView tv_fruit_4;

        public ViewHolder(View v) {
            super(v);

            tv_fruit_1 = (TextView) v.findViewById(R.id.tv_fruit_1);
            tv_fruit_2 = (TextView) v.findViewById(R.id.tv_fruit_2);
            tv_fruit_3 = (TextView) v.findViewById(R.id.tv_fruit_3);
            tv_fruit_4 = (TextView) v.findViewById(R.id.tv_fruit_4);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FruitAdapter(List<FruitListResponse> fruits, Context mContext, HomeFruitPresenter presenter) {
        this.fruits = fruits;
        this.mContext = mContext;
        this.presenter = presenter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fruit_list, parent, false);



        return new FruitAdapter.ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final FruitAdapter.ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_fruit_1.setText(fruits.get(position).getItem());
        holder.tv_fruit_2.setText(fruits.get(position).getCategory());
        holder.tv_fruit_3.setText(fruits.get(position).getFarmName());
        holder.tv_fruit_4.setText(fruits.get(position).getPhone1());


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return fruits.size();
    }
}