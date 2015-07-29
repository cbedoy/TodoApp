package self.cbedoy.todoapp.artifacts;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;

/**
 * Created by Carlos Bedoy on 7/29/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 * Pademobile
 */
public class ApplicationLoader extends Application
{
    public static volatile Handler mMainHandler;
    public static volatile Context mMainContext;
    public static volatile LayoutInflater mMainLayoutInflater;

    @Override
    public void onCreate() {
        super.onCreate();

        mMainContext = getApplicationContext();

        mMainLayoutInflater = (LayoutInflater) mMainContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        mMainHandler = new Handler(getMainLooper());
    }
}
