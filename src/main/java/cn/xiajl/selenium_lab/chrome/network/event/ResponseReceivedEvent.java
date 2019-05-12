package cn.xiajl.selenium_lab.chrome.network.event;

import cn.xiajl.selenium_lab.chrome.network.ChromeResponse;


/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:10
 */
public class ResponseReceivedEvent {

    private String requestId;//	唯一请求ID
    private float timestamp;//	以过去某个任意时间点为基点，从打开页面开始，以秒为单位单调递增的时间戳
    private String type;//	资源类型
    private ChromeResponse response;//	响应对象
    private String frameId;//	FrameID
    private String loaderId;// LoaderId

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public ChromeResponse getResponse() {
        return response;
    }

    public void setResponse(ChromeResponse response) {
        this.response = response;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getLoaderId() {
        return loaderId;
    }

    public void setLoaderId(String loaderId) {
        this.loaderId = loaderId;
    }
}
