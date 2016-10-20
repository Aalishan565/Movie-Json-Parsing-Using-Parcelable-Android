package com.moviejsonparsingusingparcelable;

import java.util.List;

/**
 * Created by aalishan on 20/10/16.
 */
public interface IMovieDownloadListener {
    void onMovieDataDownloadSuccess(List<MovieModel> movieModels);
    void onMovieDataFailure();
}
