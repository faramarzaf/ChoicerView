package com.faraaf.tictacdev.choicerview.Vasl.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.faraaf.tictacdev.choicerview.R;
import com.faraaf.tictacdev.choicerview.Vasl.choicer.PresetRadioGroup;
import com.faraaf.tictacdev.choicerview.Vasl.choicer.PresetValueButton;

public class MainActivity extends AppCompatActivity {

    PresetRadioGroup mSetDurationPresetRadioGroup;
    TextView textView;
    PresetValueButton phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        mSetDurationPresetRadioGroup.setOnCheckedChangeListener(new PresetRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View radioGroup, View radioButton, boolean isChecked, int checkedId) {
                if (phone.isChecked()) {
                    textView.setText("Phone");
                } else if (email.isChecked()) {
                    textView.setText("Email");
                }

            }
        });

    }


    private void bind() {

        textView = findViewById(R.id.witchID);
        mSetDurationPresetRadioGroup = findViewById(R.id.preset_time_radio_group);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);


    }


}
