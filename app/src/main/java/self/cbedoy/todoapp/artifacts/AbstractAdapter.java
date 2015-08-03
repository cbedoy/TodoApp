package self.cbedoy.todoapp.artifacts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Carlos Bedoy on 7/29/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 * Pademobile
 */
public abstract class AbstractAdapter<T> extends BaseAdapter
{
    protected List<T> mDataModel;
    
    public AbstractAdapter(List<T> dataModel){
        mDataModel = dataModel;
    }

    @Override
    public int getCount() {
        return mDataModel.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataModel.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
