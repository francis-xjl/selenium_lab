package cn.xiajl.selenium_lab.chrome.network;

/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * chrome响应数据
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:05
 */
public class ChromeResponse {

    private String url;//	请求url
    private int status;//	响应状态码
    private String statusText;//	状态码内容
    private Object headers;//	响应头部，json格式
    private String headersText;//	响应头部，文本格式
    private String mimeType;//	Resource mimeType
    private Object requestHeaders;//	请求头部，json格式
    private String requestHeadersText;//	请求头部，文本格式
    private boolean connectionReused;//	连接是否被复用
    private long connectionId;//	物理连接ID
    private String remoteIPAddress;//	Remote IP address
    private int remotePort;//	Remote port
    private boolean fromDiskCache;//	是否直接从浏览器缓存获取资源
    private boolean fromServiceWorker;//	Specifies that the request was served from the ServiceWorker
    private long encodedDataLength;//	响应字节数
    private ResourceTiming timing;//	ResourceTiming对象
    private String protocol;//	协议
    private String securityState;//	Security state of the request resource：unknown, neutral, insecure, secure, info
//    private	SecurityDetails	securityDetails	;//	Security details for the request


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public Object getHeaders() {
        return headers;
    }

    public void setHeaders(Object headers) {
        this.headers = headers;
    }

    public String getHeadersText() {
        return headersText;
    }

    public void setHeadersText(String headersText) {
        this.headersText = headersText;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Object getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Object requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestHeadersText() {
        return requestHeadersText;
    }

    public void setRequestHeadersText(String requestHeadersText) {
        this.requestHeadersText = requestHeadersText;
    }

    public boolean isConnectionReused() {
        return connectionReused;
    }

    public void setConnectionReused(boolean connectionReused) {
        this.connectionReused = connectionReused;
    }

    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    public String getRemoteIPAddress() {
        return remoteIPAddress;
    }

    public void setRemoteIPAddress(String remoteIPAddress) {
        this.remoteIPAddress = remoteIPAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public boolean isFromDiskCache() {
        return fromDiskCache;
    }

    public void setFromDiskCache(boolean fromDiskCache) {
        this.fromDiskCache = fromDiskCache;
    }

    public boolean isFromServiceWorker() {
        return fromServiceWorker;
    }

    public void setFromServiceWorker(boolean fromServiceWorker) {
        this.fromServiceWorker = fromServiceWorker;
    }

    public long getEncodedDataLength() {
        return encodedDataLength;
    }

    public void setEncodedDataLength(long encodedDataLength) {
        this.encodedDataLength = encodedDataLength;
    }

    public ResourceTiming getTiming() {
        return timing;
    }

    public void setTiming(ResourceTiming timing) {
        this.timing = timing;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSecurityState() {
        return securityState;
    }

    public void setSecurityState(String securityState) {
        this.securityState = securityState;
    }
}
