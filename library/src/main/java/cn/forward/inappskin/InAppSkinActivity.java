package cn.forward.inappskin;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.LayoutInflaterCompat;

public abstract class InAppSkinActivity extends FragmentActivity {

    private InAppSkinInflaterFactory mSkinInflaterFactory;
    private SkinManager mSkinManager;
    private int mThemeId;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSkinManager = new SkinManager(this);
        mSkinInflaterFactory = new InAppSkinInflaterFactory(mSkinManager);
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), mSkinInflaterFactory);
        mSkinManager.setThemeId(mThemeId);
    }

    @Override
    public void setContentView(int layoutResID) { // ？在某些机型上会出现直接调用setContentView(resId)时不会触发InflaterFactory
        setContentView(getLayoutInflater().inflate(layoutResID, null));
    }

    @Override
    public void setTheme(int resid) {
        if (mThemeId == resid) {
            return;
        }

        super.setTheme(resid);
        mThemeId = resid;
        if (mSkinManager != null) {
            mSkinManager.setThemeId(mThemeId);
        }
    }
}