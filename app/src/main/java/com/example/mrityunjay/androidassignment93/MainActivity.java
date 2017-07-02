package com.example.mrityunjay.androidassignment93;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {// mainactivity implement adaptor

    ListView listView;
    ListViewAdapter listViewAdapter;
    int index=0;

    private final static String name[] = {"sakib","Varun","mj","priyanka","Ekansh","osama","Ggara"};

    private final static String number[] = {"9988778877", "9988778874","9988778844","9977654123","9874562103","7988778877","9968778877"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(this, name, number);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);
        registerForContextMenu(listView);
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(this, "Please Long press to Call/SMS....", Toast.LENGTH_SHORT).show();
        index=position;
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, 1, 0, "Call");
        menu.add(0, 2, 1, "Send SMS");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        try {
            if(item.getItemId()==1 && item.getGroupId()==0){
                Intent i=new Intent();
                i.setAction(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+number[index]));
                startActivity(i);
            }
            else if(item.getItemId()==2 && item.getGroupId()==0){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+ number[index])));
            }
            else{
                return false;
            }
            return true;
        } catch (Exception e) {
            Toast.makeText(this, "You can't make call on emulators.", Toast.LENGTH_LONG).show();
            Log.d("Exception: ", e.getMessage());
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
