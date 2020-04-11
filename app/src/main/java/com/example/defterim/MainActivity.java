package com.example.defterim;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView veriListele;
    //int IdAl = 0;
    private int id = 0;
    public int getId()
    {
        return id;
    }
    public void setID(int id){
        this.id = id;
    }

    private void init(){
        veriListele = findViewById(R.id.lstVeri);
        this.registerForContextMenu(veriListele);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        veriListele = findViewById(R.id.lstVeri);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MainActivity.this, activity_defter.class);
                startActivity(a);
            }
        });
        veriListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = veriListele.getItemAtPosition(position).toString();
                String[] itemBol = item.split(" - ");
                int IdAl = getId();
                IdAl = Integer.valueOf(itemBol[0].toString());
                setID(IdAl);
                String icerik = String.valueOf(itemBol[2].toString());
                String baslik = String.valueOf(itemBol[1].toString());
                String secilenItem = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, activity_defter.class);
                intent.putExtra("ID",IdAl);
                intent.putExtra("icerik",icerik);
                intent.putExtra("baslik",baslik);
                startActivity(intent);
            }
        });
        listele();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        if(v.getId() == R.id.lstVeri){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(veriListele.getItemAtPosition(info.position).toString());
            menu.add(0,0,0,"Sil");
        }
    }

    public boolean onContextItemSelected(MenuItem item)
    {
        veriTabani vt = new veriTabani(MainActivity.this);
        int id = getId();
        switch (item.getItemId())
        {
            case 0:
                vt.veriSil(id);

                Toast.makeText(this, "Silindi", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void onRestart()
    {
        super.onRestart();

        finish();

        startActivity(getIntent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.hakkinda) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Hakkında");
            dlg.setMessage("Ömer KOCAMAN\nomr_kcmn@outlook.com\n©2019");
            dlg.show();

        }


        return super.onOptionsItemSelected(item);
    }

    public void listele()
    {
        veriTabani vt = new veriTabani(MainActivity.this);
        List<String> list = vt.VeriListele();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1,list);
        veriListele.setAdapter(adapter);
    }

}
