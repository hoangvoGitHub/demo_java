package com.hoangjava.demoapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hoangjava.demoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    Button operator;
    EditText edtNA;
    EditText edtNB;
    TextView tvResult;

    ActivityResultLauncher<Intent> operatorActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
        // Validity checks
        if (Activity.RESULT_OK != result.getResultCode()) {
            Toast.makeText(this, "No Operator Selected", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = result.getData();
        if (intent == null) {
            Toast.makeText(this, "Activity hasn't returned an intent.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!intent.hasExtra("OPERATOR")) {
            Toast.makeText(this, "Activity hasn't returned extra data.", Toast.LENGTH_LONG).show();
            return;
        }
        String operator = intent.getStringExtra("OPERATOR");
        // Valid result returned
        Log.d(TAG, operator);
        int numA = Integer.parseInt(edtNA.getText().toString());
        int numB = Integer.parseInt(edtNB.getText().toString());
        onOperatorReceived(numA, numB, operator);
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        operator = binding.btnOperator;
        edtNA = binding.edtNA;
        edtNB = binding.edtNB;
        tvResult = binding.tvResult;
        operator.setOnClickListener(v -> {
            Intent operatorIntent = new Intent(this, OperatorActivity.class);
            startActivity(operatorIntent);
        });
    }


    private void onOperatorReceived(int numA, int numB, String operator) {
        int result = 0;
        switch (operator) {
            case "+": {
                result = numA + numB;
            }
            case "-": {
                result = numA - numB;
            }
            case "*": {
                result = numA * numB;
            }
            case "/": {
                result = numA / numB;
            }
        }
        Log.d(TAG, String.valueOf(result));
        tvResult.setText("A " + operator + " B = " + result);
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}