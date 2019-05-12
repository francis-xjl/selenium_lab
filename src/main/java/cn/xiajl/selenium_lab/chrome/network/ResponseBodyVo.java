package cn.xiajl.selenium_lab.chrome.network;

/**
 * TODO 描述
 * @author francis.xjl@qq.com
 * @date 2018/12/28 13:33
 */
public class ResponseBodyVo {

    private String sessionId;
    private Integer status;
    private ValueBody value;

    public static class ValueBody {
        private boolean base64Encoded;
        private String body;

        public boolean getBase64Encoded() {
            return base64Encoded;
        }

        public void setBase64Encoded(boolean base64Encoded) {
            this.base64Encoded = base64Encoded;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ValueBody getValue() {
        return value;
    }

    public void setValue(ValueBody value) {
        this.value = value;
    }
}
