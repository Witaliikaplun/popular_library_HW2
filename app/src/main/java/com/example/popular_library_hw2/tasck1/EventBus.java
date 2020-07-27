package com.example.popular_library_hw2.tasck1;

import io.reactivex.subjects.PublishSubject;

public class EventBus {
    private PublishSubject<String> subject;
    private Model2 model2;

    public EventBus(){
        model2 = new Model2();
        subject = PublishSubject.create();
        model2.getObservable1().subscribe(subject);
    }

    public PublishSubject<String> getSubject() {
                return subject;
    }
    public void onNexyt(){
        subject.onNext("свое сообщение");
    }
}
