package com.example.raghu_gowda.mvp_sqlbrite;


import com.squareup.sqlbrite.SqlBrite;

import rx.Observable;

public interface QueryViewInterface {
    void completed();

    void onError(String message);

    void onEvent(SqlBrite.Query query);

    Observable<SqlBrite.Query> getEvents();
}
