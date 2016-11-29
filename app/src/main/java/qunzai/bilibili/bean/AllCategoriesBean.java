package qunzai.bilibili.bean;

import java.util.List;

/**
 * Created by XingMingDa on 16/11/28.
 * AllCategoriesBean
 */

public class AllCategoriesBean {

    /**
     * code : 0
     * message : ok
     * data : [{"id":11,"name":"手机直播","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/11.png?2016112801","height":80,"width":80}},{"id":12,"name":"手游直播","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/12.png?2016112801","height":80,"width":80}},{"id":8,"name":"萌宅推荐","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/8.png?2016112801","height":80,"width":80}},{"id":9,"name":"绘画专区","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/9.png?2016112801","height":80,"width":80}},{"id":3,"name":"网络游戏","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/3.png?2016112801","height":80,"width":80}},{"id":1,"name":"单机联机","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/1.png?2016112801","height":80,"width":80}},{"id":4,"name":"电子竞技","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/4.png?2016112801","height":80,"width":80}},{"id":10,"name":"唱见舞见","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/10.png?2016112801","height":80,"width":80}},{"id":6,"name":"生活娱乐","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/6.png?2016112801","height":80,"width":80}},{"id":2,"name":"御宅文化","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/2.png?2016112801","height":80,"width":80}},{"id":7,"name":"放映厅","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/7.png?2016112801","height":80,"width":80}},{"id":99,"name":"精彩轮播","entrance_icon":{"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/99.png?2016112801","height":80,"width":80}}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 11
         * name : 手机直播
         * entrance_icon : {"src":"http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/11.png?2016112801","height":80,"width":80}
         */

        private int id;
        private String name;
        private EntranceIconBean entrance_icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public EntranceIconBean getEntrance_icon() {
            return entrance_icon;
        }

        public void setEntrance_icon(EntranceIconBean entrance_icon) {
            this.entrance_icon = entrance_icon;
        }

        public static class EntranceIconBean {
            /**
             * src : http://static.hdslb.com/live-static/live-app/areaicon/android/xhdpi/11.png?2016112801
             * height : 80
             * width : 80
             */

            private String src;
            private int height;
            private int width;

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }
}
