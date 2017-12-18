package cn.yuntangnet.duizhang.common.util;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang3.StringUtils;

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
            String pageStr = (String) params.get("page");
            String rowsStr = (String) params.get("rows");

            if (StringUtils.isNotBlank(pageStr) && StringUtils.isNotBlank(rowsStr)) {
                int input_page = Integer.parseInt(pageStr);
                if (input_page >= 1) {
                    page = input_page;
                }
                int input_rows = Integer.parseInt(rowsStr);
                if (input_rows >= 10 && input_rows <= 100) {
                    rows = input_rows;
                }
            }
            String sidx = (String) params.get("sidx");
            if (StringUtils.isNotBlank(sidx)) {
                orderField = sidx;
            }

            String ordertype = (String) params.get("order");
            if (StringUtils.isNotBlank(ordertype)) {
                if ("asc".equalsIgnoreCase(ordertype)) {
                    isAsc = true;
                }
                if ("desc".equalsIgnoreCase(ordertype)) {
                    isAsc = false;
                }
            }
        }
        super.setCurrent(page).setSize(rows).setOrderByField(orderField).setAsc(isAsc);
    }

    public PageInfo(int current, int size, String order) {
        super(current, size, order);
    }
}
