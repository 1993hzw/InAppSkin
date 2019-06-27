package cn.forward.inappskin.entity;

import android.view.View;
import android.widget.TextView;

import cn.forward.inappskin.SkinManager;

public class TextColorAttr extends SkinAttr {

    @Override
    public void apply(View view, SkinManager skinManager) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setTextColor(skinManager.convertToColorStateList(attrValueRefId));
        }
    }
}
