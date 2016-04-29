package sr.ice.impl;

import Ice.Current;
import Slice.CategoryName;
import Slice._UserDisp;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by P on 27.04.2016.
 */
public class UserI extends _UserDisp {
    private static long idPool = 0;
    private long id;
    private CategoryName categoryName;
    public long creationalTime;

    public UserI(CategoryName categoryName) {
        this.id = idPool++;
        this.categoryName = categoryName;
    }

    public UserI(long id, CategoryName categoryName) {
        this.id = id;
        this.categoryName = categoryName;
        this.creationalTime = new Date().getTime();
    }

    @Override
    public long getId(Current __current) {
        return id;
    }

    @Override
    public CategoryName getCategory(Current __current) {
        return categoryName;
    }

    @Override
    public String getCreationTime(Current __current) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(creationalTime);

        int mHours = calendar.get(Calendar.HOUR);
        int mMinutes = calendar.get(Calendar.MINUTE);
        int mSeconds = calendar.get(Calendar.SECOND);
        return mHours + ":" + mMinutes + ":" + mSeconds;
    }
}
