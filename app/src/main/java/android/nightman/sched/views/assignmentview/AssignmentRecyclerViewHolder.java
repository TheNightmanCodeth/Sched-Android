package android.nightman.sched.views.assignmentview;

import android.nightman.sched.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by joe on 9/22/16.
 */

public class AssignmentRecyclerViewHolder extends RecyclerView.ViewHolder{
    //Header
    CircleImageView badge;
    TextView className;
    //List
    RecyclerView assignmentList;
    //Footer
    AssignmentRecyclerViewHolder(View itemView) {
        super(itemView);
        badge = (CircleImageView) itemView.findViewById(R.id.assignment_card_header_badge);
        className = (TextView)itemView.findViewById(R.id.assignment_card_header_title);
        assignmentList = (RecyclerView) itemView.findViewById(R.id.assignment_card_list);
    }
}
