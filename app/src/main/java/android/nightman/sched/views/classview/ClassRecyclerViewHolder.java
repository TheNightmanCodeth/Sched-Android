package android.nightman.sched.views.classview;

import android.nightman.sched.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by joe on 9/11/16.
 */
public class ClassRecyclerViewHolder extends RecyclerView.ViewHolder{
    CircleImageView badge;
    TextView title;
    TextView professor;
    TextView location;
    TextView days;
    TextView time;

    ClassRecyclerViewHolder(View itemView) {
        super(itemView);
        badge = (CircleImageView)itemView.findViewById(R.id.badge);
        title = (TextView)itemView.findViewById(R.id.title);
        professor = (TextView)itemView.findViewById(R.id.prof);
        location = (TextView)itemView.findViewById(R.id.loc);
        days = (TextView)itemView.findViewById(R.id.days);
        time = (TextView)itemView.findViewById(R.id.time);
    }
}
