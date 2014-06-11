package com.rssfeed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rssfeed.model.Entry;
import com.rssfeed.model.Feed;

public class MainActivity extends Activity {

	String url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topgrossingapplications/sf=143441/limit=25/json";
	TextView title;
	ListView listView;
	CustomAdapter adapter;
	Feed feed;
	private static String response = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		GetRSSFeed task = new GetRSSFeed();
		task.execute();
	}

	public class CustomAdapter extends ArrayAdapter<Feed> {
		private final Context context;
		private ArrayList<Entry> entry;
		View rowView;

		public CustomAdapter(Context context, ArrayList<Entry> objects) {
			super(context, R.layout.itemrow);
			this.context = context;
			this.entry = objects;
		}

		@Override
		public int getCount() {
			return entry.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = null;
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.itemrow, parent, false);

			TextView t = (TextView) row.findViewById(R.id.categoryId);
			ImageView i = (ImageView) row.findViewById(R.id.img_icon);
			t.setText(entry.get(position).getName().getLabel());
			Bitmap bitmap = null;
			try {
				bitmap = BitmapFactory.decodeStream((InputStream) new URL(
						entry.get(position).getImage().get(0).getLabel())
						.getContent());
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			i.setImageBitmap(bitmap);
			return row;

		}
	}
	/**
	 * Dislaying result in listview
	 * @param result
	 */
	private void displayDetails(String result) {
		try {
			JSONObject obj = new JSONObject(result);
			Gson gson = new Gson();
			JSONObject quizObject = obj.getJSONObject("feed");
			feed = gson.fromJson(quizObject.toString(), Feed.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		listView = (ListView) findViewById(R.id.listview);
		adapter = new CustomAdapter(MainActivity.this, feed.getEntry());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(MainActivity.this, Details.class);
				i.putExtra("feedData", feed);
				i.putExtra("position", arg2);
				startActivity(i);
			}
		});
		
	} 
	/**
	 * Http request to get Data
	 * @author amisha
	 *
	 */
	private class GetRSSFeed extends AsyncTask<Void, Void, String> {
		private ProgressDialog dialog;
		@Override
		protected String doInBackground(Void... params) {
			
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			try {
				HttpResponse execute = client.execute(httpGet);
				InputStream content = execute.getEntity().getContent();

				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(content));
				String s = "";
				while ((s = buffer.readLine()) != null) {
					response += s;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			dialog.cancel();
			System.out.print(result);
			displayDetails(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(MainActivity.this);
            dialog.setCancelable(true);
            dialog.setMessage("Retrieving Data...");
            dialog.show();
		}
	}

}
