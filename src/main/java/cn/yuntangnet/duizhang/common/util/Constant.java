package cn.yuntangnet.duizhang.common.util;

/**
 * 系统常量
 *
 * @author 茂林
 * @since 2017/11/28 16:51
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 用户等级
     */
    public enum UserLevel {
        /**
         * 普通用户
         */
        PUTONG("1"),
        /**
         * 代理
         */
        DAILI("2");

        private String value;

        UserLevel(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 用户来源
     */
    public enum UserFrom {
        HOUTAI("1"),
        WEIXIN("2"),
        XIAOCHENGXU("3");

        private String value;

        UserFrom(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 数据状态
     */
    public enum DataStatus {
        /**
         * 正常
         */
        ENABLED(1),
        /**
         * 暂停
         */
        DISABLED(0);

        private int value;

        DataStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
