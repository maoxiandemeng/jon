package com.yidong.jon.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends BaseActivity {
    private static final String TAG = "RxJavaActivity";
    @BindView(R.id.test)
    TextView test;

    //创建一个观察者，事件触发后会有怎样的行为
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.i(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
            Log.i(TAG, "onError: " + e.getMessage());
        }

        @Override
        public void onNext(String s) {
            Log.i(TAG, "onNext: " + s);
        }
    };

    //Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。
    // RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            observer.onNext("test");
            observer.onNext("test111");
            observer.onNext("test22");
        }
    });

    Observable<String> observableJust = Observable.just("Just1", "Just2");

    String[] from = {"From1", "From2"};
    Observable<String> observableFrom = Observable.from(from);
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.flat)
    TextView flat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observable.subscribe(observer);

        //上面这段代码中，由于 subscribeOn(Schedulers.io()) 的指定，被创建的事件的内容 1、2、3、4 将会在 IO 线程发出；
        // 而由于 observeOn(AndroidScheculers.mainThread()) 的指定，因此 subscriber 数字的打印将发生在主线程 。
        // 事实上，这种在 subscribe() 之前写上两句 subscribeOn(Scheduler.io()) 和 observeOn(AndroidSchedulers.mainThread())
        // 的使用方式非常常见，它适用于多数的 『后台线程取数据，主线程显示』的程序策略。
        Observable.just("Just1", "Just2").subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.i(TAG, "call: " + s);
                    }
                });

        Student student1 = new Student("1111", new Course[]{new Course("aa"),new Course("adsd")});
        Student student2 = new Student("2222", new Course[]{new Course("bb"),new Course("bdfs")});
        Student student3 = new Student("3333", new Course[]{new Course("cc"),new Course("cgewe")});
        Student[] students = {student1,student2,student3};
        //flatMap转化， 一对多转化
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        return Observable.from(student.getCourse());
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.i(TAG, "call: "+course.getName());
                    }
                });





        //map转化，将Integer类型转化为Bitmap对象 一对一转化
        Observable.just(R.drawable.taeyeon).map(new Func1<Integer, Bitmap>() {
            @Override
            public Bitmap call(Integer integer) {
                return BitmapFactory.decodeResource(getResources(), integer);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }
                });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_rx_java;
    }

    @OnClick(R.id.test)
    public void onClick() {
        observableJust.subscribe(observer);
        observableFrom.subscribe(observer);
        //也可以通过Action1回调
//        observableJust.subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.i(TAG, "call: "+s);
//            }
//        });


    }
}
