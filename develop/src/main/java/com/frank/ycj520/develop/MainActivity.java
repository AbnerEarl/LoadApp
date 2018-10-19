package com.frank.ycj520.develop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        that.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(that,"点击事件成功！",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(that,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

}
