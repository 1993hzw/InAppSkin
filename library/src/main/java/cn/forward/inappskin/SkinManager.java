package cn.forward.inappskin;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

public class SkinManager {

    private int mThemeId = 0;
    private Activity mContext;

    private List<InAppSkinInflaterFactory> mSkinInflaterFactoryList = new ArrayList<>();

    public SkinManager(Activity activity) {
        mContext = activity;
    }

    public void setThemeId(int themeId) {
        if (themeId == mThemeId) {
            return;
        }

        mThemeId = themeId;
        mContext.setTheme(mThemeId);

        notifyThemeChanged();
    }

    public int getThemeId() {
        return mThemeId;
    }

    private void notifyThemeChanged() {
        for (InAppSkinInflaterFactory factory : mSkinInflaterFactoryList) {
            factory.applySkin(this);
        }
    }

    public void addSkinInflaterFactory(InAppSkinInflaterFactory factory) {
        if (mSkinInflaterFactoryList.contains(factory)) {
            return;
        }

        mSkinInflaterFactoryList.add(factory);
        factory.applySkin(this);
    }

    public int getColor(int id) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(id, typedValue, true);
        if (typedValue.type == TypedValue.TYPE_REFERENCE) {
            return getResources().getColor(typedValue.resourceId);
        } else {
            return typedValue.data;
        }
    }

    public Drawable getDrawable(int id) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(id, typedValue, true);
        if (typedValue.type == TypedValue.TYPE_REFERENCE) {
            return getResources().getDrawable(typedValue.resourceId);
        } else if (typedValue.type == TypedValue.TYPE_STRING || typedValue.resourceId != 0) {
            return getResources().getDrawable(typedValue.resourceId);
        } else {
            return new ColorDrawable(typedValue.data);
        }
    }

    public ColorStateList convertToColorStateList(int id) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getTheme();
        theme.resolveAttribute(id, typedValue, true);
        if (typedValue.type == TypedValue.TYPE_REFERENCE) {
            try {
                return getResources().getColorStateList(typedValue.resourceId);
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            }

            int[][] states = new int[1][1];
            return new ColorStateList(states, new int[]{getResources().getColor(id)});
        } else {
            int[][] states = new int[1][1];
            return new ColorStateList(states, new int[]{typedValue.data});
        }

    }

    private Resources getResources() {
        return mContext.getResources();
    }

    private Resources.Theme getTheme() {
        return mContext.getTheme();
    }

}
