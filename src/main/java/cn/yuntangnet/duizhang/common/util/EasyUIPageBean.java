package cn.yuntangnet.duizhang.common.util;

import java.util.List;

/**
 * 适用于easyui的表格数据
 *
 * @author 茂林
 * @since 2017/11/29 11:26
 */
public class EasyUIPageBean {

    private long total;
    private List rows;

    public EasyUIPageBean(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
