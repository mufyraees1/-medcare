package com.example.medcare;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_Booking extends Activity {
Spinner doc,dep;
EditText date,valdate;
ListView list;
Button book,sub;
String res,departid;
String did[],docid[],depnm[],docnm[];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__booking);
		
		dep=(Spinner)findViewById(R.id.spinner1);
		list=(ListView)findViewById(R.id.listView1);
		
		sub=(Button)findViewById(R.id.button2);
		sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new getjson2().execute();
			}
		});
		new getjson1().execute();
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				HashMap<String,String> hmap=(HashMap<String, String>)arg0.getItemAtPosition(arg2);
				Intent i=new Intent(getApplicationContext(), Booking.class);
				i.putExtra("drid", hmap.get("drid"));
				i.putExtra("name", hmap.get("name"));
				startActivity(i);
			}
		});
		dep.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				departid=did[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	
	private class getjson1 extends AsyncTask<Void, Void, Void>{
		ArrayList<HashMap<String,String>> al=new ArrayList<HashMap<String,String>>();
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					String url = Login.url+"dreg/android/";
					JSONArray jdata=JsonHandler.GetJson(url);
					
					did=new String[jdata.length()];
					 depnm=new String[jdata.length()];
					
					if(jdata!=null)
					{
						try {
						for (int i = 0; i < jdata.length(); i++) {
		                    JSONObject c;
							c = jdata.getJSONObject(i);
		                    String dptid = c.getString("dptid");
		                    String name = c.getString("name");
		                   
		                    did[i]=dptid;
		                    depnm[i]=name;
		                    
		                    
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
			         ArrayAdapter<String> ad=new ArrayAdapter<String>(Add_Booking.this, android.R.layout.simple_spinner_item,depnm);
			         dep.setAdapter(ad);
			      }
			}
	
	
	
	
	
	
	private class getjson2 extends AsyncTask<Void, Void, Void>{
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
//		                    String dep = c.getString("dep");
		                    String depid = c.getString("deptid");
		                    
		                    
		                    
		                    HashMap<String, String> contact =  new HashMap<String, String>();
		                    contact.put("drid", drid);
		                    contact.put("name", name);
		                    contact.put("gen", gen);
		                    contact.put("adrs", adrs);
		                    contact.put("phn", phn);   
		                    contact.put("eml", eml);
		                    contact.put("qual", qual);
//		                    contact.put("dep", dep);
		                    contact.put("depid", depid);
		                    
		                 if(departid.equals(depid))
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
			         ListAdapter adapter = new SimpleAdapter(Add_Booking.this, al,
			            R.layout.doctorlist, new String[]{ "drid","name","gen","adrs","phn","eml","qual","depid"}, 
			               new int[]{R.id.textView9, R.id.textView10,R.id.textView11,R.id.textView12, R.id.textView13,R.id.textView14,R.id.textView15,R.id.textView16});
			         list.setAdapter(adapter);
			      }
			}
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__booking, menu);
		return true;
	}

}
