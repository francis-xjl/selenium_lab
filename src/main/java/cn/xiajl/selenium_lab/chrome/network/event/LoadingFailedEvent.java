package cn.xiajl.selenium_lab.chrome.network.event;

/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:11
 */
public class LoadingFailedEvent {

    private String requestId;//	唯一请求ID
    private String loaderId;//	加载ID
    private float timestamp;//	以过去某个任意时间点为基点，从打开页面开始，以秒为单位单调递增的时间戳
    private String type;//	资源类型
    private String errorText;//	错误原因提示
    private boolean canceled;//	如果请求加载被取消，则为true
    private String blockedReason;//	请求被阻塞的原因

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getLoaderId() {
        return loaderId;
    }

    public void setLoaderId(String loaderId) {
        this.loaderId = loaderId;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getBlockedReason() {
        return blockedReason;
    }

    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }
}
