package org.gdgsantodomingo.apec.android.devfestapec;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import javax.inject.Inject;
import org.gdgsantodomingo.apec.android.devfestapec.model.User;
import org.gdgsantodomingo.apec.android.devfestapec.service.GitHubService;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static org.gdgsantodomingo.apec.android.devfestapec.model.ClientError.fromErrorBody;

public class MainActivity extends AppCompatActivity {

    @Inject                   GitHubService gitHubService;
    @Bind(R.id.recycler_view) RecyclerView  mRecyclerView;
    @Bind(R.id.user_name)     EditText      userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.search) void search() {
        final String user = userName.getText().toString();
        if (user.isEmpty()) {
            Snackbar.make(userName, R.string.empty_username_error, LENGTH_SHORT).show();
            return;
        }

        gitHubService.listFollowers(user).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                if (response.code() == 200 && response.body() != null) {
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
                    mRecyclerView.setAdapter(new SimpleRecyclerViewAdapter(MainActivity.this, response.body()));
                } else {
                    Snackbar.make(mRecyclerView, fromErrorBody(response.errorBody()).getMessage(), LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Snackbar.make(mRecyclerView, getString(R.string.net_error_msg), Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
