package com.example.rok.hw13;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    TextView t1;
    ImageView i1;
    Mytasks mytasks;
    int value = 0;
    int a;
    int j = 0;
    int k;
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            for(int i = 0;i<5;i++){
//                i1.setImageResource(picture[i]);
//                if(i ==4){
//                    i = 0;
//                }
//            }
//
//        }
//    };
    int[] picture = {R.drawable.carrot,R.drawable.grape,R.drawable.strawberry,R.drawable.watermelon,R.drawable.pineapple};
    String[] name = {"당근","포도","딸기","수박","파인애플"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        t1 = (TextView) findViewById(R.id.textView5);
        i1 = (ImageView) findViewById(R.id.imageView);





    }

    public void onmyclick(View view) {

        switch (view.getId()) {

            case R.id.imageView:
                a = Integer.parseInt(e1.getText().toString());
                if(value == 0){
                    mytasks = new Mytasks();
                    mytasks.execute();
                    value  = 1;

//                    Mythread th = new Mythread();
//                    th.start();
                }
                else{
                    mytasks.cancel(true);
                    value = 0;
                    if(j ==-1){
                        t1.setText(name[name.length]+"선택("+k+")초");
                    }
                    else{
                        t1.setText(name[j-1]+"선택("+k+")초");

                    }

                }
                t1.setVisibility(View.VISIBLE);



                break;
            case R.id.button:
                mytasks.cancel(true);
                t1.setVisibility(View.INVISIBLE);
                i1.setImageResource(R.mipmap.ic_launcher);

                break;
        }

    }




    class Mytasks extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            for(int i =0;i<10000;i++) {
                if (isCancelled() == true) {
                    return null;
                }

                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            t1.setText("처음부터 " + values[0]);
            t1.setTextColor(Color.RED);
            k = values[0];
            for(int i = 0;i<9999;i++) {


                if (a * i == values[0]) {
                    i1.setImageResource(picture[j]);
                    j++;
                    if (j == 4) {
                        j = 0;
                    }
                }
            }



        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
