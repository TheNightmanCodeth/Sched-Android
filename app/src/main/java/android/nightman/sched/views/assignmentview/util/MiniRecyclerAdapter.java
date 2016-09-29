package android.nightman.sched.views.assignmentview.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nightman.sched.R;
import android.nightman.sched.backend.models.Assignment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joe on 9/29/16.
 */

public class MiniRecyclerAdapter extends RecyclerView.Adapter<MiniRecyclerViewHolder> {
    List<Assignment> assignments;
    String[] types = {"Homework", "Test", "Quiz", "Reading", "Project"};

    public MiniRecyclerAdapter(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public MiniRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_list_list_item, parent, false);
        MiniRecyclerViewHolder vh = new MiniRecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MiniRecyclerViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        Log.i("Position", position +"");
        Log.i("due", "" +assignment.getDue());
        Log.i("desc", "" +assignment.getDesc());
        holder.typeView.setText(types[assignment.getAsstype() - 1]);
        holder.descView.setText(assignment.getDesc());
        holder.dueView.setText(assignment.getDue());
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }
}
