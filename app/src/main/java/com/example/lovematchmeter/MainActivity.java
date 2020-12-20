package com.example.lovematchmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button bt_compute;
    ImageView iv_needle, iv_gauge;
    TextView tv_yourName, tv_theirName;
    EditText et_yourName, et_theirName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_compute = (Button)findViewById(R.id.bt_compute);
        iv_needle = (ImageView)findViewById(R.id.iv_needle);
        iv_gauge = (ImageView)findViewById(R.id.iv_gauge);
        tv_yourName = (TextView)findViewById(R.id.tv_yourName);
        tv_theirName = (TextView)findViewById(R.id.tv_theirName);
        et_yourName = (EditText)findViewById(R.id.et_yourName);
        et_theirName = (EditText)findViewById(R.id.et_theirName);

        bt_compute.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String yourName = et_yourName.getText().toString().toLowerCase();
                String theirName = et_theirName.getText().toString().toLowerCase();
                int totalLetters = yourName.length() + theirName.length();
                int totalMatches = 0;

                for (int i = 0; i < yourName.length(); i++)
                {
                    for (int j = 0; j < theirName.length(); j++)
                    {
                        if (yourName.charAt(i) == theirName.charAt(j))
                        {
                            totalMatches++;
                        }
                    }
                }

                for (int i = 0; i < theirName.length(); i++)
                {
                    for (int j = 0; j < yourName.length(); j++)
                    {
                        if (theirName.charAt(i) == yourName.charAt(j))
                        {
                            totalMatches++;
                        }
                    }
                }

                float compScore = (float) totalMatches / totalLetters;
                int loveScore  = ((int) compScore * 100) - 50;

                RotateAnimation ra = new RotateAnimation(0, 360 + loveScore, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setFillAfter(true);
                ra.setDuration(2000);
                ra.setInterpolator(new AccelerateDecelerateInterpolator());
                iv_needle.startAnimation(ra);

                Toast.makeText(MainActivity.this, "Love Score of " + compScore, Toast.LENGTH_SHORT ).show();

            }
        });

    }
}