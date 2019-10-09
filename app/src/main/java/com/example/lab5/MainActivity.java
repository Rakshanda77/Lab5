package com.example.lab5;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final WebView webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/UI.html");


        final View newView = getLayoutInflater().inflate(R.layout.prompt_main, null);
        Button Btn = (Button) newView.findViewById(R.id.btn);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(newView);
        AlertDialog prompt = builder.create();



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prompt.show();

            }
        });


        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String number = ((TextView) newView.findViewById(R.id.num)).getText().toString();
                webView.evaluateJavascript("addNode("+ number +")",num -> {
                    Log.d("WEBVIEW", "cards are: " + num);
                });
                prompt.dismiss();
            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
        if (id == R.id.action_settings) {
            WebView webView = findViewById(R.id.webView);
            webView.reload();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
