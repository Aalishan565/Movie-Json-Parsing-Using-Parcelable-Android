package com.moviejsonparsingusingparcelable;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aalishan on 20/10/16.
 */
public class MovieDownloadAsyncTask extends AsyncTask<String, Void, List<MovieModel>> {
    IMovieDownloadListener iMovieDownloadListener;
    HttpURLConnection connection;
    private BufferedReader reader;
    private List<MovieModel> mMovieModelList;

    public MovieDownloadAsyncTask(IMovieDownloadListener iMovieDownloadListener) {
        this.iMovieDownloadListener = iMovieDownloadListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<MovieModel> doInBackground(String... params) {
        mMovieModelList= new ArrayList<>();
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);

            }
            String finalData = buffer.toString();
            JSONObject jsonObject = new JSONObject(finalData);
            JSONArray jsonArray = jsonObject.getJSONArray("movies");
            StringBuffer finalBuffer = new StringBuffer();

            for (int i = 0; i < jsonArray.length(); i++) {
                MovieModel model = new MovieModel();
                JSONObject jsonFinalObject = jsonArray.getJSONObject(i);
                model.movie = jsonFinalObject.getString("movie");
                model.year = jsonFinalObject.getInt("year");
                mMovieModelList.add(model);
            }
            return mMovieModelList;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mMovieModelList;
    }

    @Override
    protected void onPostExecute(List<MovieModel> movieModels) {
        if (movieModels != null) {
            iMovieDownloadListener.onMovieDataDownloadSuccess(movieModels);
        } else {
            iMovieDownloadListener.onMovieDataFailure();
        }
    }
}

