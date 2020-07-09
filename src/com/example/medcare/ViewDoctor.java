package com.example.medcare;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ViewDoctor extends Activity {
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_doctor);
		

		list=(ListView)findViewById(R.id.listView1);
		new getjson1().execute();
		
		
	}

	
	private class getjson1 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"staffreg/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
							
											    
		                    String drid = c.getString("drid");
		                    String name = c.getString("name");
		                    String gen = c.getString("gender");
		                    String adrs = c.getString("address");
		                    String phn = c.getString("phone");
		                    
		                    String eml = c.getString("email");
		                    String qual = c.getString("qualification");
		                    String dep = c.getString("deptid");
		                   
		                    
		                    
		                    
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("drid", drid);
		                    contact.put("name", name);
		                    contact.put("gen", gen);
		                    contact.put("adrs", adrs);
		                    contact.put("phn", phn);   
		                    contact.put("eml", eml);
		                    contact.put("qual", qual);
		                    contact.put("dep", dep);
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
			         ListAdapter adapter = new SimpleAdapter(ViewDoctor.this, al,
			            R.layout.doctorlist, new String[]{ "drid","name","gen","adrs","phn","eml","qual","dep"}, 
			               new int[]{R.id.textView9, R.id.textView10,R.id.textView11,R.id.textView12, R.id.textView13,R.id.textView14,R.id.textView15,R.id.textView16});
			         list.setAdapter(adapter);
			      }
			}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_doctor, menu);
		return true;
	}

}
