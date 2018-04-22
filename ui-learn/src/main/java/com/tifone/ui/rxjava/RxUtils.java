package com.tifone.ui.rxjava;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by tongkao.chen on 2018/4/20.
 */

public class RxUtils {

    public void create() {
        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Event 1");
                emitter.onNext("Event 2");
                emitter.onNext("Event 3");
                emitter.onComplete();
            }
        });
        Observer observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                logger(d.isDisposed() + "");
            }

            @Override
            public void onNext(String s) {
                logger(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                logger("onComplete");
            }
        };

        Consumer consumer = new Consumer<String>() {
            @Override
            public void accept(String string) throws Exception {
                logger("consumer + " + string);
            }
        };

        observable.subscribe(observer);
        observable.subscribe(consumer);

    }
    private static void logger(String msg) {
        Log.e("tifone", msg);
    }
}
