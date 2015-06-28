package ru.hand_build.android.githubsearcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
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
                if(message.isEmpty()) return;
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra(QUERY, message);
                startActivity(intent);
            }
        });
    }
}
