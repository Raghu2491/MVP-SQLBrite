package com.example.raghu_gowda.mvp_sqlbrite.Presenter;

import android.util.Log;

import com.example.raghu_gowda.mvp_sqlbrite.Presenter.Presenter;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter implements Presenter {

    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate() {
        System.out.print("");
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        System.out.println("*****BasePresenteronResume"+getClass().getName());
        configureSubscription();

    }

    private CompositeSubscription configureSubscription() {
        System.out.println("*****BasePresenterConfig"+getClass().getName());
        if(compositeSubscription==null || compositeSubscription.isUnsubscribed()){
            compositeSubscription=new CompositeSubscription();
        }
        return compositeSubscription;
    }

    @Override
    public void onDestroy() {
        unSubscribeAll();
    }

    protected void unSubscribeAll() {

        if(compositeSubscription!=null){
            compositeSubscription.unsubscribe();
            compositeSubscription.clear();
        }
    }

    protected <E> void subscribe(Observable<E> observable, Observer<E> observer) {

        Subscription subscription = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.computation())
                .subscribe(observer);

        configureSubscription().add(subscription);
    }

}

