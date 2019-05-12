package cn.xiajl.selenium_lab.chrome.network.event;

/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:15
 */
public class LoadingFinishedEvent {

    private String requestId;//	唯一请求ID
    private float timestamp;//	以过去某个任意时间点为基点，从打开页面开始，以秒为单位单调递增的时间戳
    private long encodedDataLength;//	响应字节数
    private boolean blockedCrossSiteDocument;//	如果由于跨域阻塞了响应，则为true

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

    public long getEncodedDataLength() {
        return encodedDataLength;
    }

    public void setEncodedDataLength(long encodedDataLength) {
        this.encodedDataLength = encodedDataLength;
    }

    public boolean isBlockedCrossSiteDocument() {
        return blockedCrossSiteDocument;
    }

    public void setBlockedCrossSiteDocument(boolean blockedCrossSiteDocument) {
        this.blockedCrossSiteDocument = blockedCrossSiteDocument;
    }
}
