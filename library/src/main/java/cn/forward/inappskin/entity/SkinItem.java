package cn.forward.inappskin.entity;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.forward.inappskin.SkinManager;

public class SkinItem {

    public View view;

    public List<SkinAttr> attrs;

    public SkinItem() {
        attrs = new ArrayList<SkinAttr>();
    }

    public void apply(SkinManager manager) {
        if (attrs == null || attrs.size() == 0) {
            return;
        }
        for (SkinAttr at : attrs) {
            at.apply(view, manager);
        }
    }

    public void clean() {
        if (attrs == null || attrs.size() == 0) {
            return;
        }
        for (SkinAttr at : attrs) {
            at = null;
        }
    }

    @Override
    public String toString() {
        return "SkinItem [view=" + view.getClass().getSimpleName() + ", attrs=" + attrs + "]";
    }
}
