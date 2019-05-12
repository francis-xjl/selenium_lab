package cn.xiajl.selenium_lab.chrome.network.event;

import cn.xiajl.selenium_lab.chrome.network.ChromeRequest;
import cn.xiajl.selenium_lab.chrome.network.ChromeResponse;
import cn.xiajl.selenium_lab.chrome.network.Initiator;

/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:02
 */
public class RequestWillBeSentEvent {

    private String requestId;//	唯一请求ID
    private String loaderId;//	加载ID
    private String documentURL;//	页面文档URL
    private ChromeRequest request;//	请求数据对象
    private float timestamp;//	以过去某个任意时间点为基点，从打开页面开始，以秒为单位单调递增的时间戳
    private float wallTime;//	UTC时间
    private Initiator initiator;//	请求数据对象初始化对象
    private ChromeResponse redirectResponse;//	重定向响应对象
    private String type;//	资源类型
    private String frameId;//	FrameID
    private boolean hasUserGesture;//	Whether the request is initiated by a user gesture. Defaults to false.

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

    public String getDocumentURL() {
        return documentURL;
    }

    public void setDocumentURL(String documentURL) {
        this.documentURL = documentURL;
    }

    public ChromeRequest getRequest() {
        return request;
    }

    public void setRequest(ChromeRequest request) {
        this.request = request;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public float getWallTime() {
        return wallTime;
    }

    public void setWallTime(float wallTime) {
        this.wallTime = wallTime;
    }

    public Initiator getInitiator() {
        return initiator;
    }

    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }

    public ChromeResponse getRedirectResponse() {
        return redirectResponse;
    }

    public void setRedirectResponse(ChromeResponse redirectResponse) {
        this.redirectResponse = redirectResponse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public boolean isHasUserGesture() {
        return hasUserGesture;
    }

    public void setHasUserGesture(boolean hasUserGesture) {
        this.hasUserGesture = hasUserGesture;
    }
}
