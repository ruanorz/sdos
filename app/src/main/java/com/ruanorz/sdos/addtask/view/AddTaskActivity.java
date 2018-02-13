package com.ruanorz.sdos.addtask.view;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ruanorz.sdos.R;
import com.ruanorz.sdos.addtask.interfaces.IAddTaskView;
import com.ruanorz.sdos.addtask.presenter.AddTaskPresenter;
import com.ruanorz.sdos.dbwrapper.Wrapper;
import com.ruanorz.sdos.models.Task;
import com.ruanorz.sdos.models.TaskGroup;
import com.ruanorz.sdos.models.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends AppCompatActivity implements IAddTaskView{

    @BindView(R.id.et_task_description)
    TextView et_task_description;
    @BindView(R.id.np_duration)
    NumberPicker np_duration;
    @BindView(R.id.rg_task_group)
    RadioGroup rg_task_group;
    @BindView(R.id.btn_add_task)
    ImageView btn_add_task;
    @BindView(R.id.btn_close_add_task)
    ImageView btn_close_add_task;
    @BindView(R.id.cv_result)
    CardView cv_result;
    @BindView(R.id.tv_task_name)
    TextView tv_task_name;
    @BindView(R.id.tv_task_assigned_to)
    TextView tv_task_assigned_to;
    @BindView(R.id.iv_result)
    ImageView iv_result;


    private AddTaskPresenter presenter;

    public String rb_clicked = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ButterKnife.bind(this);

        presenter = new AddTaskPresenter(getApplicationContext(), this);

        presenter.getAllSkillGroup();

        renderView();
    }

    public void renderView(){
        np_duration.setMaxValue(100);
        np_duration.setMinValue(1);


    }

    @Override
    public void getallSkillGroupOK(ArrayList<TaskGroup> skillgroups) {
        createRadioButton(skillgroups);
    }

    @Override
    public void getallSkillGroupKO() {

    }

    private void createRadioButton(final ArrayList<TaskGroup> skillgroups) {



        final RadioButton[] rb = new RadioButton[skillgroups.size()];
        RadioGroup rg = new RadioGroup(this); //create the RadioGroup
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        for(int i=0; i<skillgroups.size(); i++){
            rb[i]  = new RadioButton(this);
            rb[i].setText(skillgroups.get(i).getName());
            rb[i].setId(i + 100);
            final int idx= i;
            rb[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("error", "eeeeeeeeeee "+skillgroups.get(idx).getName());
                    rb_clicked = skillgroups.get(idx).getName();
                }
            });
            rg.addView(rb[i]);
        }

        rg_task_group.addView(rg);//you add the whole RadioGroup to the layout


    }

    @OnClick(R.id.btn_add_task)
    public void onBntAddTaskClicked(){

        if (!et_task_description.getText().toString().matches("") && !rb_clicked.equals("")){

            Task task = new Task();
            task.setName(et_task_description.getText().toString());
            task.setDuration(np_duration.getValue());
            task.setPending(true);
            TaskGroup sg = new TaskGroup();
            sg.setName(rb_clicked);


            Log.e("error", "eeeeeeeeeee "+et_task_description.getText()+" "+rb_clicked+" "+np_duration.getValue());

            presenter.assignTaskToUser(rb_clicked, task);

        }else{

            Toast.makeText(this, getString(R.string.complete_fields),
                    Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void taskAddedCompletedOK(User user) {

        tv_task_name.setText(getString(R.string.task_name, et_task_description.getText().toString()));
        tv_task_assigned_to.setText(getString(R.string.task_assigned_to, user.getName()));
        iv_result.setImageDrawable(this.getResources().getDrawable(R.drawable.task_added_ok));
        revealResult();

    }

    @Override
    public void taskAddedCompletedKO() {

    }

    @OnClick(R.id.btn_close_add_task)
    public void onBtnCloseAddTaskClicked(){
        finish();
    }

    public void revealResult(){
        btn_add_task.setVisibility(View.GONE);
        Log.e("error", "TASKADDEDCOMPLETED OK");
        cv_result.setVisibility(View.VISIBLE);
        int cx = cv_result.getWidth();
        int cy = cv_result.getHeight();

        int startX = (int) (btn_add_task.getX()+ (btn_add_task.getWidth()/2));
        int startY = (int) (btn_add_task.getY()+ (btn_add_task.getHeight()/2));

        float finalRadius = Math.max(cx, cy) * 1.2f;

        Animator reveal = ViewAnimationUtils
                .createCircularReveal(cv_result, startX, startY, 0f, finalRadius);

        reveal.setDuration(1000);

        reveal.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Wrapper.getInstance().getAllUser();
        Wrapper.getInstance().getAllTask();
    }

    @Override
    public void getFreeUsersForTaskKO() {

        tv_task_name.setText(getString(R.string.no_users));
        iv_result.setImageDrawable(this.getResources().getDrawable(R.drawable.close));

        revealResult();
    }
}
