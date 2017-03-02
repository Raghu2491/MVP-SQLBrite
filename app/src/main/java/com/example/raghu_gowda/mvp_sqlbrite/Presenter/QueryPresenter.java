package com.example.raghu_gowda.mvp_sqlbrite.Presenter;


import android.util.Log;

import com.example.raghu_gowda.mvp_sqlbrite.QueryViewInterface;
import com.squareup.sqlbrite.SqlBrite;

import rx.Observer;

public class QueryPresenter extends BasePresenter implements Observer<SqlBrite.Query> {

    private QueryViewInterface queryViewInterface;

    public QueryPresenter(QueryViewInterface queryViewInterface) {
        this.queryViewInterface = queryViewInterface;
    }

    @Override
    public void onCompleted() {
        queryViewInterface.completed();
    }

    @Override
    public void onError(Throwable e) {
        queryViewInterface.onError(e.getMessage());
    }

    @Override
    public void onNext(SqlBrite.Query query) {
        queryViewInterface.onEvent(query);
    }

    public void fetchResult() {
        System.out.println("*****QueryPresenterFetchResult"+ getClass().getName());
        unSubscribeAll();

        subscribe(queryViewInterface.getEvents(),QueryPresenter.this);
    }

}
