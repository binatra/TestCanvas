package com.minci.testcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Minci on 12/23/2016.
 */

public class GraphView extends View {

    public static boolean BAR = true;
    public static boolean LINE = false;

    private Paint paint;
    private float[] values;
    private String[] horizontal_labels;
    private String[] vertical_labels;
    private String title;
    private boolean type;

    public GraphView(Context context, float[] values, String title, String[] horizontal_labels, String[] vertical_labels, boolean type) {
        super(context);
        if (values == null)
            values = new float[0];
        else
            this.values = values;
        if (title == null)
            title = "";
        else
            this.title = title;
        if (horizontal_labels == null)
            this.horizontal_labels = new String[0];
        else
            this.horizontal_labels = horizontal_labels;
        if (vertical_labels == null)
            this.vertical_labels = new String[0];
        else
            this.vertical_labels = vertical_labels;
        this.type = type;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float border = 20;
        float horstart = border * 2;
        float height = getHeight();
        float width = getWidth() - 1;
        float max = getMax();
        float min = getMin();
        float diff = max - min;
        float graphheight = height - (2 * border);
        float graphwidth = width - (2 * border);

        paint.setTextAlign(Paint.Align.LEFT);
        int vers = vertical_labels.length - 1;
        for (int i = 0; i < vertical_labels.length; i++) {
            paint.setColor(Color.BLACK);
            float y = ((graphheight / vers) * i)+border;
            canvas.drawLine(horstart, y, width, y, paint);
            paint.setTextSize(30f);
            canvas.drawText(vertical_labels[i], 0, y, paint);
        }
        int hors = horizontal_labels.length - 1;
        for (int i = 0; i < horizontal_labels.length; i++) {
            paint.setColor(Color.BLACK);
            float x = ((graphwidth / hors) * i) + horstart;
            canvas.drawLine(x, height - border, x, border, paint);
            paint.setTextAlign(Paint.Align.CENTER);
            if (i== horizontal_labels.length-1)
                paint.setTextAlign(Paint.Align.RIGHT);
            if (i==0)
                paint.setTextAlign(Paint.Align.LEFT);
            paint.setTextSize(25f);
            canvas.drawText(horizontal_labels[i], x, height - 4, paint);
        }

        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(title, (graphwidth / 2) + horstart, border - 4, paint);

        if (max != min) {
            paint.setColor(Color.LTGRAY);
            if (type == BAR) {
                float datalength = values.length;
                float column_width = (width - (2 * border)) / datalength;
                for (int i = 0; i < values.length; i++) {
                    float val = values[i] - min;
                    float rat = val / diff;
                    float h = graphheight * rat;
                    canvas.drawRect((i * column_width) + horstart, (border - h) + graphheight, ((i * column_width) + horstart) + (column_width - 1), height - (border - 1), paint);
                }
            } else {
                float datalength = values.length;
                float column_width = (width - (2 * border)) / datalength;
                float half_column = column_width / 2;
                float last_height = 0;
                for (int i = 0; i < values.length; i++) {
                    float val = values[i] - min;
                    float rat = val / diff;
                    float h = graphheight * rat;
                    if (i > 0)
                        canvas.drawLine(((i - 1) * column_width) + (horstart + 1) + half_column, (border - last_height) + graphheight, (i * column_width) + (horstart + 1) + half_column, (border - h) + graphheight, paint);
                    last_height = h;
                }
            }
        }
    }

    private float getMax() {
        float largest = Integer.MIN_VALUE;
        for (int i = 0; i < values.length; i++)
            if (values[i] > largest)
                largest = values[i];
        return largest;
    }

    private float getMin() {
        float smallest = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; i++)
            if (values[i] < smallest)
                smallest = values[i];
        return smallest;
    }

}