package self.cbedoy.todoapp.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Carlos Bedoy on 7/29/15.
 * <p/>
 * Mobile App Developer - Todo App
 * <p/>
 * Pademobile
 */
public class TODOPojo implements Serializable
{
    private Date mDate;
    private String mTitle;
    private String mDescription;

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getTitle() {
        return mTitle;
    }
}
