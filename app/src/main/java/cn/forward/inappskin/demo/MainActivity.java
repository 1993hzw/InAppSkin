package cn.forward.inappskin.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RadioGroup;

import cn.forward.inappskin.InAppSkinActivity;

public class MainActivity extends InAppSkinActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.img_type);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LayoutInflater layoutInflater = getLayoutInflater();
                LayoutInflater.Factory factory  = layoutInflater.getFactory();
                if (checkedId == R.id.theme1) {
                    setTheme(R.style.BaseTheme);
                } else if (checkedId == R.id.theme2) {
                    setTheme(R.style.SkinTheme1);
                } else if (checkedId == R.id.theme3) {
                    setTheme(R.style.SkinTheme2);
                }
            }
        });
        radioGroup.check(R.id.theme1);
    }
}
