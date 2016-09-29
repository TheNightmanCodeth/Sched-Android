package android.nightman.sched.views.classview;


import android.app.Dialog;
import android.nightman.sched.backend.Sched;
import android.nightman.sched.backend.data.classes.ClassInterface;
import android.nightman.sched.backend.models.Class;
import android.nightman.sched.backend.models.ClassHolder;
import android.nightman.sched.backend.models.Response;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassesFragment extends Fragment {
    Sched sched;
    ClassRecyclerViewAdapter adapter;
    RecyclerView classesRV;

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
        refresh();
        _subscriptions
                .add(_rxBus.toObservable()
                    .subscribe(new Action1<Object>() {
                        @Override
                        public void call(Object o) {
                            String title = (String) o;
                            if (o.equals("Classes")) {
                                addClass();
                            }
                        }
                    }));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_classes, container, false);
        //Create Sched object for interacting with api
        sched = new Sched();
        //UI
        classesRV = (RecyclerView)rootView.findViewById(R.id.classRec);
        //Populate rv
        refresh();
        return rootView;
    }

    public void addClass() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_class_dialog);
        dialog.setTitle("Add Class...");

        EditText nameET = (EditText) dialog.findViewById(R.id.nameField);
        EditText profET = (EditText) dialog.findViewById(R.id.profField);
        EditText locaET = (EditText) dialog.findViewById(R.id.locaField);
        EditText fromET = (EditText) dialog.findViewById(R.id.timeFromField);
        EditText toET   = (EditText) dialog.findViewById(R.id.timeToField);

        Button submit   = (Button) dialog.findViewById(R.id.submitClassButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassInterface ci = sched.getClassAPI();
                Observable<Response> response = ci.createClass("Basic " +Base64.encodeToString("joyod3@gmail.com:nightman_420".getBytes(), Base64.NO_WRAP), 1, new Class(69, nameET.getText().toString(), 1, profET.getText().toString(), locaET.getText().toString(), getDaysString(dialog), fromET.getText().toString() +" - " +toET.getText().toString(), 0, null));
                response.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Response>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onNext(Response response) {
                                Log.i("CLASSES_FRAGMENT", response.getResponse());
                                refresh();
                                dialog.dismiss();
                            }
                        });
            }
        });

        dialog.show();
    }

    private String getDaysString(Dialog d) {
        CheckBox U, M, T, W, r, F, S;
        U = (CheckBox) d.findViewById(R.id.sunday);
        M = (CheckBox) d.findViewById(R.id.monday);
        T = (CheckBox) d.findViewById(R.id.tuesday);
        W = (CheckBox) d.findViewById(R.id.wednesday);
        r = (CheckBox) d.findViewById(R.id.thursday);
        F = (CheckBox) d.findViewById(R.id.friday);
        S = (CheckBox) d.findViewById(R.id.saturday);

        String days = "";

        if (U.isChecked()) days += "U";
        if (M.isChecked()) days += "M";
        if (T.isChecked()) days += "T";
        if (W.isChecked()) days += "W";
        if (r.isChecked()) days += "R";
        if (F.isChecked()) days += "F";
        if (S.isChecked()) days += "S";

        return days;
    }
    private void refresh() {
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
                        adapter = new ClassRecyclerViewAdapter(classHolder.getClasses(), getContext());
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
