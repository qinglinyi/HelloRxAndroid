package com.eiwana.hellorx;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class FlatMapActivity extends BaseActivity {

    private TextView simpleTv;
    public static final String TAG = "FlatMapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        displayHomeAsUpEnabled(true);
        simpleTv = (TextView) findViewById(R.id.simpleTv);

        // 场景1
//        ArrayList<Course> courses = new ArrayList<>();
//        courses.add(new Course("数学"));
//        courses.add(new Course("语文"));
//        courses.add(new Course("英语"));
//        Student studentA = new Student("张三", courses);
//        Student studentB = new Student("李四", courses);
//        Student studentC = new Student("王五", courses);
//        Student[] students = new Student[]{studentA, studentB, studentC};
//
//        //Observable.flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable
//        Observable
//                .from(students)
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//                        Log.d(TAG, student.getName());
//                        return Observable.from(student.getCourses());
//                    }
//                })
//                .subscribe(new Action1<Course>() {
//                    @Override
//                    public void call(Course course) {
//                        Log.d(TAG, course.getName());
//                        simpleTv.append(course.getName() + "\n");
//                    }
//                });

        // 场景2
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("ha");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("i");
        list.add("j");
        list.add("k");
        list.add("l");
        list.add("m");
        list.add("n");
        Observable<List<String>> observable = Observable.just(list);
//        observable.subscribe(new Action1<List<String>>() {
//            @Override
//            public void call(List<String> strings) {
//                for (int i = 0; i < strings.size(); i++) {
//                    Log.d(TAG, strings.get(i));
//                }
//            }
//        });

        // flatMap输出的新的 Observable正是我们在Subscriber想要接收的
        observable
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.d(TAG, s);
                    }
                });


    }

    static class Student {
        private String name;
        private List<Course> courses;

        public Student(String name, List<Course> courses) {
            this.name = name;
            this.courses = courses;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Course> getCourses() {
            return courses;
        }

        public void setCourses(List<Course> courses) {
            this.courses = courses;
        }
    }

    static class Course {
        private String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
