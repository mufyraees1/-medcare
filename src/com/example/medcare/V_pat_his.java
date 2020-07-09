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

public class V_pat_his extends Activity {
ListView l1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_v_pat_his);
		l1=(ListView)findViewById(R.id.listView1);
		
		new getjson1().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.v_pat_his, menu);
		return true;
	}
	
	
	public class getjson1 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"patientreg/android1/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
		                    String pid = c.getString("pid");
		                    String pname = c.getString("pname");
		                    String date = c.getString("date");
		                    String doc = c.getString("doc");
		                    String treat = c.getString("treat");
		                    String app = c.getString("app");
		                    
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("pid", pid);
		                    contact.put("pname", pname);
		                    contact.put("date", date);
		                    contact.put("doc", doc);
		                    contact.put("treat", treat);
		                    contact.put("app", app);
		                    
		                    if(pid.equals(Login.uid))
		                    {
		                    al.add(contact);
		                    }
		                    
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
			         ListAdapter adapter = new SimpleAdapter(V_pat_his.this, al,
			            R.layout.history, new String[]{ "pname","date","doc","treat","app"}, 
			               new int[]{R.id.textView10, R.id.textView11,R.id.textView13,R.id.textView6,R.id.textView7});
			         l1.setAdapter(adapter);
			      }
			}
	
	
	
}
