package org.gdgsantodomingo.apec.android.devfestapec.service;

import com.squareup.okhttp.OkHttpClient;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static org.gdgsantodomingo.apec.android.devfestapec.service.Api.BASE_URL;

@Module(
    library = true,
    complete = false
)
public class ServiceModule {

    @Provides
    @Singleton Retrofit retrofitProvider(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .client(okHttpClient)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
    }

    @Provides
    @Singleton GitHubService gitHubServiceProvider(Retrofit retrofit) {
        return retrofit.create(GitHubService.class);
    }

    @Provides OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
