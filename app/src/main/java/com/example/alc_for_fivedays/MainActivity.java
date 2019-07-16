package com.example.alc_for_fivedays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static Button button_show;
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View button = findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
            roll();
            }
        });

        View button1 = findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showAbout();
            }
        });
    }
    private void showAbout(){
        Intent intent = new Intent(this, About_alc.class);
        startActivity(intent);
    }
    private void roll(){
    Intent intent = new Intent(this, Main2Activity.class);
    startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(), "double click to exit", Toast.LENGTH_SHORT );
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
//    public void openAboutActivity(View view){
//        String url = "https://andela.com/alc/";
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//        CustomTabsIntent customTabsIntent = builder.build();
//        customTabsIntent.launchUrl(this, Uri.parse(url));
//    }


}
