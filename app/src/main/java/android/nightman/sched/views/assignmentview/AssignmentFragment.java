package android.nightman.sched.views.assignmentview;


import android.app.Dialog;
import android.nightman.sched.backend.Sched;
import android.nightman.sched.backend.data.assignments.AssignmentInterface;
import android.nightman.sched.backend.data.classes.ClassInterface;
import android.nightman.sched.backend.data.user.UserInterface;
import android.nightman.sched.backend.models.Assignment;
import android.nightman.sched.backend.models.Class;
import android.nightman.sched.backend.models.ClassHolder;
import android.nightman.sched.backend.models.Response;
import android.nightman.sched.backend.models.User;
import android.nightman.sched.views.assignmentview.util.ClassListArrayAdapter;
import android.nightman.sched.views.mainview.MainTabActivity;
import android.nightman.sched.views.util.RxBus;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.nightman.sched.R;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.nightman.sched.R.string.submit;

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
                                    final Dialog dialog = new Dialog(getContext());
                                    dialog.setContentView(R.layout.add_assignment_dialog);
                                    dialog.setTitle("Add Assignment...");

                                    Spinner type = (Spinner) dialog.findViewById(R.id.assignment_dialog_type);
                                    EditText desc= (EditText) dialog.findViewById(R.id.assignment_dialog_desc);
                                    EditText dateDay = (EditText) dialog.findViewById(R.id.assignment_dialog_date_day);
                                    EditText dateMon = (EditText) dialog.findViewById(R.id.assignment_dialog_date_month);
                                    Spinner classSpinner = (Spinner) dialog.findViewById(R.id.assignment_dialog_class);

                                    List<String> types = new ArrayList<String>();
                                    types.add("Homework");
                                    types.add("Test");
                                    types.add("Quiz");
                                    types.add("Reading");
                                    types.add("Project");

                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, types);
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    type.setAdapter(dataAdapter);

                                    UserInterface ui = sched.getAuthAPI();
                                    Observable<User> user = ui.getUser("Basic " +Base64.encodeToString("joyod3@gmail.com:nightman_420".getBytes(), Base64.NO_WRAP));
                                    user.subscribeOn(Schedulers.newThread())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Subscriber<User>() {
                                                @Override
                                                public void onCompleted() {

                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }

                                                @Override
                                                public void onNext(User user) {
                                                    ClassListArrayAdapter adapter = new ClassListArrayAdapter(getActivity(), user.getClasses());
                                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                    classSpinner.setAdapter(adapter);
                                                }
                                            });

                                    Button submit   = (Button) dialog.findViewById(R.id.assignment_dialog_submit_button);
                                    submit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //Get current user id
                                            UserInterface ui = sched.getAuthAPI();
                                            Observable<User> user = ui.getUser("Basic " +Base64.encodeToString("joyod3@gmail.com:nightman_420".getBytes(), Base64.NO_WRAP));
                                            user.subscribeOn(Schedulers.newThread())
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribe(new Subscriber<User>() {
                                                        @Override
                                                        public void onCompleted() {

                                                        }

                                                        @Override
                                                        public void onError(Throwable e) {

                                                        }

                                                        @Override
                                                        public void onNext(User user) {
                                                            AssignmentInterface ai = sched.getAssignmentAPI();
                                                            int type_id = 1;
                                                            String selectedType = (String) type.getSelectedItem();
                                                            switch (selectedType) {
                                                                case "Homework":
                                                                    type_id = 1;
                                                                    break;
                                                                case "Test":
                                                                    type_id = 2;
                                                                    break;
                                                                case "Quiz":
                                                                    type_id = 3;
                                                                    break;
                                                                case "Reading":
                                                                    type_id = 4;
                                                                    break;
                                                                case "Project":
                                                                    type_id = 5;
                                                                    break;
                                                            }
                                                            Class c = (Class) classSpinner.getSelectedItem();
                                                            Log.i("Class name:", "" +c.getName());
                                                            String due = dateMon.getText().toString() +"/" +dateDay.getText().toString();
                                                            Assignment toPush = new Assignment(69, type_id, desc.getText().toString(), due, c.getId());
                                                            Observable<Response> response = ai.createAssignment("Basic " +Base64.encodeToString("joyod3@gmail.com:nightman_420".getBytes(), Base64.NO_WRAP), user.getId(), c.getId(), toPush);
                                                            response.subscribeOn(Schedulers.newThread())
                                                                    .observeOn(AndroidSchedulers.mainThread())
                                                                    .subscribe(new Subscriber<Response>() {
                                                                        @Override
                                                                        public void onCompleted() {

                                                                        }

                                                                        @Override
                                                                        public void onError(Throwable e) {

                                                                        }

                                                                        @Override
                                                                        public void onNext(Response response) {
                                                                            Log.i("RESPONSE", response.getResponse());
                                                                        }
                                                                    });
                                                        }
                                                    });
                                        }
                                    });

                                    dialog.show();
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
        //Refresh drag listener
        SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.assignment_swipe_refresh);
        //TODO: getColor is deprecated
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorAccent2), getResources().getColor(R.color.colorAccent3));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(new OnRefreshFinished() {
                    @Override
                    public void onFinish() {
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        //Populate rv
        refresh(null);
        return rootView;
    }

    private interface OnRefreshFinished {
        void onFinish();
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
                        if (listener != null) {
                            listener.onFinish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ClassHolder classHolder) {
                        List<Class> classesWithAssignments = new ArrayList<Class>();
                        for (Class a : classHolder.getClasses()) {
                            if (a.getAssignments().size() > 0) {
                                classesWithAssignments.add(a);
                            }
                        }
                        adapter = new AssignmentRecyclerViewAdapter(classesWithAssignments, getContext());
                        classesRV.setAdapter(adapter);
                        classesRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        _rxBus = ((MainTabActivity) getActivity()).getRxBusSingleton();
    }
}
