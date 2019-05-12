package cn.xiajl.selenium_lab.chrome.network;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

/**
 * ChromeProxy的代理
 * @author francis.xjl@qq.com
 * @date 2018/12/4 16:03
 */
public class ChromeDriverProxy extends ChromeDriver {


    private static final int COMMAND_TIMEOUT = 10000;
    private static final int CHROME_DRIVER_DEFAULT_PORT = 9999;
    private int chromeDriverPort = CHROME_DRIVER_DEFAULT_PORT;

    private Logger logger = Logger.getLogger(ChromeDriverProxy.class);

    public ChromeDriverProxy(ChromeOptions options, int port) {
        super(new ChromeDriverService.Builder().usingPort(port).build(), options);
        this.chromeDriverPort = port;
    }

    public ChromeDriverProxy(ChromeOptions options) {
        super(new ChromeDriverService.Builder().usingPort(CHROME_DRIVER_DEFAULT_PORT).build(), options);
    }

    /**
     * 根据请求ID获取返回内容
     * @param requestId
     * @return
     */
    public ResponseBodyVo getResponseBody(String requestId) {
        ResponseBodyVo result = null;

        try {
            String url = String.format("http://localhost:%s/session/%s/goog/cdp/execute", chromeDriverPort, getSessionId());

            HttpPost httpPost = new HttpPost(url);

            JSONObject object = new JSONObject();
            JSONObject params = new JSONObject();
            params.put("requestId", requestId);
            object.put("cmd", "Network.getResponseBody");
            object.put("params", params);

            httpPost.setEntity(new StringEntity(object.toString()));

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(COMMAND_TIMEOUT).setConnectTimeout(COMMAND_TIMEOUT).build();
            CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

            HttpResponse response = httpClient.execute(httpPost);

            JSONObject data = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            return JSONObject.toJavaObject(data, ResponseBodyVo.class);
        } catch (IOException e) {
            logger.error("getResponseBody failed!", e);
        }

        return result;
    }

}



