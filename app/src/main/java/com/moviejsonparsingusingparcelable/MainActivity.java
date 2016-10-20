package com.moviejsonparsingusingparcelable;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMovieDownloadListener {
    private ProgressDialog progressDialog;
    private Button mBtnLoadData;
    private ListView mLvMovieData;
    private MovieAdapter movieAdapter;
    public static final String MOVIE_URL = "http://jsonparsing.parseapp.com/jsonData/moviesDemoList.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wWit Loading");
        mLvMovieData = (ListView) findViewById(R.id.lv_movie_data);
        mBtnLoadData = (Button) findViewById(R.id.btn_load_data);
        mBtnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                new MovieDownloadAsyncTask(MainActivity.this).execute(MOVIE_URL);

            }
        });
    }

    @Override
    public void onMovieDataDownloadSuccess(List<MovieModel> movieModels) {
        movieAdapter = new MovieAdapter(MainActivity.this, movieModels);
        mLvMovieData.setAdapter(movieAdapter);
        progressDialog.dismiss();
    }

    @Override
    public void onMovieDataFailure() {
        progressDialog.dismiss();
    }
}
