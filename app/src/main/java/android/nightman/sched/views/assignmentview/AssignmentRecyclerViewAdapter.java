package android.nightman.sched.views.assignmentview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nightman.sched.R;
import android.nightman.sched.backend.models.Assignment;
import android.nightman.sched.backend.models.Class;
import android.nightman.sched.views.assignmentview.util.MiniRecyclerAdapter;
import android.nightman.sched.views.classview.ClassRecyclerViewAdapter;
import android.nightman.sched.views.util.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joe on 9/22/16.
 */

public class AssignmentRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentRecyclerViewHolder> {
    private List<Class> classes;
    private List<Drawable> badges = new ArrayList<>();
    private Context x;

    public AssignmentRecyclerViewAdapter(List<Class> l, Context x) {
        this.classes = l;
        this.x = x;
        badges.add(x.getDrawable(R.drawable.globe));
        badges.add(x.getDrawable(R.drawable.beaker));
    }

    @Override
    public AssignmentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_list_item, parent, false);
        AssignmentRecyclerViewHolder vh = new AssignmentRecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AssignmentRecyclerViewHolder holder, int position) {
        Class clazz = classes.get(position);
        // Set the badge and class name in card header
        holder.badge.setImageDrawable(badges.get(clazz.getBadge()));
        holder.className.setText(clazz.getName());
        //Populate the list
        MiniRecyclerAdapter adapter = new MiniRecyclerAdapter(clazz.getAssignments());
        holder.assignmentList.addItemDecoration(new DividerItemDecoration(x, DividerItemDecoration.VERTICAL_LIST));
        holder.assignmentList.setAdapter(adapter);
        holder.assignmentList.setLayoutManager(new LinearLayoutManager(x));
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }
}
