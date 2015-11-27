package org.gdgsantodomingo.apec.android.devfestapec.service;

import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.util.List;
import org.gdgsantodomingo.apec.android.devfestapec.model.Repo;
import org.gdgsantodomingo.apec.android.devfestapec.model.User;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.http.Path;

import static com.squareup.okhttp.ResponseBody.create;

public class MockGitHubService implements GitHubService {
    private static final Type LIST_REPOS_TYPE     = new TypeToken<List<Repo>>() {
    }.getType();
    private static final Type LIST_FOLLOWERS_TYPE = new TypeToken<List<User>>() {
    }.getType();

    private static final int UNSUCCESSFUL_ERROR_CODE = 5000;

    private final Gson gson = new Gson();

    private String listFollowersBody;
    private int    listFollowersStatusCode;

    @Override public Call<List<User>> listFollowers(@Path("user") final String user) {
        return new Call<List<User>>() {
            @Override public Response<List<User>> execute() throws IOException {
                return getResponse();
            }

            @Override public void enqueue(final Callback<List<User>> callback) {
                if (listFollowersStatusCode == UNSUCCESSFUL_ERROR_CODE) {
                    callback.onFailure(new UnknownHostException());
                    return;
                } else if (listFollowersStatusCode == -1 && listFollowersBody == null) {
                    return;

                }
                callback.onResponse(getResponse(), null);
            }

            @Override public void cancel() {

            }

            @Override public Call<List<User>> clone() {
                return null;
            }

            private Response<List<User>> getResponse() {
                if (listFollowersStatusCode == 200) {
                    List<User> repos = gson.fromJson(listFollowersBody, LIST_FOLLOWERS_TYPE);
                    return Response.success(repos);
                } else {
                    final ResponseBody responseBody = create(MediaType.parse("application/json"), listFollowersBody);

                    return Response.error(listFollowersStatusCode, responseBody);
                }
            }
        };
    }

    @Override public Call<List<Repo>> listRepos(@Path("user") final String user) {
        throw new UnsupportedOperationException();
    }

    public void respondListFollowersRequestWithBodyAndStatusCode(@NonNull final String body, final int statusCode) {
        this.listFollowersBody = body;
        this.listFollowersStatusCode = statusCode;
    }

    public void respondListFollowersRequestWithError() {
        this.listFollowersBody = null;
        this.listFollowersStatusCode = UNSUCCESSFUL_ERROR_CODE;
    }

    public void reset() {
        this.listFollowersBody = null;
        this.listFollowersStatusCode = -1;
    }
}
