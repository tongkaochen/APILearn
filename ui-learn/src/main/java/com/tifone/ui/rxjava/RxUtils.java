package com.tifone.ui.rxjava;

import android.util.Log;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

    public void zip() {
        Observable.zip(getIntegerObservable(), getStringObservable(), new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + ": " + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                logger(s);
            }
        });
    }

    public void interval() {
        final Flowable flowable = Flowable.interval(1, TimeUnit.SECONDS);

        flowable.take(5).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                logger("Num : " + aLong);
                if (aLong == 10) {
                    flowable.blockingSubscribe();
                }
            }
        });
        countDown(6).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                logger("Count down: " + aLong);
            }
        });
        countUp(6).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                logger("Count up: " + aLong);
            }
        });
        countUp2(5).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                logger("Count up2 : " + aLong);
            }
        });
    }

    private Flowable<Long> countUp2(final long time) {
        return Flowable.interval(1, TimeUnit.SECONDS)
                .take(time + 1);
    }

    private Observable<Long> countUp(final long time) {
        return Observable.interval(1, TimeUnit.SECONDS)
                .take(time + 1);
    }

    private Observable<Long> countDown(final long time) {
        return Observable.interval(1, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return time - aLong;
                    }
                }).take(time + 1);
    }

    public void unSubscribe() {

    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("msg 1");
                emitter.onNext("msg 2");
                emitter.onNext("msg 3");
                emitter.onNext("msg 4");
                emitter.onNext("msg 5");
                emitter.onComplete();
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onComplete();
            }
        });
    }

    public void repeat() {
        Observable.just(1, 2, 3)
                .repeat(3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        logger("Data : " + integer);
                    }
                });
    }

    public void range() {
        Observable.range(1, 5)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        logger("Data->" + integer);
                    }
                });
    }

    public void fromArray() {
        Integer[] array = {1, 3, 5, 7, 9};
        Observable.fromArray(array)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        logger("array: " + integer);
                    }
                });
    }

    public void fromIterable() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        Observable.fromIterable(list)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        logger("iterable: " + s);
                    }
                });
    }

    private static void logger(String msg) {
        Log.e("tifone", msg);
    }
}
