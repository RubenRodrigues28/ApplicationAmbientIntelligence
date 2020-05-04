package tecnico.ulisboa.pt.smarthomeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_Enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Enter = findViewById(R.id.btn_Enter);

        Toast.makeText(getApplicationContext(), "Welcome back.", Toast.LENGTH_LONG);
        setListeners();
    }

    public void setListeners() {
        btn_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainConsoleActivity();
            }
        });
    }

    public void startMainConsoleActivity() {
        Intent intent = new Intent(this, MainConsoleActivity.class);
        startActivity(intent);
    }
}
