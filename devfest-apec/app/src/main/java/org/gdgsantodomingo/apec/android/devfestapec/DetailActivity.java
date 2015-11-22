package org.gdgsantodomingo.apec.android.devfestapec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by eonoe on 11/22/15.
 */
public class DetailActivity extends AppCompatActivity {

	public static final String EXTRA_NAME = "EXTRA_NAME";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
	}
}
