package qunzai.bilibili.bean;

/**
 * Created by QunZai on 16/12/8.
 */

public class FindTagBean {

    protected String name;
    protected String value;
    protected int spid;
    protected int ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }
}
