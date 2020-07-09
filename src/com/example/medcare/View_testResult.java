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

public class View_testResult extends Activity {
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_test_result);
		

		list=(ListView)findViewById(R.id.listView1);
		new getjson1().execute();
	}

	
	
	private class getjson1 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"testreslt/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
		                    String testdate = c.getString("testdate");
		                    String remark = c.getString("remark");
		                    String drid = c.getString("drid");
		                    String pid = c.getString("pid");
		                    String result = c.getString("result");
		                    
		                    String testrdate = c.getString("testrdate");
		                    String testname = c.getString("testname");
		                    String amnt = c.getString("amnt");
		                    String depnm = c.getString("depnm");
		                    
		                    
		                    
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("testdate", testdate);
		                    contact.put("remark", remark);
		                    contact.put("result", result);
		                    contact.put("pid", pid);
		                    contact.put("drid", drid);
		                    
		                    contact.put("testrdate", testrdate);
		                    contact.put("testname", testname);
		                    contact.put("amnt", amnt);
		                    contact.put("depnm", depnm);
		                    
		                    
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
			         ListAdapter adapter = new SimpleAdapter(View_testResult.this, al,
			            R.layout.testresult, new String[]{ "testdate","remark","drid","testrdate","testname","amnt","depnm"}, 
			               new int[]{R.id.textView9, R.id.textView10,R.id.textView11,R.id.textView12, R.id.textView13,R.id.textView14,R.id.textView15});
			         list.setAdapter(adapter);
			      }
			}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_test_result, menu);
		return true;
	}

}
