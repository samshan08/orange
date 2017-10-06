package org.sam.fortuneteller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newFortuneActivity(View view)
    {
        setContentView(R.layout.activity_create);
    }

    public void loadFortuneActivity(View view)
    {

    }

    public void exitFortuneActivity(View view)
    {

    }
}
