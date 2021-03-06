package com.basmapp.marshal.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.basmapp.marshal.interfaces.UpdateServiceListener;
import com.basmapp.marshal.services.UpdateIntentService;

public class UpdateBroadcastReceiver extends BroadcastReceiver {

    Context mContext;
    UpdateServiceListener mUpdateServiceListener;

    public UpdateBroadcastReceiver(Context context, UpdateServiceListener updateServiceListener) {
        this.mContext = context;
        this.mUpdateServiceListener = updateServiceListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(UpdateIntentService.ACTION_CHECK_FOR_UPDATE)) {
            boolean result = intent.getBooleanExtra(UpdateIntentService.RESULT_CHECK_FOR_UPDATE, false);
            if (!result) {
//                mUpdateServiceListener.onProgressUpdate(context.getString(R.string.refresh_new_update), 0);
//                UpdateIntentService.startUpdateData(mContext);
                mUpdateServiceListener.onFinish(false);
            }
//            else {
//                mUpdateServiceListener.onProgressUpdate(context.getString(R.string.refresh_no_update), 100);
//                mUpdateServiceListener.onFinish(false);
//            }
        }
        if (intent.getAction().equals(UpdateIntentService.ACTION_UPDATE_DATA)) {
            boolean result = intent.getBooleanExtra(UpdateIntentService.RESULT_UPDATE_DATA, false);
            mUpdateServiceListener.onFinish(result);
//            if (result) {
//                mUpdateServiceListener.onFinish(true);
//            } else {
//                mUpdateServiceListener.onProgressUpdate(context.getString(R.string.refresh_update_failed), 0);
//                mUpdateServiceListener.onFinish(false);
//            }
        }
//        else if (intent.getAction().equals(UpdateIntentService.ACTION_UPDATE_DATA_PROGRESS_CHANGED)) {
//            int progress = intent.getIntExtra(UpdateIntentService.EXTRA_PROGRESS_PERCENT, 0);
//            mUpdateServiceListener.onProgressUpdate(context.getString(R.string.refresh_new_update), progress);
//        }
    }

}
