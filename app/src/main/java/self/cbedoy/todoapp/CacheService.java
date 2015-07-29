package self.cbedoy.todoapp;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import self.cbedoy.todoapp.artifacts.ApplicationLoader;

/**
 * Created by Carlos Bedoy on 7/29/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 * Pademobile
 */
public class CacheService<T>
{
    private static CacheService instance;
    private final String        mFolderStorage;

    public static CacheService getInstance(){
        if(instance == null)
            instance = new CacheService();
        return instance;
    }

    public CacheService()
    {
        File external_files_dir = ApplicationLoader.mMainContext.getExternalFilesDir(null);
        String applicationName = "TodoApp";
        if(external_files_dir != null && this.isExternalStorageWritable())
        {
            this.mFolderStorage = external_files_dir.getAbsolutePath() + File.separator + applicationName + File.separator + "cache" + File.separator;
        }
        else
        {
            this.mFolderStorage = ApplicationLoader.mMainContext.getFilesDir().getAbsolutePath() + File.separator + applicationName + File.separator + "cache" + File.separator;
        }
        new File(this.mFolderStorage).mkdirs();
    }


    public boolean writeCacheForSHA(String sha, List<T> serviceList)
    {
        try
        {
            String path = this.mFolderStorage + sha;
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serviceList);
            objectOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    public List<T> readCacheFromSHA(String sha)
    {
        ArrayList<T> data = new ArrayList<T>();
        try
        {
            String path = this.mFolderStorage + sha;
            File file = new File(path);
            FileInputStream fileInputStream  = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            data = (ArrayList<T>) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (Exception ignored)
        {

        }
        return data;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

}
