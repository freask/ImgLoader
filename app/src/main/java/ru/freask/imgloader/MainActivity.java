package ru.freask.imgloader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRAS_KEY = "url";
    EditText urlText;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlText = (EditText) findViewById(R.id.editText);
        but = (Button) findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = urlText.getText().toString();
                if (text.equals(""))
                    Toast.makeText(MainActivity.this, "Empty url address", Toast.LENGTH_SHORT).show();
                else if (!Tools.checkUrlProtocol(text))
                    Toast.makeText(MainActivity.this, "Wrong url address", Toast.LENGTH_SHORT).show();
                else {
                    Intent i = new Intent(MainActivity.this, ImagesActivity.class);
                    i.putExtra(EXTRAS_KEY, text);
                    startActivity(i);
                }
            }
        });
    }

}
