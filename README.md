# InAppSkin
一种应用内换肤方案，利用安卓系统的`Style+Theme`机制进行应用内换肤。

1. 首先需要定义支持换肤的属性：

```xml

<attr name="skin_color" format="color|reference" />
<attr name="skin_drawable" format="color|reference" />

```

2. 声明多个style, 每个style代表一个换肤风格

```xml
<style name="BaseTheme" parent="AppTheme">
  <item name="skin_color">#FF0000</item>
  <item name="skin_drawable">@drawable/ic_profile_music</item>
</style>

<style name="SkinTheme1" parent="BaseTheme">
  <item name="skin_color">@color/colorPrimaryDark</item>
  <item name="skin_drawable">@drawable/ic_profile_nick_name</item>
</style>

<style name="SkinTheme2" parent="BaseTheme">
  <item name="skin_color">#00FF00</item>
  <item name="skin_drawable">@drawable/ic_profile_profile</item>
</style>
```

3. 在xml布局里面通过`?attr/name`的形式引用定义好的属性

```xml
<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="?attr/skin_color"
        android:padding="10dp"
        android:text="Hello World!" />
```



3. 代码里面通过设置Theme选择不同的style

```java

InAppSkinActivity.setTheme(R.style.BaseTheme);
InAppSkinActivity.setTheme(R.style.SkinTheme1);
InAppSkinActivity.setTheme(R.style.SkinTheme2);

```



## 原理

通过监听布局文件中View的创建过程，获取`?attr/name`定义的属性，在切换主题样式的时候更新该属性。

关键代码可以查看`InAppSkinInflaterFactory`中的方法：

```java
private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        List<SkinAttr> viewAttrs = new ArrayList<SkinAttr>();

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);

            if (!AttrFactory.isSupportedAttr(attrName)) {
                continue;
            }

            if (attrValue.startsWith("?")) { // ?attr/name
                try {
                    int id = Integer.parseInt(attrValue.substring(1));
                    String entryName = context.getResources().getResourceEntryName(id);
                    String typeName = context.getResources().getResourceTypeName(id);
                    SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
                    if (mSkinAttr != null) {
                        viewAttrs.add(mSkinAttr);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!isEmpty(viewAttrs)) {
            SkinItem skinItem = new SkinItem();
            skinItem.view = view;
            skinItem.attrs = viewAttrs;

            mSkinItems.add(skinItem);

            skinItem.apply(mSkinManager);
        }
}
```

