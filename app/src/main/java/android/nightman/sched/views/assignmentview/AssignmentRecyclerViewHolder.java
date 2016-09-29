package android.nightman.sched.views.assignmentview;

import android.nightman.sched.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by joe on 9/22/16.
 */

public class AssignmentRecyclerViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView descr;

    AssignmentRecyclerViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        descr = (TextView) itemView.findViewById(R.id.desc);
    }
}
