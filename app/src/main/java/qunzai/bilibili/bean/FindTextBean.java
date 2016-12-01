package qunzai.bilibili.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by QunZai on 16/11/29.
 */
public class FindTextBean {


    /**
     * code : 0
     * seid : 11544902399613803861
     * message : success
     * timestamp : 1479060703
     * list : [{"keyword":"法医秦明","status":"keep"},{"keyword":"吃货木下","status":"up"},{"keyword":"暴走大事件","status":"down"},{"keyword":"极乐净土","status":"keep"},{"keyword":"哔哩哔哩篮球队","status":"down"},{"keyword":"校对女孩河野悦子","status":"keep"},{"keyword":"心里的声音","status":"up"},{"keyword":"主播真会玩","status":"keep"},{"keyword":"阴阳师","status":"down"},{"keyword":"一年生","status":"up"},{"keyword":"大胃王密子君","status":"keep"},{"keyword":"如果蜗牛有爱情","status":"down"},{"keyword":"董力阿拉蕾","status":"up"},{"keyword":"谷阿莫","status":"up"},{"keyword":"你的名字","status":"down"},{"keyword":"海贼王","status":"up"},{"keyword":"守望先锋","status":"down"},{"keyword":"敖厂长","status":"down"},{"keyword":"蜡笔小新","status":"down"},{"keyword":"锦绣未央","status":"up"},{"keyword":"夏目友人帐","status":"keep"},{"keyword":"主播炸了","status":"keep"},{"keyword":"安吉","status":"up"},{"keyword":"ppap","status":"down"},{"keyword":"snh48","status":"down"},{"keyword":"日剧","status":"up"},{"keyword":"逗鱼时刻","status":"down"},{"keyword":"徐老师来巡山","status":"down"},{"keyword":"起小点","status":"down"},{"keyword":"深夜食堂","status":"up"},{"keyword":"火影忍者","status":"down"},{"keyword":"blackpink","status":"down"},{"keyword":"请叫我英雄","status":"down"},{"keyword":"阿拉蕾","status":"up"},{"keyword":"冰上的尤里","status":"down"},{"keyword":"亚人","status":"down"},{"keyword":"董力","status":"up"},{"keyword":"砂之塔","status":"down"},{"keyword":"不可抗力","status":"up"},{"keyword":"twice","status":"up"},{"keyword":"灵契","status":"down"},{"keyword":"张继科","status":"up"},{"keyword":"杨洋","status":"up"},{"keyword":"川普","status":"down"},{"keyword":"抗韩中年人","status":"up"},{"keyword":"齐木楠雄的灾难","status":"down"},{"keyword":"pdd","status":"up"},{"keyword":"攻壳机动队","status":"up"},{"keyword":"美人为馅","status":"up"},{"keyword":"黑镜","status":"keep"}]
     * cost : {"timer":"hotword","total":"0.000273","read file":"0.000162"}
     */

    private int code;
    private String seid;
    private String message;
    private int timestamp;
    private CostBean cost;
    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSeid() {
        return seid;
    }

    public void setSeid(String seid) {
        this.seid = seid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public CostBean getCost() {
        return cost;
    }

    public void setCost(CostBean cost) {
        this.cost = cost;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class CostBean {
        /**
         * timer : hotword
         * total : 0.000273
         * read file : 0.000162
         */

        private String timer;
        private String total;
        @SerializedName("read file")
        private String readFile;

        public String getTimer() {
            return timer;
        }

        public void setTimer(String timer) {
            this.timer = timer;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getReadFile() {
            return readFile;
        }

        public void setReadFile(String readFile) {
            this.readFile = readFile;
        }
    }

    public static class ListBean {
        /**
         * keyword : 法医秦明
         * status : keep
         */

        private String keyword;
        private String status;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
