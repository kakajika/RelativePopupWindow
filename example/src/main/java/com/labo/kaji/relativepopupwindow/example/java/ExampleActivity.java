package com.labo.kaji.relativepopupwindow.example.java;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.labo.kaji.relativepopupwindow.RelativePopupWindow.HorizontalPosition;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.VerticalPosition;

import com.labo.kaji.relativepopupwindow.example.R;

public class ExampleActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        final Spinner spinner_vertical = (Spinner) findViewById(R.id.spinner_vertical);
        ArrayAdapter<String> adapterVertical = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterVertical.addAll(getResources().getStringArray(R.array.vertical_positions));
        adapterVertical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vertical.setAdapter(adapterVertical);

        final Spinner spinner_horizontal = (Spinner) findViewById(R.id.spinner_horizontal);
        ArrayAdapter<String> adapterHorizonal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterHorizonal.addAll(getResources().getStringArray(R.array.horizontal_positions));
        adapterHorizonal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_horizontal.setAdapter(adapterHorizonal);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int vertPos;
                switch (spinner_vertical.getSelectedItemPosition()) {
                    case 0:
                        vertPos = VerticalPosition.ABOVE;
                        break;
                    case 1:
                        vertPos = VerticalPosition.ALIGN_BOTTOM;
                        break;
                    case 2:
                        vertPos = VerticalPosition.CENTER;
                        break;
                    case 3:
                        vertPos = VerticalPosition.ALIGN_TOP;
                        break;
                    default:
                        vertPos = VerticalPosition.BELOW;
                        break;
                }

                final int horizPos;
                switch (spinner_horizontal.getSelectedItemPosition()) {
                    case 0:
                        horizPos = HorizontalPosition.LEFT;
                        break;
                    case 1:
                        horizPos = HorizontalPosition.ALIGN_RIGHT;
                        break;
                    case 2:
                        horizPos = HorizontalPosition.CENTER;
                        break;
                    case 3:
                        horizPos = HorizontalPosition.ALIGN_LEFT;
                        break;
                    default:
                        horizPos = HorizontalPosition.RIGHT;
                        break;
                }

                new ExampleCardPopup(view.getContext()).showOnAnchor(view, vertPos, horizPos);
            }
        });
    }

}
