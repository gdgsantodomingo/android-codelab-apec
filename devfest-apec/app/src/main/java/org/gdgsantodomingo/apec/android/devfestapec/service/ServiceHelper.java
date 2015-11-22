package org.gdgsantodomingo.apec.android.devfestapec.service;

import org.gdgsantodomingo.apec.android.devfestapec.model.Repo;
import org.gdgsantodomingo.apec.android.devfestapec.model.User;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by eonoe on 11/22/15.
 */
public class ServiceHelper {

	private static final String BASE_URL = "https://api.github.com";
	private static GitHubService gitHubService;

	private ServiceHelper() {
		super();
	}

	private static GitHubService getInstance() {
		if (gitHubService == null) {
			setupRetrofitClient();
		}
		return gitHubService;
	}

	private static void setupRetrofitClient() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		gitHubService = retrofit.create(GitHubService.class);
	}

	public static Call<List<Repo>> listRepos(String user){
		return getInstance().listRepos(user);
	}

	public static Call<List<User>> listFollowers(String user){
		return getInstance().listFollowers(user);
	}

}
