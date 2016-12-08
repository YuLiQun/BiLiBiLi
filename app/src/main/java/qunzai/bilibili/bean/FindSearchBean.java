package qunzai.bilibili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by QunZai on 16/12/7.
 */

public class FindSearchBean {


    /**
     * accurate : {"upuser":{"0":{"name":"长生hh","value":"长生hh","mid":32755092,"url":"http://space.bilibili.com/32755092","s_pic":"http://i0.hdslb.com/bfs/face/763cadf485191c13c43b4a4e4a78242dba7a68f6.jpg"}}}
     * tag : {"0":{"name":"校对女孩河野悦子","value":"校对女孩河野悦子","spid":0,"ref":51},"1":{"name":"绘画","value":"绘画","spid":3368,"ref":18850},"2":{"name":"HH天团","value":"HH天团","spid":0,"ref":1},"3":{"name":"HH大丧失","value":"HH大丧失","spid":0,"ref":1},"4":{"name":"HH原班人马","value":"HH原班人马","spid":0,"ref":1},"5":{"name":"哈哈哈哈哈哈哈哈","value":"哈哈哈哈哈哈哈哈","spid":0,"ref":315},"6":{"name":"红红火火恍恍惚惚","value":"红红火火恍恍惚惚","spid":0,"ref":72},"7":{"name":"恍恍惚惚红红火火","value":"恍恍惚惚红红火火","spid":0,"ref":18},"8":{"name":"哈哈哈哈哈哈哈哼","value":"哈哈哈哈哈哈哈哼","spid":0,"ref":11},"9":{"name":"火火火火火火火火","value":"火火火火火火火火","spid":0,"ref":7}}
     */

    private AccurateBean accurate;
    private TagBean tag;

    public AccurateBean getAccurate() {
        return accurate;
    }

    public void setAccurate(AccurateBean accurate) {
        this.accurate = accurate;
    }

    public TagBean getTag() {
        return tag;
    }

    public void setTag(TagBean tag) {
        this.tag = tag;
    }


    private ArrayList<TagBean> mArrayList ;

    public ArrayList<TagBean> getArrayList() {
        return mArrayList;
    }

    public void setArrayList(ArrayList<TagBean> arrayList) {
        mArrayList = arrayList;
    }

    public static class AccurateBean {
        /**
         * upuser : {"0":{"name":"长生hh","value":"长生hh","mid":32755092,"url":"http://space.bilibili.com/32755092","s_pic":"http://i0.hdslb.com/bfs/face/763cadf485191c13c43b4a4e4a78242dba7a68f6.jpg"}}
         */

        private UpuserBean upuser;

        public UpuserBean getUpuser() {
            return upuser;
        }

        public void setUpuser(UpuserBean upuser) {
            this.upuser = upuser;
        }

        public static class UpuserBean {
            /**
             * 0 : {"name":"长生hh","value":"长生hh","mid":32755092,"url":"http://space.bilibili.com/32755092","s_pic":"http://i0.hdslb.com/bfs/face/763cadf485191c13c43b4a4e4a78242dba7a68f6.jpg"}
             */

            @SerializedName("0")
            private ZoroBean value0;

            public ZoroBean getValue0() {
                return value0;
            }

            public void setValue0(ZoroBean value0) {
                this.value0 = value0;
            }

            public static class ZoroBean {
                /**
                 * name : 长生hh
                 * value : 长生hh
                 * mid : 32755092
                 * url : http://space.bilibili.com/32755092
                 * s_pic : http://i0.hdslb.com/bfs/face/763cadf485191c13c43b4a4e4a78242dba7a68f6.jpg
                 */

                private String name;
                private String value;
                private int mid;
                private String url;
                private String s_pic;

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

                public int getMid() {
                    return mid;
                }

                public void setMid(int mid) {
                    this.mid = mid;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getS_pic() {
                    return s_pic;
                }

                public void setS_pic(String s_pic) {
                    this.s_pic = s_pic;
                }
            }
        }
    }

    public static class TagBean {
        /**
         * 0 : {"name":"校对女孩河野悦子","value":"校对女孩河野悦子","spid":0,"ref":51}
         * 1 : {"name":"绘画","value":"绘画","spid":3368,"ref":18850}
         * 2 : {"name":"HH天团","value":"HH天团","spid":0,"ref":1}
         * 3 : {"name":"HH大丧失","value":"HH大丧失","spid":0,"ref":1}
         * 4 : {"name":"HH原班人马","value":"HH原班人马","spid":0,"ref":1}
         * 5 : {"name":"哈哈哈哈哈哈哈哈","value":"哈哈哈哈哈哈哈哈","spid":0,"ref":315}
         * 6 : {"name":"红红火火恍恍惚惚","value":"红红火火恍恍惚惚","spid":0,"ref":72}
         * 7 : {"name":"恍恍惚惚红红火火","value":"恍恍惚惚红红火火","spid":0,"ref":18}
         * 8 : {"name":"哈哈哈哈哈哈哈哼","value":"哈哈哈哈哈哈哈哼","spid":0,"ref":11}
         * 9 : {"name":"火火火火火火火火","value":"火火火火火火火火","spid":0,"ref":7}
         */

        @SerializedName("0")
        private ZoroBeanX value0;
        @SerializedName("1")
        private OneBean value1;
        @SerializedName("2")
        private TwoBean value2;
        @SerializedName("3")
        private ThreeBean value3;
        @SerializedName("4")
        private FourBean value4;
        @SerializedName("5")
        private FiveBean value5;
        @SerializedName("6")
        private SixBean value6;
        @SerializedName("7")
        private SevenBean value7;
        @SerializedName("8")
        private EightBean value8;
        @SerializedName("9")
        private NineBean value9;

        public ZoroBeanX getValue() {
            return value0;
        }

        public void setValue(ZoroBeanX value0) {
            this.value0 = value0;
        }

        public OneBean getValue1() {
            return value1;
        }

        public void setValue1(OneBean value1) {
            this.value1 = value1;
        }

        public TwoBean getValue2() {
            return value2;
        }

        public void setValue2(TwoBean value2) {
            this.value2 = value2;
        }

        public ThreeBean getValue3() {
            return value3;
        }

        public void setValue3(ThreeBean value3) {
            this.value3 = value3;
        }

        public FourBean getValue4() {
            return value4;
        }

        public void setValue4(FourBean value4) {
            this.value4 = value4;
        }

        public FiveBean getValue5() {
            return value5;
        }

        public void setValue5(FiveBean value5) {
            this.value5 = value5;
        }

        public SixBean getValue6() {
            return value6;
        }

        public void setValue6(SixBean value6) {
            this.value6 = value6;
        }

        public SevenBean getValue7() {
            return value7;
        }

        public void setValue7(SevenBean value7) {
            this.value7 = value7;
        }

        public EightBean getValue8() {
            return value8;
        }

        public void setValue8(EightBean value8) {
            this.value8 = value8;
        }

        public NineBean getValue9() {
            return value9;
        }

        public void setValue9(NineBean value9) {
            this.value9 = value9;
        }

        public static class ZoroBeanX extends FindTagBean{

        }

        public static class OneBean extends FindTagBean{

        }

        public static class TwoBean extends FindTagBean{

        }

        public static class ThreeBean extends FindTagBean{

        }

        public static class FourBean extends FindTagBean{

        }

        public static class FiveBean extends FindTagBean{

        }

        public static class SixBean extends FindTagBean{

        }

        public static class SevenBean extends FindTagBean{

        }

        public static class EightBean extends FindTagBean{

        }

        public static class NineBean extends FindTagBean{

        }
    }
}
