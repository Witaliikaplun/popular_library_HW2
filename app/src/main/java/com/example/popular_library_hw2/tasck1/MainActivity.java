package com.example.popular_library_hw2.tasck1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.popular_library_hw2.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity{

    private TextView tv1;
    private TextView tv2;
    private ViewModel viewModel;
    private EventBus eventBus;
    private Consumer<String> consumer;
    private Observer<String> observer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et1 = findViewById(R.id.et1);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        viewModel = new ViewModel();
        eventBus = new EventBus();

        consumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                tv1.setText(s);
            }
        };

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("observer", "onSubscribe " + d);
            }

            @Override
            public void onNext(String s) {
                Log.d("observer", "onNext " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("observer", "onError " + e);
            }

            @Override
            public void onComplete() {
                Log.d("observer", "onComplete ");
            }
        };

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.getSobject().subscribe(consumer);
                viewModel.updateModel(charSequence.toString());
                viewModel.read();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("observer", "Start ");
                eventBus.getSubject().subscribe(observer);
                eventBus.onNexyt();
            }
        }).start();
    }
}