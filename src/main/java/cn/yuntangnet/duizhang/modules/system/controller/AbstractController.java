package cn.yuntangnet.duizhang.modules.system.controller;

import cn.yuntangnet.duizhang.modules.system.entity.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author 茂林
 * @since 2017/11/22 18:36
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SystemUser getUser() {
        return (SystemUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    public class PageInfo {
        private int current;
        private int size;

        public PageInfo(Map<String, Object> params) {
            init(params);
        }

        private void init(Map<String, Object> params) {
            int page = 1;
            int rows = 10;
            if (params != null) {
                if (params.get("page") != null && params.get("rows") != null) {
                    int input_page = Integer.parseInt((String) params.get("page"));
                    if (input_page >= 1) {
                        page = input_page;
                    }
                    int input_rows = Integer.parseInt((String) params.get("rows"));
                    if (input_rows >= 10 && input_rows <= 100) {
                        rows = input_rows;
                    }
                }
            }
            this.current = (page - 1) * rows;
            this.size = rows;
        }

        public int getCurrent() {
            return current;
        }

        public int getSize() {
            return size;
        }
    }
}
