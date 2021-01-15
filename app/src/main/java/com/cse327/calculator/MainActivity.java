package com.cse327.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    double crd_add = 0;
    double total_add = 0;
    Button addButton, refrButton;
    EditText edt1, edt2;
    ListView itemsListView;
    TextView cgpa_text;
    ArrayList<Items> list = new ArrayList<>();
    NumberFormat format;
    LinearLayout rootLayout;
    LinearLayout probation;
    LinearLayout validnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refrButton = findViewById(R.id.refresh_btn_id);
        addButton = findViewById(R.id.add_id);
        edt1 = findViewById(R.id.credit_id);
        edt2 = findViewById(R.id.gpa_id);
        cgpa_text = findViewById(R.id.total_id);
        itemsListView = findViewById(R.id.listview_id);
        probation = findViewById(R.id.probation_status);
        validnum = findViewById(R.id.validnum_status);
        rootLayout = findViewById(R.id.rootlayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mCredit = edt1.getText().toString();
                String mGPA = edt2.getText().toString();

                if (mCredit.matches("") || mGPA.matches("")) {
                    Snackbar.make(rootLayout, "Enter Credit and GPA", Snackbar.LENGTH_SHORT).show();
                } else {
                    double credit = Double.parseDouble(mCredit);
                    double gpa = Double.parseDouble(mGPA);


                    if(mGPA.isEmpty() && mCredit.isEmpty()) {
                        validnum.setVisibility(View.VISIBLE);
                        return;
                    } else {
                        validnum.setVisibility(View.GONE);
                    }

                    double total = credit * gpa;

                    if (gpa == 4.00) {
                        String GRADE = "A";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 3.7) {
                        String GRADE = "A-";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 3.3) {
                        String GRADE = "B+";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 3.0) {
                        String GRADE = "B";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 2.7) {
                        String GRADE = "B-";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 2.3) {
                        String GRADE = "C+";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 2.0) {
                        String GRADE = "C";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 1.7) {
                        String GRADE = "C-";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 1.3) {
                        String GRADE = "D+";
                        Items item = new Items(mCredit, mGPA, GRADE);

                        list.add(item);
                    } else if (gpa == 1.00) {
                        String GRADE = "D";
                        Items item = new Items(mCredit, mGPA, GRADE);
                        list.add(item);
                    } else  if (gpa == 0.00){
                        String GRADE = "F";
                        Items item = new Items(mCredit, mGPA, GRADE);
                        list.add(item);
                    }

                    crd_add += credit;
                    total_add += total;


                    format = new DecimalFormat("#.##");

                    Double avg = total_add / crd_add;
                    String cv = format.format(avg);
                    String avgs = Double.toString(Double.parseDouble(cv));

                    if(avg < 2.0) {
                        probation.setVisibility(View.VISIBLE);
                    } else {
                        probation.setVisibility(View.GONE);
                    }

                    if(avg > 4.0) {
                        validnum.setVisibility(View.VISIBLE);
                    } else {
                        validnum.setVisibility(View.GONE);
                    }

                    final CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, list);

                    refrButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            edt1.setText("");
                            edt2.setText("");
                            cgpa_text.setText("");
                            list.clear();
                            adapter.notifyDataSetChanged();
                        }
                    });
                    itemsListView.setAdapter(adapter);

                    cgpa_text.setText(avgs);
                    edt2.setText("");
                }
            }
        });
    }

}