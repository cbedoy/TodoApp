package self.cbedoy.todoapp.artifacts;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import self.cbedoy.todoapp.R;
import self.cbedoy.todoapp.pojos.TODOPojo;

/**
 * Created by Carlos Bedoy on 7/29/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 * Pademobile
 */
public class TodoViewCell extends AbstractAdapter<TODOPojo> {

    public TodoViewCell(List<TODOPojo> dataModel) {
        super(dataModel);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;
        if(view == null){
            view = ApplicationLoader.mMainLayoutInflater.inflate(R.layout.todo_view_cell, null);
            viewHolder = new ViewHolder();
            viewHolder.titleView = (TextView) view.findViewById(R.id.title_view_cell);
            viewHolder.dateView = (TextView) view.findViewById(R.id.date_view_cel);
            viewHolder.descriptionView = (TextView) view.findViewById(R.id.description_view_cell);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        TODOPojo todoPojo = mDataModel.get(i);

        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String date = df.format(todoPojo.getDate());

        viewHolder.titleView.setText(todoPojo.getTitle());

        viewHolder.descriptionView.setText(todoPojo.getDescription());

        viewHolder.dateView.setText(date);


        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(750);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());

        viewHolder.titleView.startAnimation(animation);
        viewHolder.descriptionView.startAnimation(animation);
        viewHolder.dateView.startAnimation(animation);


        return view;
    }

    private class ViewHolder{
        TextView titleView;
        TextView descriptionView;
        TextView dateView;
    }
}
