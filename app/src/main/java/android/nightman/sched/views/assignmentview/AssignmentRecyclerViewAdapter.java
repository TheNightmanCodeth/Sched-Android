package android.nightman.sched.views.assignmentview;

import android.nightman.sched.R;
import android.nightman.sched.backend.models.Assignment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by joe on 9/22/16.
 */

public class AssignmentRecyclerViewAdapter extends RecyclerView.Adapter<AssignmentRecyclerViewHolder> {
    private List<Assignment> assignments;

    public AssignmentRecyclerViewAdapter(List<Assignment> l) {
        this.assignments = l;
    }

    @Override
    public AssignmentRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_list_item, parent, false);
        AssignmentRecyclerViewHolder vh = new AssignmentRecyclerViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AssignmentRecyclerViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.title.setText(Integer.toString(assignment.getClasss_id()));
        holder.descr.setText(assignment.getDesc());
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }
}
