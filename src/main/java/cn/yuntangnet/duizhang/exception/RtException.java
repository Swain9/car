package cn.yuntangnet.duizhang.exception;

/**
 * 自定义异常:运行时异常
 *
 * @author 茂林
 * @since 2017/11/21 10:17
 */
public class RtException extends RuntimeException {

    private String msg;
    private int code = 500;

    public RtException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public RtException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public RtException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public RtException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
