package com.example.user.timer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnpause,btnstart;
    EditText editText1, editText2;
    TextView tvtime1,tvtime2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart=(Button)findViewById(R.id.btnstart);
        btnpause = (Button) findViewById(R.id.btnpause);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        tvtime1=(TextView)findViewById(R.id.tvtime1);
        tvtime2=(TextView)findViewById(R.id.tvtime2);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t1 = Integer.parseInt(editText1.getText().toString());
                int t2 = Integer.parseInt(editText2.getText().toString());
                CountTask count=new CountTask();;
                count.execute(t1,t2);

            }

        });
    }
    class  CountTask extends AsyncTask<Integer,Integer,Void> {

        protected Void doInBackground(Integer... params) {
            int n = params[0];
            int m = params[1];


            for (int i = n; i >=0 ; i--) {
                for (int j = m; j >= 0; j--) {

                    if((i==0)&&(j==1))
                    {
                        wait1Sec();
                        publishProgress(i, j);
                        break;
                    }
                    if (j == 0) {
                        m = 59;
                    }

                    wait1Sec();
                    publishProgress(i, j);


                }
            }
            return  null;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(values[0]==0 && values[1]==1)
            {
                tvtime1.setText("TIME");
                tvtime2.setText("UP....");

            }

            //tvtime1.setText(String.valueOf(values[0]));
            //tvtime2.setText(String.valueOf(values[1]));

        }
    }

        void wait1Sec () {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() < startTime + 1000);

        }


    }

