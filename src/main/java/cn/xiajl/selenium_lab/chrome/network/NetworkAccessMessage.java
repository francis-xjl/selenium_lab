package cn.xiajl.selenium_lab.chrome.network;

/**
 * 网络请求的汇总信息
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:33
 */
public class NetworkAccessMessage {

    private String requestId; // 唯一标识
    private long requestTime; // 请求时间(毫秒)
    private long responseTime; // 响应时间(毫秒)
    private long finishTime; // 完成时间(毫秒)
    private int statusCode; // 状态码
    private String url;

    private String errorText;//	错误原因提示
    private String blockedReason;//	请求被阻塞的原因

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getBlockedReason() {
        return blockedReason;
    }

    public void setBlockedReason(String blockedReason) {
        this.blockedReason = blockedReason;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
