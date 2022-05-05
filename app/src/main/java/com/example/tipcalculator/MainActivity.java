package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.text.Editable;
import android.text.TextWatcher;


import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat=
            NumberFormat.getPercentInstance();


    private double billAmount =0.0;
    private double percent =0.15;
    private    TextView amountTextView;
    private    TextView percentTextView;
    private    TextView tipTextView;
    private TextView totalTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountTextView=findViewById(R.id.amountTextView);
        percentTextView= findViewById(R.id.percentTextView);
        tipTextView= findViewById(R.id.tipTextView);
        totalTextView= findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));


        EditText amountEditText=  findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(getAmountEditTextWatcher);

        SeekBar percentSeekBar= findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

    }

    private void calculate(){
        percentTextView.setText(percentFormat.format(percent));

        double tip=billAmount * percent;
        double total= billAmount + tip;

        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));

    }
    private final OnSeekBarChangeListener seekBarListener=new OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
            percent =progress /100.0;
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    };

    private final TextWatcher getAmountEditTextWatcher= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {   }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billAmount = Double.parseDouble(s.toString())/100;
                amountTextView.setText(currencyFormat.format(billAmount));
            }
            catch (NumberFormatException e){
                amountTextView.setText("");
                billAmount = 0.0;
            }
            calculate();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


}
