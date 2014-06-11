package com.rssfeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rssfeed.model.Feed;

public class Details extends Activity {

	TextView titleView;
	TextView summary;
	TextView appRights;
	TextView appReleaseDate;
	TextView appArtist;
	ImageView appIcon;
	Button appPrice;
	private static Feed obj;
	private static int position;
	private static String favorites = null;
	private static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		titleView = (TextView) findViewById(R.id.title);
		titleView.setSelected(true);
		titleView.setEllipsize(TruncateAt.MARQUEE);
		titleView.setSingleLine(true);
		summary = (TextView) findViewById(R.id.summary);
		appIcon = (ImageView) findViewById(R.id.appIcon);
		appPrice = (Button) findViewById(R.id.appPrice);
		appRights = (TextView) findViewById(R.id.appRights);
		appReleaseDate = (TextView) findViewById(R.id.appReleaseDate);
		appArtist = (TextView) findViewById(R.id.appArtist);

		Intent i = getIntent();
		obj = (Feed) i.getSerializableExtra("feedData");
		position = i.getIntExtra("position", 0);

		titleView.setText(obj.getEntry().get(position).getTitle().getLabel());
		summary.setText(obj.getEntry().get(position).getSummary().getLabel());
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream((InputStream) new URL(obj
					.getEntry().get(position).getImage().get(1).getLabel())
					.getContent());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		appIcon.setImageBitmap(bitmap);
		appPrice.setText(obj.getEntry().get(position).getPrice().getLabel());
		appRights.setText(obj.getEntry().get(position).getRights().getLabel());
		appReleaseDate.setText(obj.getEntry().get(position).getReleaseDate()
				.getAttribute().getLabel());
		appArtist.setText(obj.getEntry().get(position).getArtist().getLabel());
		String webLink = obj.getEntry().get(position).getLink().getAttributes().getHref();
		TextView link = (TextView) findViewById(R.id.appWebLink);
		link.setText(Html.fromHtml(webLink));
		link.setMovementMethod(LinkMovementMethod.getInstance());
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		favorites = sharedPreferences.getString(obj.getEntry().get(position)
				.getTitle().getLabel().toString(), "");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.actionbar, menu);
		MenuItem item = menu.findItem(R.id.menu_item_share);

		if (favorites != null && !favorites.equals("")) {
			MenuItem m = (MenuItem) menu.findItem(R.id.action_save);
			m.setIcon(android.R.drawable.btn_star_big_on);

		}

		ShareActionProvider myShareActionProvider = (ShareActionProvider) item
				.getActionProvider();
		Intent myIntent = new Intent();
		myIntent.setAction(Intent.ACTION_SEND);
		String subject = "Check Out : "
				+ obj.getEntry().get(position).getTitle().getLabel();
		String shareMessage = obj.getEntry().get(position).getId().getLabel();
		myIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		myIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
		myIntent.setType("text/plain");
		myShareActionProvider.setShareIntent(myIntent);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_save:
			item.setIcon(android.R.drawable.btn_star_big_on);
			savePreferences(obj.getEntry().get(position).getTitle().getLabel()
					.toString(),
					new Gson().toJson(obj.getEntry().get(position)));
			break;
		}
		return true;
	}

	private void savePreferences(String key, String value) {
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
}
