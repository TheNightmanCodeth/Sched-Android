package android.nightman.sched.views.classview;


import android.nightman.sched.backend.Sched;
import android.nightman.sched.backend.data.classes.ClassInterface;
import android.nightman.sched.backend.models.Class;
import android.nightman.sched.backend.models.ClassHolder;
import android.nightman.sched.views.util.ClassRecyclerViewAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.nightman.sched.R;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassesFragment extends Fragment {


    public ClassesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_classes, container, false);
        Sched sched = new Sched();
        RecyclerView classesRV = (RecyclerView)rootView.findViewById(R.id.classRec);
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
                        classesRV.setAdapter(new ClassRecyclerViewAdapter(classHolder.getClasses(), getContext()));
                        classesRV.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                });
        return rootView;
    }

}
