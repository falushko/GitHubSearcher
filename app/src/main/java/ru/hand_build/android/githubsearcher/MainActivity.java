package ru.hand_build.android.githubsearcher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
    private Button mSearchButton;
    private EditText mEditText;
    private static final String QUERY = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.search_query);
        mSearchButton = (Button) findViewById(R.id.searchButton);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(QUERY, message);
                startActivity(intent);
            }
        });

    }

}
