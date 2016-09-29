package android.nightman.sched.views.assignmentview.util;

import android.nightman.sched.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by joe on 9/29/16.
 */

public class MiniRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView typeView;
    TextView descView;
    TextView dueView;

    MiniRecyclerViewHolder(View itemView) {
        super(itemView);
        typeView = (TextView) itemView.findViewById(R.id.assignment_card_list_type);
        descView = (TextView) itemView.findViewById(R.id.assignment_card_list_desc);
        dueView  = (TextView) itemView.findViewById(R.id.assignment_card_list_duedate);
    }
}
