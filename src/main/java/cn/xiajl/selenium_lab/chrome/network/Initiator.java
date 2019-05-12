package cn.xiajl.selenium_lab.chrome.network;

/**
 * https://chromedevtools.github.io/devtools-protocol/tot/Network
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:08
 */
public class Initiator {

    private long lineNumber;
    private String type;
    private String url;

    public long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
