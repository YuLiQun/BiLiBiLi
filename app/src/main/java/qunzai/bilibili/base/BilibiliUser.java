package qunzai.bilibili.base;

import cn.bmob.v3.BmobUser;

/**
 * Created by QunZai on 16/12/2.
 */

public class BilibiliUser extends BmobUser{

    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
