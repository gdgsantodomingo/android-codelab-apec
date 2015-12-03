package org.gdgsantodomingo.apec.android.devfestapec.app;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.gdgsantodomingo.apec.android.devfestapec.MainActivityTest;
import org.gdgsantodomingo.apec.android.devfestapec.service.GitHubService;
import org.gdgsantodomingo.apec.android.devfestapec.service.MockGitHubService;

@Module(
    complete = false,
    overrides = true,
    library = true,
    injects = {
                  BaseTestCase.class,
                  MainActivityTest.class
    }
)
public class TestModule {
    @Provides @Singleton GitHubService gitHubService(MockGitHubService mockGitHubService) {
        return mockGitHubService;
    }

    @Provides @Singleton MockGitHubService mockGitHubServiceProvider() {
        return new MockGitHubService();
    }
}
