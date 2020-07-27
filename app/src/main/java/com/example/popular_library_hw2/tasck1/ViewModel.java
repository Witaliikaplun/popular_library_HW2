package com.example.popular_library_hw2.tasck1;

import io.reactivex.subjects.PublishSubject;

public class ViewModel {
    private Model model;
    private PublishSubject<String> subject;

    public ViewModel(){
        model = new Model();
        subject = PublishSubject.create();
    }

    public void updateModel(String str){
        model.setStr(str);
    }

    public PublishSubject<String> getSobject() {
        return subject;
    }

    public void read(){
        subject.onNext(model.getStr());
    }
}
