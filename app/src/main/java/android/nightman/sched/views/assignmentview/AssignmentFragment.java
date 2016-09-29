package android.nightman.sched.views.assignmentview;


import android.nightman.sched.backend.Sched;
import android.nightman.sched.backend.data.classes.ClassInterface;
import android.nightman.sched.backend.models.Assignment;
import android.nightman.sched.backend.models.Class;
import android.nightman.sched.backend.models.ClassHolder;
import android.nightman.sched.views.MainTabActivity;
import android.nightman.sched.views.util.RxBus;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.nightman.sched.R;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentFragment extends Fragment {
    Sched sched;
    AssignmentRecyclerViewAdapter adapter;
    RecyclerView classesRV;
    public OnRefreshFinished listener;

    private RxBus _rxBus;
    private CompositeSubscription _subscriptions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        _subscriptions = new CompositeSubscription();

        _subscriptions
                .add(_rxBus.toObservable()
                        .subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                String title = (String) o;
                                if (o.equals("Assignments")) {
                                    Toast.makeText(getContext(), "Oh shit, nigga you clicked the button. Kill yourself!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_assignment, container, false);
        //Create Sched object for interacting with api
        sched = new Sched();
        //UI
        classesRV = (RecyclerView)rootView.findViewById(R.id.assRec);
        //Set up listener for FAB
        listener = new OnRefreshFinished() {
            @Override
            public void onFinish(ClassHolder classHolder) {
                List<Assignment> assignments = new ArrayList<>();
                for (int i = 0; i < classHolder.getClasses().size(); i++) {
                    Class current = classHolder.getClasses().get(i);
                    for (int v = 0; v < current.getAssignments().size(); v++) {
                        assignments.add(current.getAssignments().get(v));
                    }
                }
                adapter = new AssignmentRecyclerViewAdapter(assignments);
                classesRV.setAdapter(adapter);
                classesRV.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        };
        //Populate rv
        refresh(listener);
        return rootView;
    }

    private interface OnRefreshFinished {
        void onFinish(ClassHolder classHolder);
    }
    private void refresh(OnRefreshFinished listener) {
        //Populate RecyclerView
        ClassInterface classAPI = sched.getClassAPI();
        Observable<ClassHolder> classes = classAPI.getClasses("Basic " +Base64.encodeToString("joyod3@gmail.com:nightman_420".getBytes(), Base64.NO_WRAP), 1);
        classes.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassHolder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ClassHolder classHolder) {
                        listener.onFinish(classHolder);
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _rxBus = ((MainTabActivity) getActivity()).getRxBusSingleton();
    }
}
