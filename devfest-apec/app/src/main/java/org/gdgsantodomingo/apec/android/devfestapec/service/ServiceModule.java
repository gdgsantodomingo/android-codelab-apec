package org.gdgsantodomingo.apec.android.devfestapec.service;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module(
    library = true,
    complete = false
)
public class ServiceModule {
    private static final String BASE_URL = "https://api.github.com";

    @Provides
    @Singleton Retrofit retrofitProvider() {
        return new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
    }

    @Provides
    @Singleton GitHubService gitHubServiceProvider(Retrofit retrofit) {
        return retrofit.create(GitHubService.class);
    }
}
