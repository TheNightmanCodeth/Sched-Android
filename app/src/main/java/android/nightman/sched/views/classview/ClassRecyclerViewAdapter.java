package android.nightman.sched.views.classview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nightman.sched.R;
import android.nightman.sched.backend.models.Class;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joe on 9/11/16.
 */
public class ClassRecyclerViewAdapter extends RecyclerView.Adapter<ClassRecyclerViewHolder>{
    private List<Class> classes;
    private List<Drawable> badges = new ArrayList<>(); //0: Beaker, 1: Globe,

    public ClassRecyclerViewAdapter(List<Class> classes, Context x) {
        this.classes = classes;
        badges.add(0, x.getDrawable(R.drawable.beaker));
        badges.add(1, x.getDrawable(R.drawable.globe));
    }

    @Override
    public ClassRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_item, parent, false);
        ClassRecyclerViewHolder vh = new ClassRecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ClassRecyclerViewHolder holder, int position) {
        Class clazz = classes.get(position);
        holder.badge.setImageDrawable(badges.get(clazz.getBadge()));
        holder.title.setText(clazz.getName());
        holder.location.setText(clazz.getLocation());
        holder.professor.setText(clazz.getProfessor());
        holder.days.setText(clazz.getDays());
        holder.time.setText(clazz.getTime());
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }
}
