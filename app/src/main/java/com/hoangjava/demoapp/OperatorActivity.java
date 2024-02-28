package com.hoangjava.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hoangjava.demoapp.databinding.ActivityOperatorBinding;

public class OperatorActivity extends AppCompatActivity {

    private ActivityOperatorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOperatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button plus = binding.btnPlus;
        Button minus = binding.btnMinus;
        Button multiply = binding.btnMultiply;
        Button divide = binding.btnDivide;


        plus.setOnClickListener(v -> {
            onOperatorClick("+");
        });
        minus.setOnClickListener(v -> {
            onOperatorClick("-");
        });
        multiply.setOnClickListener(v -> {
            onOperatorClick("*");
        });
        divide.setOnClickListener(v -> {
            onOperatorClick("/");
        });
    }

    private void onOperatorClick(String operator) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("OPERATOR", operator);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}