package foundation.util;

/**
 * @Author: NZY
 * @Date: 2020/6/18 17:28
 */
public class Result {
    public int code;
    public String msg;
    public Object data;
    
    public Result() {
    }
    
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
