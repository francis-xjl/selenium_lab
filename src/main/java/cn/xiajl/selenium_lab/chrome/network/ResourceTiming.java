package cn.xiajl.selenium_lab.chrome.network;

/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:06
 */
public class ResourceTiming {

    private float requestTime;//	时间基线
    private float proxyStart;//	Started resolving proxy.
    private float proxyEnd;//	Finished resolving proxy.
    private float dnsStart;//	Started DNS address resolve.
    private float dnsEnd;//	Finished DNS address resolve.
    private float connectStart;//	Started connecting to the remote host.
    private float connectEnd;//	Connected to the remote host.
    private float sslStart;//	Started SSL handshake.
    private float sslEnd;//	Finished SSL handshake.
    private float workerStart;//	Started running ServiceWorker.
    private float workerReady;//	Finished Starting ServiceWorker.
    private float sendStart;//	Started sending request.
    private float sendEnd;//	Finished sending request.
    private float pushStart;//	Time the server started pushing request.
    private float pushEnd;//	Time the server finished pushing request.
    private float receiveHeadersEnd;//	Finished receiving response headers.

    public float getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(float requestTime) {
        this.requestTime = requestTime;
    }

    public float getProxyStart() {
        return proxyStart;
    }

    public void setProxyStart(float proxyStart) {
        this.proxyStart = proxyStart;
    }

    public float getProxyEnd() {
        return proxyEnd;
    }

    public void setProxyEnd(float proxyEnd) {
        this.proxyEnd = proxyEnd;
    }

    public float getDnsStart() {
        return dnsStart;
    }

    public void setDnsStart(float dnsStart) {
        this.dnsStart = dnsStart;
    }

    public float getDnsEnd() {
        return dnsEnd;
    }

    public void setDnsEnd(float dnsEnd) {
        this.dnsEnd = dnsEnd;
    }

    public float getConnectStart() {
        return connectStart;
    }

    public void setConnectStart(float connectStart) {
        this.connectStart = connectStart;
    }

    public float getConnectEnd() {
        return connectEnd;
    }

    public void setConnectEnd(float connectEnd) {
        this.connectEnd = connectEnd;
    }

    public float getSslStart() {
        return sslStart;
    }

    public void setSslStart(float sslStart) {
        this.sslStart = sslStart;
    }

    public float getSslEnd() {
        return sslEnd;
    }

    public void setSslEnd(float sslEnd) {
        this.sslEnd = sslEnd;
    }

    public float getWorkerStart() {
        return workerStart;
    }

    public void setWorkerStart(float workerStart) {
        this.workerStart = workerStart;
    }

    public float getWorkerReady() {
        return workerReady;
    }

    public void setWorkerReady(float workerReady) {
        this.workerReady = workerReady;
    }

    public float getSendStart() {
        return sendStart;
    }

    public void setSendStart(float sendStart) {
        this.sendStart = sendStart;
    }

    public float getSendEnd() {
        return sendEnd;
    }

    public void setSendEnd(float sendEnd) {
        this.sendEnd = sendEnd;
    }

    public float getPushStart() {
        return pushStart;
    }

    public void setPushStart(float pushStart) {
        this.pushStart = pushStart;
    }

    public float getPushEnd() {
        return pushEnd;
    }

    public void setPushEnd(float pushEnd) {
        this.pushEnd = pushEnd;
    }

    public float getReceiveHeadersEnd() {
        return receiveHeadersEnd;
    }

    public void setReceiveHeadersEnd(float receiveHeadersEnd) {
        this.receiveHeadersEnd = receiveHeadersEnd;
    }
}
