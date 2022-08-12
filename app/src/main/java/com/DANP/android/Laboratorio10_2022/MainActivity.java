package com.DANP.android.Laboratorio10_2022;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    WorkManager workManager;
    AppCompatButton btnStartOneTimeRequest, btnStartPeriodicRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workManager = WorkManager.getInstance();
        btnStartOneTimeRequest = findViewById(R.id.btn_one_time);
        btnStartPeriodicRequest = findViewById(R.id.btn_periodic_time);

        btnStartOneTimeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constraints constraints = new Constraints.Builder()
                        .setRequiresCharging(true)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder
                        (NotiWorker.class)
                        .setConstraints(constraints)
                        .build();
                Constraints constraints2 = new Constraints.Builder()
                        .setRequiresCharging(true)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();
                OneTimeWorkRequest oneTimeWorkRequest2 = new OneTimeWorkRequest.Builder
                        (NotiWorker2.class)
                        .setConstraints(constraints2)
                        .build();

                workManager.beginWith(oneTimeWorkRequest).then(oneTimeWorkRequest2).enqueue();

                /*workManager.enqueue(oneTimeWorkRequest);
                workManager.enqueue(oneTimeWorkRequest2);*/
            }
        });

        btnStartPeriodicRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constraints constraints = new Constraints.Builder()
                        .setRequiresCharging(false)
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
                         NotiWorker.class, 1, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();
                workManager.enqueue(periodicWorkRequest);
            }
        });


    }
}
