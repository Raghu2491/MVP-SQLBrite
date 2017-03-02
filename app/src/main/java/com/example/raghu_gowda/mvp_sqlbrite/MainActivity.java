package com.example.raghu_gowda.mvp_sqlbrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.raghu_gowda.mvp_sqlbrite.Presenter.QueryPresenter;
import com.squareup.sqlbrite.SqlBrite;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;

public class MainActivity extends AppCompatActivity implements QueryViewInterface {

    QueryPresenter queryPresenter;
    SQLFactory sqlFactory;
    
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    Db_Adapter dbadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);


        queryPresenter=new QueryPresenter(MainActivity.this);
        queryPresenter.onCreate();

        initDataBase();
        initRecyclerView();

    }

    private void initRecyclerView() {
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        dbadapter=new Db_Adapter(getLayoutInflater());
        recyclerView.setAdapter(dbadapter);
    }

    private void initDataBase() {
        sqlFactory=new SQLFactory(this);
        sqlFactory.insert();
        //sqlFactory.queryDB();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("*****Mainactivity on Resume"+getClass().getName());
        queryPresenter.onResume();
        queryPresenter.fetchResult();

    }



    @Override
    public void completed() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onEvent(SqlBrite.Query query) {
        System.out.println("*****MainActivity onEvent"+ getClass().getName());
        dbadapter.getQueryResult(query);
    }

    @Override
    public Observable<SqlBrite.Query> getEvents() {
        System.out.println("*****MainActivity onEvents Service Called"+ getClass().getName());

        return sqlFactory.queryDB();
    }
}
