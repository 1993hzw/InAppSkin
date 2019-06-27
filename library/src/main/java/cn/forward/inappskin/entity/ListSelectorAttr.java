package cn.forward.inappskin.entity;

import android.view.View;
import android.widget.AbsListView;

import cn.forward.inappskin.SkinManager;

public class ListSelectorAttr extends SkinAttr {

    @Override
    public void apply(View view, SkinManager skinManager) {
        if (view instanceof AbsListView) {
            AbsListView tv = (AbsListView) view;
            tv.setSelector(skinManager.getDrawable(attrValueRefId));
        }
    }
}
