package org.gdgsantodomingo.apec.android.devfestapec;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import javax.inject.Inject;
import org.gdgsantodomingo.apec.android.devfestapec.app.BaseTestCase;
import org.gdgsantodomingo.apec.android.devfestapec.service.MockGitHubService;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.dannyroa.espresso_samples.recyclerview.RecyclerViewMatcher.withRecyclerView;
import static org.gdgsantodomingo.apec.android.devfestapec.service.GithubServiceSetupData.ERROR_RESPONSE;
import static org.gdgsantodomingo.apec.android.devfestapec.service.GithubServiceSetupData.VALID_RESPONSE_WITHOUT_FOLLOWERS;
import static org.gdgsantodomingo.apec.android.devfestapec.service.GithubServiceSetupData.VALID_RESPONSE_WITH_FOLLOWERS;
import static org.hamcrest.CoreMatchers.allOf;

public class MainActivityTest extends BaseTestCase<MainActivity> {
    @Inject MockGitHubService gitHubService;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
        gitHubService.reset();
    }

    public void testThatUiElementsAreVisible() {
        getActivity();
        onView(withText(R.string.app_name)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).check(matches(isDisplayed()));
        onView(withHint("Nombre de usuario")).check(matches(isDisplayed()));
    }

    public void testThatEmptyUserReturnsError() {
        getActivity();
        onView(withId(R.id.search)).perform(click());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.empty_username_error)))
            .check(matches(isDisplayed()));
    }

    public void testThatUserWithFollowersShowsInList() {
        gitHubService.respondListFollowersRequestWithBodyAndStatusCode(VALID_RESPONSE_WITH_FOLLOWERS, 200);
        getActivity();
        onView(withId(R.id.user_name)).perform(typeText("apec"));
        onView(withText("Buscar")).perform(click());
        onView(withRecyclerView(R.id.recycler_view).atPosition(0))
            .check(matches(new MyRowMatcher("myhduck")));

        onView(withRecyclerView(R.id.recycler_view).atPosition(1))
            .check(matches(new MyRowMatcher("trevor")));
    }

    public void testThatUserWithNoFollowersDisplaysMessage() {
        gitHubService.respondListFollowersRequestWithBodyAndStatusCode(VALID_RESPONSE_WITHOUT_FOLLOWERS, 200);
        getActivity();
        onView(withId(R.id.user_name)).perform(typeText("apec"));
        onView(withText("Buscar")).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Este usuario no tiene seguidores")))
            .check(matches(isDisplayed()));
    }

    public void testThatUnknownUserDisplaysErrorMessage() {
        gitHubService.respondListFollowersRequestWithBodyAndStatusCode(ERROR_RESPONSE, 404);
        getActivity();
        onView(withId(R.id.user_name)).perform(typeText("apec"));
        onView(withText("Buscar")).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Not Found")))
            .check(matches(isDisplayed()));
    }

    public void testThatInternetErrorDisplaysError() {
        gitHubService.respondListFollowersRequestWithError();
        getActivity();
        onView(withId(R.id.user_name)).perform(typeText("apec"));
        onView(withText("Buscar")).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Verifique su conexión")))
            .check(matches(isDisplayed()));
    }

    public static class MyRowMatcher extends BaseMatcher<View> {
        private final String text;

        public MyRowMatcher(final String text) {
            this.text = text;
        }

        @Override public boolean matches(final Object item) {
            if (item == null || !(item instanceof ViewGroup)) {
                return false;
            }

            ViewGroup viewGroup = (ViewGroup) item;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (!(viewGroup.getChildAt(i) instanceof TextView)) {
                    continue;
                }
                TextView textView = (TextView) viewGroup.getChildAt(i);
                return textView.getText().toString().equalsIgnoreCase(text);
            }
            return false;
        }

        @Override public void describeTo(final Description description) {

        }
    }
}
