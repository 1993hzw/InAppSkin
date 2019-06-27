package cn.forward.inappskin.entity;

import android.view.View;
import android.widget.ListView;

import cn.forward.inappskin.SkinManager;

public class DividerAttr extends SkinAttr {

    @Override
    public void apply(View view, SkinManager skinManager) {
        if (view instanceof ListView) {
            ListView tv = (ListView) view;
            tv.setDivider(skinManager.getDrawable(attrValueRefId));
        }
    }
}
