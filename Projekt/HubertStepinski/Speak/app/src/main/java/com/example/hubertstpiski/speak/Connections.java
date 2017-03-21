package com.example.hubertstpiski.speak;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Connections extends AppCompatActivity {
    private ListView listView;
    private String[] languages;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connections);

        listView= (ListView) findViewById(R.id.list_item);
        initResources();
        initLanguagesListView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_connections, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast toast= Toast.makeText(getApplicationContext(),"Wyszukiwanie",Toast.LENGTH_SHORT);
    toast.show();
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    private void initLanguagesListView() {
        listView.setAdapter(new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                languages));
    }

    private void initResources() {
        Resources res = getResources();
        languages = res.getStringArray(R.array.computers);

    }
}
