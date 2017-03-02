package com.example.raghu_gowda.mvp_sqlbrite;


import com.squareup.sqlbrite.SqlBrite;

import rx.Observable;

public interface UserQuery {

    Observable<SqlBrite.Query> queryDB();

}
