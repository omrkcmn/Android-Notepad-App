package com.example.defterim;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class activity_defter extends AppCompatActivity {

    private EditText txtBaslik, txtIcerik;
    private ListView listVeri;
    int IdAl = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtBaslik = findViewById(R.id.txtBaslik);
        txtIcerik = findViewById(R.id.txtIcerik);
        listVeri = findViewById(R.id.lstVeri);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gelenBaslik = txtBaslik.getText().toString();
                String gelenIcerik = txtIcerik.getText().toString();
                veriTabani vt = new veriTabani(activity_defter.this);
                vt.veriEkle(gelenBaslik,gelenIcerik);
                Snackbar.make(view, "Kaydedildi!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String baslik = intent.getStringExtra("baslik");
        String icerik = intent.getStringExtra("icerik");
        txtBaslik.setText(baslik);
        txtIcerik.setText(icerik);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_defter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_edit) {
            Toast.makeText(this, "Düzenlendi", Toast.LENGTH_SHORT).show();
        }
        if(id == R.id.action_delete)
        {
            veriTabani vt = new veriTabani(activity_defter.this);
            try{
                Bundle extras = getIntent().getExtras();
                int ID = extras.getInt("ID");
                vt.veriSil(ID);
                finish();
                Toast.makeText(this, "Silindi", Toast.LENGTH_SHORT).show();
            }catch (Exception e){}

        }
        return super.onOptionsItemSelected(item);
    }
}
