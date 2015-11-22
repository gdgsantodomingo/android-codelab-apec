package org.gdgsantodomingo.apec.android.devfestapec;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.gdgsantodomingo.apec.android.devfestapec.SimpleRecyclerViewAdapter.ViewHolder;
import org.gdgsantodomingo.apec.android.devfestapec.model.User;

import java.util.List;

/**
 * Created by eonoe on 11/22/15.
 */
public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
	private final TypedValue mTypedValue = new TypedValue();
	private int        mBackground;
	private List<User> mValues;


	public SimpleRecyclerViewAdapter(Context context, List<User> items) {
		context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
		mBackground = mTypedValue.resourceId;
		mValues = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.list_item, parent, false);
		view.setBackgroundResource(mBackground);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final String userLogin = mValues.get(position).getLogin();
		holder.mTextView.setText(mValues.get(position).getLogin());

		holder.mView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Context context = v.getContext();
				Intent intent = new Intent(context, DetailActivity.class);
				intent.putExtra(DetailActivity.EXTRA_NAME, userLogin);
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		return mValues.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public final View      mView;
		public final ImageView mImageView;
		public final TextView  mTextView;

		public ViewHolder(View view) {
			super(view);
			mView = view;
			mImageView = (ImageView) view.findViewById(R.id.avatar);
			mTextView = (TextView) view.findViewById(android.R.id.text1);
		}

		@Override
		public String toString() {
			return super.toString() + " '" + mTextView.getText();
		}
	}
}
