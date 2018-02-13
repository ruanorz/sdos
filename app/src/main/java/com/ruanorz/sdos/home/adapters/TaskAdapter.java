package com.ruanorz.sdos.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ruanorz.sdos.R;
import com.ruanorz.sdos.home.presenter.HomeTechnicalPresenter;
import com.ruanorz.sdos.models.Task;

import java.util.ArrayList;

/**
 * Created by ruano on 11/02/2018.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> tasks;
    static private Context mContext;
    public HomeTechnicalPresenter presenter;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tv_task_name;
        public TextView tv_task_duration;
        public CheckBox cb_completed;

        public ViewHolder(View v) {
            super(v);

            tv_task_name = (TextView) v.findViewById(R.id.tv_task_name);
            tv_task_duration = (TextView) v.findViewById(R.id.tv_task_duration);
            cb_completed = (CheckBox) v.findViewById(R.id.cb_completed);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaskAdapter(ArrayList<Task> tasks, Context mContext, HomeTechnicalPresenter presenter) {
        this.tasks = tasks;
        this.mContext = mContext;
        this.presenter = presenter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_task_list, parent, false);



        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tv_task_name.setText(tasks.get(position).getName());
        holder.tv_task_duration.setText(mContext.getString(R.string.task_duration, tasks.get(position).getDuration() + ""));
        if (tasks.get(position).isPending()) {
            holder.cb_completed.setChecked(false);
        } else {
            holder.cb_completed.setChecked(true);
        }

        holder.cb_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.updateTask(tasks.get(position));

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tasks.size();
    }
}