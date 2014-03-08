package edu.scu.ogstest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class APITask extends AsyncTask<Object, Void, APITask.APIResult> {
	static protected final boolean USE_JSON = true;
	
	public enum WhatToAccess {
		/*
		 * All we need is to add URLs and data Classes here.
		 * This class will take care of JSON-ifying the data objects,
		 * and talking to the API.
		 */
		SHOPPER_ACCOUNT("shopper_accounts", "shopper_account", ShopperAccount.class),
		INVENTORY_DETAIL("inventory_details", "inventory_detail", InventoryDetail.class),
		/* TODO Add other data tables here */
		;
		
		private String url;			// Plural form
		private String varName;		// Singular form
		private Class<?> clazz;		// Data class
		
		WhatToAccess(String url, String varName, Class<?> clazz) {
			this.url = url;
			this.varName = varName;
			this.clazz = clazz;
		}
		
		public String getUrl() {
			return url;
		}
		
		public String getVarName() {
			return varName;
		}
		
		public Class<?> getDataClass() {
			return clazz;
		}
	}
	
	public enum Operation {
		LIST,
		CREATE,
		READ,
		UPDATE,
		DELETE,
	}
	
	public enum APIErrorCode {
		OK,
		ERROR,
	}
	
	public class APIResult {
		public APIErrorCode error;
		public Operation op;
		public String status;
		public String content;
		public List<? extends APIData> dataList; // for list
		public APIData data; // for read

		public APIResult(APIErrorCode error, Operation op, String status, String content) {
			this.error = error;
			this.op = op;
			this.status = status;
			this.content = content;
		}
	}
	
	public interface Callback {
		void onResultCallback(APITask from, APIResult result);
	}
	
	protected WhatToAccess what;
	protected Callback callback;
	protected Gson gson = new Gson();
	
	/**
	 * Access to database (CRUDL)
	 * @param what		Which table to access
	 * @param cb		Callback interface to receive API status and result
	 */
	public APITask(WhatToAccess what, Callback cb) {
		this.what = what;
		this.callback = cb;
	}

	public APITask create(APIData data) {
		return (APITask) execute(Operation.CREATE, data);
	}
	
	public APITask listData() {
		return (APITask) execute(Operation.LIST);
	}
	
	public APITask read(int id) {
		return (APITask) execute(Operation.READ, null, id);
	}
	
	public APITask update(int id, APIData data) {
		return (APITask) execute(Operation.UPDATE, data, id);
	}
	
	public APITask delete(int id) {
		return (APITask) execute(Operation.DELETE, null, id);
	}
	
	@Override
	protected APIResult doInBackground(Object... args) {
		Operation op = (Operation) args[0];
		APIData data = args.length > 1 ? (APIData) args[1] : null;
		int id = args.length > 2 ? (Integer)args[2] : -1;
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpRequestBase httpreq = null;

		switch (op) {
		case CREATE:
			httpreq = new HttpPost(Params.Host + "/" + what.getUrl() + ".json");
			break;
		case LIST:
			httpreq = new HttpGet(Params.Host + "/" + what.getUrl() + ".json");
			break;
		case READ:
			httpreq = new HttpGet(Params.Host + "/" + what.getUrl() + "/" + id + ".json");
			break;
		case UPDATE:
			httpreq = new HttpPost(Params.Host + "/" + what.getUrl() + "/" + id + ".json");
			break;
		case DELETE:
			httpreq = new HttpDelete(Params.Host + "/" + what.getUrl() + "/" + id + ".json");
			break;
		default:
			break;
		}

		if (httpreq instanceof HttpPost) {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put(what.getVarName(), data);
	
			String json = gson.toJson(param);
	
			try {
				((HttpPost)httpreq).setEntity(new StringEntity(json));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httpreq.setHeader("Content-type", "application/json");
		}
		
		String statusLine = "", content = "";
		HttpResponse response = null;
		APIErrorCode errCode;
		try {
			response = httpclient.execute(httpreq);
			statusLine = response.getStatusLine().toString();
			errCode = APIErrorCode.OK;		
			content = EntityUtils.toString(response.getEntity(), "UTF-8");
		} catch (ClientProtocolException e) {
			statusLine = e.toString();
			errCode = APIErrorCode.ERROR;
		} catch (IOException e) {
			statusLine = e.toString();
			errCode = APIErrorCode.ERROR;
		}

		return new APIResult(errCode, op, statusLine, content);
	}

	@Override
	protected void onPostExecute(APIResult result) {
		if (callback == null)
			return;

		Type listType;
		switch (result.op) {
		case LIST:
			switch (what) {
			case SHOPPER_ACCOUNT:
				listType = new TypeToken<List<ShopperAccount>>() {
				}.getType();
				result.dataList = (List<ShopperAccount>) gson.fromJson(
						result.content, listType);
				break;
			case INVENTORY_DETAIL:
				listType = new TypeToken<List<InventoryDetail>>() {
				}.getType();
				result.dataList = (List<InventoryDetail>) gson.fromJson(
						result.content, listType);
				break;
			}
			break;
		case READ:
			APIData data = (APIData) gson.fromJson(result.content, what.getDataClass());
			result.data = data;
			break;
		default:
			break;
		}

		callback.onResultCallback(this, result);
	}
}
