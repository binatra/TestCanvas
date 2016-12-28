package com.minci.testcanvas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        float[] values = new float[]{2.0f, 1.5f, 2.5f, 1.0f, 3.0f,10f,12.5f};
        String[] vertical_labels = {"40","35","30","25","20","15","10","5","0"};
        String[] horizontal_labels = {"Janar", "Shkurt", "Mars", "Prill", "Maj","Qershor","Korrik","Gusht","Shtator","Tetor","Nentor","Dhjetor"};
        GraphView graphView = new GraphView(this, values, "GraphView", horizontal_labels, vertical_labels, GraphView.LINE);
        setContentView(graphView);

    }
}
