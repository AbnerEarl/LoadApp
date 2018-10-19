package com.frank.ycj520.develop;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        that.findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(that,"点击事件成功！",Toast.LENGTH_LONG).show();
            }
        });
    }

}
