package edu.scu.ogstest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import android.os.AsyncTask;
import android.widget.TextView;

public class APITask extends AsyncTask<String, Void, String> {
	static protected final boolean USE_JSON = true;
	
	TextView textViewResult;
	public APITask(TextView res) {
		textViewResult = res;
	}
	
	@Override
	protected String doInBackground(String... arg0) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost;
		
		if (USE_JSON) {
			httppost = new HttpPost(Params.Host + "/shopper_accounts.json");

			// JSON VERSION
			ShopperAccount shopper_account = new ShopperAccount();
			shopper_account.userName = "test_username_" + (new Random()).nextInt(100);
			shopper_account.password = "1234";
			shopper_account.firstName = "Tom";
			shopper_account.lastName = "LastNameOfTom";
			shopper_account.streetAddress = "n/a";
			shopper_account.city = "n/a";
			shopper_account.state = "n/a";
			shopper_account.zipcode = "n/a";
			shopper_account.phone = "n/a";
			shopper_account.email = "n/a";

			Map<String,Object> param = new HashMap<String,Object>();
			param.put("shopper_account", shopper_account);

			String json = new Gson().toJson(param);

			try {
				httppost.setEntity(new StringEntity(json));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httppost.setHeader("Content-type", "application/json");
		} else {
			httppost = new HttpPost(Params.Host + "/shopper_accounts");
			
			// URL ENCODING VERSION
			HashMap<String,String> data = new HashMap<String,String>();
			data.put("shopper_account[userName]",
					"test_username_" + (new Random()).nextInt(100));
			data.put("shopper_account[password]", "1234");
			data.put("shopper_account[firstName]", "Tom");
			data.put("shopper_account[lastName]", "LastNameOfTom");
			data.put("shopper_account[streetAddress]", "n/a");
			data.put("shopper_account[city]", "n/a");
			data.put("shopper_account[state]", "n/a");
			data.put("shopper_account[zipcode]", "n/a");
			data.put("shopper_account[phone]", "n/a");
			data.put("shopper_account[email]", "n/a");

			List<NameValuePair> nvlist = new ArrayList<NameValuePair>();
			for (Map.Entry<String,String> ent : data.entrySet()) {
				nvlist.add(new BasicNameValuePair(ent.getKey(), ent.getValue()));
			}
			
			try {
				httppost.setEntity(new UrlEncodedFormEntity(nvlist));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httppost.setHeader("Content-type", "application/x-www-form-urlencoded");
		}
		
		String result = null;
		HttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
			result = response.getStatusLine().toString();
		} catch (ClientProtocolException e) {
			result = e.toString();
		} catch (IOException e) {
			result = e.toString();
		}
		
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		textViewResult.setText(result);
	}
}
