package com.DANP.android.Laboratorio10_2022;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotiWorker extends Worker {

    public NotiWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    private static final String TAG = NotiWorker.class.getName();

    @NonNull
    @Override
    public Result doWork() {

        Context context = getApplicationContext();
        try {
            Log.d(TAG, "doWork llamado");
            Utils.sendNotification(context);
            return Result.success();
        } catch (Throwable throwable) {
            Log.d(TAG, "Error al enviar notificación" + throwable.getMessage());
            return Result.failure();
        }

    }
}
