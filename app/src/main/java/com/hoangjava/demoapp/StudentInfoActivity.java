package com.hoangjava.demoapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hoangjava.data.model.Gender;
import com.hoangjava.data.model.Hobby;
import com.hoangjava.data.model.Student;
import com.hoangjava.demoapp.databinding.ActivityStudentInfoBinding;

import java.util.HashSet;
import java.util.Set;

public class StudentInfoActivity extends AppCompatActivity {

    private ActivityStudentInfoBinding binding;

    private Student student;

    private Button saveButton;
    private EditText nameEditText;
    private EditText classEditText;
    private CheckBox singCheckBox;
    private CheckBox sportCheckBox;
    private CheckBox gameCheckBox;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStudentInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        saveButton = findViewById(R.id.saveButton);

        nameEditText = binding.nameEditText;
        classEditText = binding.classEditText;
        singCheckBox = binding.checkboxSing;
        sportCheckBox = binding.checkboxSport;
        gameCheckBox = binding.checkboxGame;
        maleRadioButton = binding.radioMale;
        femaleRadioButton = binding.radioFemale;
        saveButton = binding.saveButton;


        saveButton.setOnClickListener(v -> {
            if (isFormValid()) {
                String name = nameEditText.getText().toString();
                String studentClass = classEditText.getText().toString();
                student = new Student(
                        name, studentClass, extractHobbies(), extractGender()
                );
                Toast.makeText(this, "Successful", Toast.LENGTH_LONG).show();
                resetForm();
                return;
            }

            Toast.makeText(this, "Form invalid", Toast.LENGTH_LONG).show();
        });

        binding.reloadButton.setOnClickListener(v ->
                {
                    student = null;
                    resetForm();
                }
        );

        binding.displayButton.setOnClickListener(v -> {
            populateForm();
        });


    }

    private void populateForm() {
        if (student == null) {
            Toast.makeText(this, "Please save first", Toast.LENGTH_LONG).show();
            return;
        }
        nameEditText.setText(student.getName());
        classEditText.setText(student.getStudentClass());
        Set<Hobby> hobbies = student.getHobbies();

        gameCheckBox.setChecked(hobbies.contains(Hobby.Game));
        sportCheckBox.setChecked(hobbies.contains(Hobby.Sport));
        singCheckBox.setChecked(hobbies.contains(Hobby.Sing));
        maleRadioButton.setChecked(student.getGender() == Gender.Male);
        femaleRadioButton.setChecked(student.getGender() == Gender.Female);


    }

    private void resetForm() {
        nameEditText.setText("");
        classEditText.setText("");
        singCheckBox.setChecked(false);
        sportCheckBox.setChecked(false);
        gameCheckBox.setChecked(false);
    }


    private Set<Hobby> extractHobbies() {
        Set<Hobby> hobbies = new HashSet<>();

        if (singCheckBox.isChecked()) {
            hobbies.add(Hobby.Sing);
        }
        if (sportCheckBox.isChecked()) {
            hobbies.add(Hobby.Sport);
        }
        if (gameCheckBox.isChecked()) {
            hobbies.add(Hobby.Game);
        }
        return hobbies;
    }

    private Gender extractGender() {
        if (maleRadioButton.isChecked()) {
            return Gender.Male;
        }
        return Gender.Female;
    }


    private boolean isFormValid() {
        if (nameEditText.getText().toString().isEmpty()) return false;
        if (classEditText.getText().toString().isEmpty()) return false;
        if (!singCheckBox.isChecked() &&
                !sportCheckBox.isChecked() &&
                !gameCheckBox.isChecked()
        ) return false;

        if (!femaleRadioButton.isChecked() && !maleRadioButton.isChecked())
            return false;
        return true;
    }
}