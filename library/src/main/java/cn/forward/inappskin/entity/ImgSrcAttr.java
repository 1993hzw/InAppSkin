package cn.forward.inappskin.entity;

import android.view.View;
import android.widget.ImageView;

import cn.forward.inappskin.SkinManager;

public class ImgSrcAttr extends SkinAttr {

    @Override
    public void apply(View view, SkinManager skinManager) {
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(skinManager.getDrawable(attrValueRefId));
        }
    }
}
