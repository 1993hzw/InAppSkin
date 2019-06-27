package cn.forward.inappskin.entity;

import android.graphics.drawable.Drawable;
import android.view.View;

import cn.forward.inappskin.SkinManager;

public class BackgroundAttr extends SkinAttr {

    @Override
    public void apply(View view, SkinManager skinManager) {
        Drawable drawable = skinManager.getDrawable(attrValueRefId);
        view.setBackgroundDrawable(drawable);
    }
}
