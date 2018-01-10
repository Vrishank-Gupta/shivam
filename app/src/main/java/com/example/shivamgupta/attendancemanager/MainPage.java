package com.example.shivamgupta.attendancemanager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
      ListView lvList;
      ArrayList<subjects> subjectList = new ArrayList<>();
      attendanceAdapter attendanceAdapter = new attendanceAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        subjectList.add(new subjects("Hello",Float.valueOf(23)));

        Log.d("Out", "onCreate: ");

        ArrayList<String> here = null;
        if(getIntent().hasExtra("subjectname"))
            here = getIntent().getExtras().getStringArrayList("subjectname");
        if(here !=null)
        {
            String SubjectName =  here.get(0);
            String bunkedClasses = here.get(1);
            String totalClasses = here.get(2);
            int BunkedClasses = Integer.parseInt(bunkedClasses);
            int TotalClasses = Integer.parseInt(totalClasses);
            Float percentage = Float.valueOf((TotalClasses - BunkedClasses)/TotalClasses);
            subjectList.add(new subjects(SubjectName ,percentage));
            lvList.setAdapter(attendanceAdapter);
            here = null;
            Log.d("hola", "not null ");
        }
        else
        {
            Log.d("hola", "onCreate:null ");
        }


        Button btnAdd = findViewById(R.id.btnAdd);
        lvList = findViewById(R.id.lvItems);

        GraphView graph = findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
        lvList.setAdapter(attendanceAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainPage.this, SubjectDetails.class);
                startActivity(i);

            }
        });


        
    }

    class attendanceAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return subjectList.size();
        }

        @Override
        public subjects getItem(int i) {
            return subjectList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater li = getLayoutInflater();
            view = li.inflate(R.layout.list_item , viewGroup , false);

            TextView tvSubName = view.findViewById(R.id.tvsubName);
            TextView tvPercentage = view.findViewById(R.id.tvPercentage);

            subjects thisSubject = getItem(i);

            tvSubName.setText(thisSubject.getSubjectName());
            tvPercentage.setText(String.valueOf(thisSubject.getPercentage()));

            return view;

        }
    }
}

