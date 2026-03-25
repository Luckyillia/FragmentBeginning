package com.example.fragmentbeginning;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FirstFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText weightInput;
    private EditText heightInput;
    private Button calculateButton;
    private TextView resultTextView;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // Initialize views
        weightInput = getView().findViewById(R.id.weight);
        heightInput = getView().findViewById(R.id.height);
        calculateButton = getView().findViewById(R.id.button);
        resultTextView = getView().findViewById(R.id.resultTextView);

        // Set button click listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    private void calculateBMI() {
        String weightStr = weightInput.getText().toString().trim();
        String heightStr = heightInput.getText().toString().trim();

        // Validate inputs
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            showAlertDialog(String.valueOf(R.string.error_empty_fields));
            return;
        }

        try {
            double weight = Double.parseDouble(weightStr);
            double heightCm = Double.parseDouble(heightStr);

            // Validate positive numbers
            if (weight <= 0 || heightCm <= 0) {
                showAlertDialog(String.valueOf(R.string.error_zero_values));
                return;
            }

            // Convert height from cm to meters
            double heightM = heightCm / 100.0;

            // Calculate BMI (weight in kg, height in meters)
            double bmi = weight / (heightM * heightM);

            // Format result
            String result = String.valueOf(bmi);
            String category = getBMICategory(bmi);

            resultTextView.setText(result + " - " + category);
            resultTextView.setTextColor(getStatusColor(bmi));

        } catch (NumberFormatException e) {
            showAlertDialog("Wprowadź poprawne liczby");
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25) {
            return "Waga prawidłowa";
        } else if (bmi < 30) {
            return "Nadwaga";
        } else {
            return "Otyłość";
        }
    }
    private String getStatusColor(double bmi) {
        if (bmi < 18.5) {
            return R.color.status_warning;
        } else if (bmi < 25) {
            return R.color.status_good;
        } else if (bmi < 30) {
            return R.color.status_warning;
        } else {
            return R.color.status_bad;
        }
    }

    private void showAlertDialog(String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Blad");
        builder.setMessage(text);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"kliknięto OK",Toast.LENGTH_LONG).show();

            }
        });
        builder.create().show();
    }
}