package com.example.raghu_gowda.mvp_sqlbrite;


import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Db_Adapter extends RecyclerView.Adapter<Db_Adapter.ViewHolder>{


    private final LayoutInflater inflater;
    List<String> queryResult;
    @Bind(R.layout.cardview)
    CardView _cardview;

    public Db_Adapter(LayoutInflater inflater) {
        this.inflater=inflater;
        queryResult=new ArrayList<>();
    }

    @Override
    public Db_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(Db_Adapter.ViewHolder holder, int position) {
        holder.textview.setText(queryResult.get(position));
    }

    @Override
    public int getItemCount() {
        return queryResult.size();
    }

    public void getQueryResult(SqlBrite.Query query) {
        System.out.println("*****ADAPTER getQueryResult"+ getClass().getName());
        Cursor cursor=query.run();
        int index=cursor.getColumnIndex(SQLFactory.SQLHelper.USER_NAME);
        while (cursor.moveToNext()){
            queryResult.add(cursor.getString(index));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textview;

        public ViewHolder(View itemView) {
            super(itemView);

            textview= (TextView) itemView.findViewById(R.id.text);

        }
    }
}
