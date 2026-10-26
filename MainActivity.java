package com.example.myapplicationdb;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText ed1;
    TextView t1;
    Button b1, b2;
    DatabaseHelper dbHelper;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.ed1);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        t1 = findViewById(R.id.t1);
        dbHelper = new DatabaseHelper(this);
        b1.setOnClickListener(v -> {
            String name = ed1.getText().toString().trim();
            if (name.isEmpty()) {
                ed1.setError("Enter a name");
                return;
            }
            if (dbHelper.addName(name)) {
                Toast.makeText(this, "Name added",
                        Toast.LENGTH_SHORT).show();
                ed1.setText("");
            } else {
                Toast.makeText(this, "Failed to add name",
                        Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(v -> {
            String names = dbHelper.getAllNames();
            t1.setText(names);
        });
    }
    @Override

    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }
        super.onDestroy();
    }
}