package com.ruanorz.sdos.utils;

import android.content.Context;
import android.content.Intent;

import com.ruanorz.sdos.addtask.view.AddTaskActivity;
import com.ruanorz.sdos.home.HomeActivity;
import com.ruanorz.sdos.login.view.LoginActivity;

/**
 * Created by ruano on 09/02/2018.
 */

public class Intents {

    public static void goToLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static void goToHome(Context context, boolean isAdministrator){
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra(Constants.IS_ADMINISTRATOR, isAdministrator);
        context.startActivity(intent);

    }

    public static void goToAddTask(Context context){
        Intent intent = new Intent(context, AddTaskActivity.class);
        context.startActivity(intent);
    }
}
