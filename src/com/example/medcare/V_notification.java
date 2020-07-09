package com.example.medcare;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.medcare.ViewPrescription.getjson1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class V_notification extends Activity {
ListView l1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_v_notification);
		l1=(ListView)findViewById(R.id.listView1);
		
		
		
		
		new getjson1().execute();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.v_notification, menu);
		return true;
	}
	private class getjson1 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"notify/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
		                    String not_id = c.getString("not_id");
		                    String notification = c.getString("notification");
		                    String date = c.getString("date");
		                  
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("not_id", not_id);
		                    contact.put("date", date);
		                    contact.put("notification", notification);
		                    
		                    al.add(contact);
		                    
		                    
		                }
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else
					{
					}
					return null;
				}
				
				@Override
			      protected void onPostExecute(Void result) {
			         super.onPostExecute(result);
			         ListAdapter adapter = new SimpleAdapter(V_notification.this, al,
			            R.layout.noti, new String[]{ "date","notification"}, 
			               new int[]{R.id.textView4, R.id.textView1});
			         l1.setAdapter(adapter);
			      }
			}
}
