package cn.xiajl.selenium_lab.chrome.network;

/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * chrome请求数据
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:03
 */
public class ChromeRequest {

    private String url;//	请求url
    private String method;//	HTTP请求类型
    private Object headers;//	请求头信息
    private String postData;//	Post请求数据
    private boolean hasPostData;//	如果是Post请求，则为true
    private String mixedContentType;//	是否存在混淆内容问题：blockable, optionally-blockable, none.
    private String initialPriority;//	资源加载优先级：VeryLow, Low, Medium, High, VeryHigh.
    private String referrerPolicy;//	跨域策略：no-referrer-when-downgrade, no-referrer, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin
    private boolean isLinkPreload;//	是否通过预加载方式加载

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getHeaders() {
        return headers;
    }

    public void setHeaders(Object headers) {
        this.headers = headers;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public boolean isHasPostData() {
        return hasPostData;
    }

    public void setHasPostData(boolean hasPostData) {
        this.hasPostData = hasPostData;
    }

    public String getMixedContentType() {
        return mixedContentType;
    }

    public void setMixedContentType(String mixedContentType) {
        this.mixedContentType = mixedContentType;
    }

    public String getInitialPriority() {
        return initialPriority;
    }

    public void setInitialPriority(String initialPriority) {
        this.initialPriority = initialPriority;
    }

    public String getReferrerPolicy() {
        return referrerPolicy;
    }

    public void setReferrerPolicy(String referrerPolicy) {
        this.referrerPolicy = referrerPolicy;
    }

    public boolean isLinkPreload() {
        return isLinkPreload;
    }

    public void setLinkPreload(boolean linkPreload) {
        isLinkPreload = linkPreload;
    }
}
