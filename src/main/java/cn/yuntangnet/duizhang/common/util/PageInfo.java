package cn.yuntangnet.duizhang.common.util;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.Map;

/**
 * @author 茂林
 * @since 2017/11/30 11:50
 */
public class PageInfo<T> extends Page<T> {

    private PageInfo() {

    }

    public PageInfo(Map<String, Object> params) {
        int page = 1;
        int rows = 10;
        String orderField = null;
        boolean isAsc = true;
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
            if (params.get("sidx") != null) {
                orderField = (String) params.get("sidx");
            }
            if (params.get("order") != null) {
                String ordertype = (String) params.get("order");
                if ("asc".equalsIgnoreCase(ordertype)) {
                    isAsc = true;
                }
                if ("desc".equalsIgnoreCase(ordertype)) {
                    isAsc = false;
                }
            }
        }
        super.setCurrent((page - 1) * rows).setSize(rows).setOrderByField(orderField).setAsc(isAsc);
    }

    public PageInfo(int current, int size, String order) {
        super(current, size, order);
    }
}
