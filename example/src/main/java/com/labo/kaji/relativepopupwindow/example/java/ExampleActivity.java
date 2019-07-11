package com.labo.kaji.relativepopupwindow.example.java;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.labo.kaji.relativepopupwindow.RelativePopupWindow.HorizontalPosition;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow.VerticalPosition;

import com.labo.kaji.relativepopupwindow.example.R;

public class ExampleActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        final Spinner spinner_vertical = findViewById(R.id.spinner_vertical);
        ArrayAdapter<String> adapterVertical = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterVertical.addAll(getResources().getStringArray(R.array.vertical_positions));
        adapterVertical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vertical.setAdapter(adapterVertical);

        final Spinner spinner_horizontal = findViewById(R.id.spinner_horizontal);
        ArrayAdapter<String> adapterHorizonal = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterHorizonal.addAll(getResources().getStringArray(R.array.horizontal_positions));
        adapterHorizonal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_horizontal.setAdapter(adapterHorizonal);

        final Spinner spinner_width = findViewById(R.id.spinner_width);
        ArrayAdapter<String> adapterWidth = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterWidth.addAll(getResources().getStringArray(R.array.width));
        adapterWidth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_width.setAdapter(adapterWidth);

        final Spinner spinner_height = findViewById(R.id.spinner_height);
        ArrayAdapter<String> adapterHeight = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapterHeight.addAll(getResources().getStringArray(R.array.height));
        adapterHeight.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_height.setAdapter(adapterHeight);

        final CheckBox checkbox_fit_in_screen = findViewById(R.id.checkbox_fit_in_screen);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleCardPopup popup = new ExampleCardPopup(view.getContext());
                switch (spinner_width.getSelectedItemPosition()) {
                    case 0:
                        popup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                        break;
                    case 1:
                        popup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                        break;
                    case 2:
                        popup.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()));
                        break;
                    case 3:
                        popup.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics()));
                        break;
                    case 4:
                        popup.setWidth((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480, getResources().getDisplayMetrics()));
                        break;
                    default:
                        throw new IllegalStateException();
                }
                switch (spinner_width.getSelectedItemPosition()) {
                    case 0:
                        popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                        break;
                    case 1:
                        popup.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                        break;
                    case 2:
                        popup.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics()));
                        break;
                    case 3:
                        popup.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 240, getResources().getDisplayMetrics()));
                        break;
                    case 4:
                        popup.setHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 480, getResources().getDisplayMetrics()));
                        break;
                    default:
                        throw new IllegalStateException();
                }
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
                    case 4:
                        vertPos = VerticalPosition.BELOW;
                        break;
                    default:
                        throw new IllegalStateException();
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
                    case 4:
                        horizPos = HorizontalPosition.RIGHT;
                        break;
                    default:
                        throw new IllegalStateException();
                }
                final boolean fitInScreen = checkbox_fit_in_screen.isChecked();
                popup.showOnAnchor(view, vertPos, horizPos, fitInScreen);
            }
        });
    }

}
