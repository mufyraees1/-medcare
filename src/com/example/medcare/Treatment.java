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

public class Treatment extends Activity {
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_treatment);
		list=(ListView)findViewById(R.id.listView1);
		new getjson1().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.treatment, menu);
		return true;
	}
	public class getjson1 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"treatment/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
							
								    
		                    String pres = c.getString("treatmentid");
		                    String admitstat = c.getString("p_id");
		                    String date = c.getString("remark");
		                    String pid = c.getString("date");
		                    String drid = c.getString("admit_status");
		                    String drid1 = c.getString("drid");
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("pres", pres);
		                    contact.put("date", date);
		                    contact.put("admitstat", admitstat);
		                    contact.put("pid", pid);
		                    contact.put("drid", drid);
		                    contact.put("drid1", drid1);
		                    
		                    if(admitstat.equals(Login.uid))
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
			         ListAdapter adapter = new SimpleAdapter(Treatment.this, al,
			            R.layout.trae, new String[]{ "date","pid","drid","drid1"}, 
			               new int[]{R.id.textView9, R.id.textView10,R.id.textView11,R.id.textView12});
			         list.setAdapter(adapter);
			      }
			}
}
