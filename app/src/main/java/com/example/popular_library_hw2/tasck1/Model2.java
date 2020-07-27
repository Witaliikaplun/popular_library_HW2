package com.example.popular_library_hw2.tasck1;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class Model2 {
    private String[] str1;
    private Observable<String> observable1;
    private Observable<Long> observable2;

    public Model2(){
        str1 = new String[] {"1", "2", "3","4", "5", "6","7", "8", "9"};
        observable2 = Observable.interval(1, TimeUnit.SECONDS);
        observable1 = Observable.fromArray(str1)
        .zipWith(observable2, new BiFunction<String, Long, String>() {
            @Override
            public String apply(String s, Long aLong) throws Exception {
                return s + " " + aLong;
            }
        });
    }

    public Observable<String> getObservable1() {
        return observable1;
    }

}
