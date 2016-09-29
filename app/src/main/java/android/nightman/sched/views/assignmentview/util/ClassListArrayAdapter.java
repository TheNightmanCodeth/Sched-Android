package android.nightman.sched.views.assignmentview.util;

import android.app.Activity;
import android.content.Context;
import android.nightman.sched.R;
import android.nightman.sched.backend.models.Class;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by joe on 9/29/16.
 */

public class ClassListArrayAdapter extends ArrayAdapter {
    List<Class> list;
    Activity activity;

    public ClassListArrayAdapter(Activity a, List<Class> list) {
        super(a, android.R.layout.simple_list_item_1, list);
        this.activity = a;
        this.list = list;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);

        if (tv == null) {
            tv = new TextView(activity);
        }
        tv.setText(list.get(position).getName());
        return tv;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            v = inflater.inflate(R.layout.spinner_item, null);
        }
        TextView title = (TextView) v.findViewById(R.id.spinner_title);
        title.setText(list.get(position).getName());
        return v;
    }
}
