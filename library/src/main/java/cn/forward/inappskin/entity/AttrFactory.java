package cn.forward.inappskin.entity;


public class AttrFactory {

    public static final String BACKGROUND = "background";
    public static final String TEXT_COLOR = "textColor";
    public static final String SRC = "src";
    public static final String LIST_SELECTOR = "listSelector";
    public static final String DIVIDER = "divider";

    public static SkinAttr get(String attrName, int attrValueRefId, String attrValueRefName, String typeName) {

        SkinAttr skinAttr = null;

        if (BACKGROUND.equals(attrName)) {
            skinAttr = new BackgroundAttr();
        } else if (TEXT_COLOR.equals(attrName)) {
            skinAttr = new TextColorAttr();
        } else if (SRC.equals(attrName)) {
            skinAttr = new ImgSrcAttr();
        } else if (LIST_SELECTOR.equals(attrName)) {
            skinAttr = new ListSelectorAttr();
        } else if (DIVIDER.equals(attrName)) {
            skinAttr = new DividerAttr();
        } else {
            return null;
        }

        skinAttr.attrName = attrName;
        skinAttr.attrValueRefId = attrValueRefId;
        skinAttr.attrValueRefName = attrValueRefName;
        skinAttr.attrValueTypeName = typeName;
        return skinAttr;
    }

    public static boolean isSupportedAttr(String attrName) {
        return (BACKGROUND.equals(attrName)
                || TEXT_COLOR.equals(attrName)
                || SRC.equals(attrName)
                || LIST_SELECTOR.equals(attrName)
                || DIVIDER.equals(attrName));
    }
}
