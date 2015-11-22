package org.gdgsantodomingo.apec.android.devfestapec.service;

import org.gdgsantodomingo.apec.android.devfestapec.model.Repo;
import org.gdgsantodomingo.apec.android.devfestapec.model.User;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by eonoe on 11/22/15.
 */
public interface GitHubService {

	@GET("/users/{user}/followers")
	Call<List<User>> listFollowers(@Path("user") String user);

	@GET("/users/{user}/repos")
	Call<List<Repo>> listRepos(@Path("user") String user);
}
