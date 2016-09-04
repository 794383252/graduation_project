package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myapplication.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class adapter_memorandum extends BaseAdapter {

    int year;
    int month;
    int day;
    int hour;
    int minute;

    Context context;
    List<String> list;
    viewHolder holder;
    LayoutInflater inflater;

    public adapter_memorandum(Context context, List<String> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        getTime();
        if (view == null) {
            view = inflater.inflate(R.layout.memorandum_item, null);
            holder = new viewHolder();
            holder.memorandum_item_content = (TextView) view.findViewById(R.id.memorandum_item_content);
            holder.memorandum_item_time = (TextView) view.findViewById(R.id.memorandum_item_time);
            holder.memorandum_item_day = (TextView) view.findViewById(R.id.memorandum_item_day);
            view.setTag(holder);
        } else {
            holder = (viewHolder) view.getTag();
        }
        holder.memorandum_item_content.setText(list.get(i));
        holder.memorandum_item_time.setText(month + "月" + day + "日" + "  " + hour + ":" + minute);
        holder.memorandum_item_day.setText(month + "月");
        return view;
    }


    private void getTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    class viewHolder {
        TextView memorandum_item_content;
        TextView memorandum_item_time;
        TextView memorandum_item_day;
    }

}
