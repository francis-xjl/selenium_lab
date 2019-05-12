package cn.xiajl.selenium_lab.utils;

import cn.xiajl.selenium_lab.chrome.network.ChromeDriverProxy;
import cn.xiajl.selenium_lab.chrome.network.NetworkAccessMessage;
import cn.xiajl.selenium_lab.chrome.network.ResponseBodyVo;
import cn.xiajl.selenium_lab.chrome.network.event.LoadingFailedEvent;
import cn.xiajl.selenium_lab.chrome.network.event.LoadingFinishedEvent;
import cn.xiajl.selenium_lab.chrome.network.event.RequestWillBeSentEvent;
import cn.xiajl.selenium_lab.chrome.network.event.ResponseReceivedEvent;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jboss.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * chrome网络相关的工具类
 * @author francis.xjl@qq.com
 * @date 2018/11/29 11:29
 */
public class PerformanceLogs {

    private static Logger logger = Logger.getLogger(PerformanceLogs.class);

    // Fired when page is about to send HTTP request.
    public static final String NETWORK_REQUEST_WILL_BE_SENT = "Network.requestWillBeSent";
    // Fired when HTTP response is available.
    public static final String NETWORK_RESPONSE_RECEIVED = "Network.responseReceived";
    // Fired when HTTP request has finished loading.
    public static final String NETWORK_LOADING_FAILED = "Network.loadingFailed";
    // Fired when HTTP request has failed to load.
    public static final String NETWORK_LOADING_FINISHED = "Network.loadingFinished";

    public static List<String> doBuildPerformanceLogsFriendly(LogEntries logEntries) {
        List<String> resultList = new ArrayList<>();

        List<LoadingFailedEvent> loadingFailedEvents = new ArrayList<>();
        List<LoadingFinishedEvent> loadingFinishedEvents = new ArrayList<>();
        List<RequestWillBeSentEvent> requestWillBeSentEvents = new ArrayList<>();
        List<ResponseReceivedEvent> responseReceivedEvents = new ArrayList<>();

        for(LogEntry entry : logEntries) {
            JSONObject jsonObj = JSON.parseObject(entry.getMessage()).getJSONObject("message");
            String method = jsonObj.getString("method");
            String params = jsonObj.getString("params");

            if (method.equals(NETWORK_REQUEST_WILL_BE_SENT)) {
                RequestWillBeSentEvent request = JSON.parseObject(params, RequestWillBeSentEvent.class);
                requestWillBeSentEvents.add(request);
            } else if (method.equals(NETWORK_RESPONSE_RECEIVED)) {
                ResponseReceivedEvent response = JSON.parseObject(params, ResponseReceivedEvent.class);
                responseReceivedEvents.add(response);
            } else if (method.equals(NETWORK_LOADING_FINISHED)) {
                LoadingFinishedEvent finished = JSON.parseObject(params, LoadingFinishedEvent.class);
                loadingFinishedEvents.add(finished);
            } else if (method.equals(NETWORK_LOADING_FAILED)) {
                LoadingFailedEvent failed = JSON.parseObject(params, LoadingFailedEvent.class);
                loadingFailedEvents.add(failed);
            }
        }

        List<NetworkAccessMessage> accessMessages = buildNetworkAccessMessage(requestWillBeSentEvents, responseReceivedEvents, loadingFinishedEvents, loadingFailedEvents);

        resultList.add("耗时(毫秒)\t状态码\tURL\t请求时间\t响应时间\t完成时间\t错误信息\t阻塞原因");
        for(NetworkAccessMessage message : accessMessages) {
            resultList.add(buildMessageLine(message));
        }

        return resultList;
    }

    /**
     * 将chrome的性能日志转化成以下格式：
     * 耗时(毫秒)	状态码	URL	请求时间	响应时间	完成时间	错误信息	阻塞原因
     * 以便日志查看。
     * @param logEntries
     * @return
     */
    public static List<String> doBuildPerformanceLogsFriendly(ChromeDriverProxy driver, LogEntries logEntries) {
        List<String> resultList = new ArrayList<>();

        List<LoadingFailedEvent> loadingFailedEvents = new ArrayList<>();
        List<LoadingFinishedEvent> loadingFinishedEvents = new ArrayList<>();
        List<RequestWillBeSentEvent> requestWillBeSentEvents = new ArrayList<>();
        List<ResponseReceivedEvent> responseReceivedEvents = new ArrayList<>();

        for(LogEntry entry : logEntries) {
            JSONObject jsonObj = JSON.parseObject(entry.getMessage()).getJSONObject("message");
            String method = jsonObj.getString("method");
            String params = jsonObj.getString("params");

            if (method.equals(NETWORK_REQUEST_WILL_BE_SENT)) {
                RequestWillBeSentEvent request = JSON.parseObject(params, RequestWillBeSentEvent.class);
                requestWillBeSentEvents.add(request);
            } else if (method.equals(NETWORK_RESPONSE_RECEIVED)) {
                ResponseReceivedEvent response = JSON.parseObject(params, ResponseReceivedEvent.class);
                responseReceivedEvents.add(response);
            } else if (method.equals(NETWORK_LOADING_FINISHED)) {
                LoadingFinishedEvent finished = JSON.parseObject(params, LoadingFinishedEvent.class);
                loadingFinishedEvents.add(finished);
            } else if (method.equals(NETWORK_LOADING_FAILED)) {
                LoadingFailedEvent failed = JSON.parseObject(params, LoadingFailedEvent.class);
                loadingFailedEvents.add(failed);
            }
        }

        List<NetworkAccessMessage> accessMessages = buildNetworkAccessMessage(requestWillBeSentEvents, responseReceivedEvents, loadingFinishedEvents, loadingFailedEvents);

        resultList.add("耗时(毫秒)\t状态码\tURL\t请求时间\t响应时间\t完成时间\t错误信息\t阻塞原因");
        for(NetworkAccessMessage message : accessMessages) {
            resultList.add(buildMessageLine(message));
        }

        resultList.add("------------------");

        List<String> contentData = getHttpTransferDataIfNecessary(driver, responseReceivedEvents);
        resultList.addAll(contentData);

        return resultList;
    }

    // 保存网络请求（包括页面源至文件)
    private static List<String> getHttpTransferDataIfNecessary(ChromeDriverProxy driver, List<ResponseReceivedEvent> responses) {
        List<String> content = new ArrayList<>(1024);

        for(ResponseReceivedEvent response : responses) {
            String url = response.getResponse().getUrl();
            boolean staticFiles = url.endsWith(".png")
                    || url.endsWith(".jpg")
                    || url.endsWith(".css")
                    || url.endsWith(".ico")
                    || url.endsWith(".js")
                    || url.endsWith(".gif");

            if(!staticFiles && url.startsWith("http")) {
                content.add(url);
                ResponseBodyVo body = driver.getResponseBody(response.getRequestId());
                if(body != null && body.getStatus() == 0) {
                    content.add("base64Encoded:" + body.getValue().getBase64Encoded());
                    content.add("body:\n" + body.getValue().getBody());
                }
                content.add("\n");
            }
        }

        return content;
    }

    private static String buildMessageLine(NetworkAccessMessage message) {
        if(message == null){ return "";}

        String errorText = message.getErrorText();
        String blockedReason = message.getBlockedReason();

        if(errorText == null) {
            errorText = "-";
        }

        if(blockedReason == null) {
            blockedReason = "-";
        }

        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                computeCostTime(message),
                message.getStatusCode(),
                message.getUrl(),
                formatTime(message.getRequestTime()),
                formatTime(message.getResponseTime()),
                formatTime(message.getFinishTime()),
                errorText,
                blockedReason);
    }

    private static long computeCostTime(NetworkAccessMessage message) {
        long responseCost = message.getResponseTime() - message.getRequestTime();
        long finishCost = message.getFinishTime() - message.getRequestTime();
        return Math.max(responseCost, finishCost);
    }

    private static String formatTime(long timestamp) {
        //时间戳极小的情况下，说明时间有问题，显示为占位符，表示时间并未获取到
        if(timestamp < 10) {
            return "-";
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        return format.format(dateTime);
    }

    private static List<NetworkAccessMessage> buildNetworkAccessMessage(List<RequestWillBeSentEvent> requestWillBeSentEvents, List<ResponseReceivedEvent> responseReceivedEvents, List<LoadingFinishedEvent> loadingFinishedEvents, List<LoadingFailedEvent> loadingFailedEvents) {
        if(CollectionUtils.isEmpty(requestWillBeSentEvents)) {
            return new ArrayList<>();
        }

        Map<String, NetworkAccessMessage> messageMap = new HashMap<>();
        Map<String, RequestWillBeSentEvent> requestMap = new HashMap<>();
        for(RequestWillBeSentEvent event: requestWillBeSentEvents) {
            NetworkAccessMessage message = new NetworkAccessMessage();
            message.setRequestTime((long)(event.getWallTime() * 1000));// UTC时间
            message.setUrl(event.getRequest().getUrl());

            messageMap.put(event.getRequestId(), message);
            requestMap.put(event.getRequestId(), event);
        }

        // 收到响应
        for(ResponseReceivedEvent event: responseReceivedEvents) {
            NetworkAccessMessage message = messageMap.get(event.getRequestId());
            RequestWillBeSentEvent request = requestMap.get(event.getRequestId());
            if(message != null && request != null) {
                //必须先*1000转化为long再计算，否则精度会存在问题
                long endTime = (long)(request.getWallTime() * 1000) +
                        (long)(event.getTimestamp() * 1000) - (long)(request.getTimestamp() * 1000);
                message.setResponseTime(endTime);
                message.setStatusCode(event.getResponse().getStatus());
            }
        }

        //传输成功
        for(LoadingFinishedEvent event: loadingFinishedEvents) {
            NetworkAccessMessage message = messageMap.get(event.getRequestId());
            RequestWillBeSentEvent request = requestMap.get(event.getRequestId());
            if(message != null && request != null) {
                //必须先*1000转化为long再计算，否则精度会存在问题
                long endTime = (long)(request.getWallTime() * 1000) +
                        (long)(event.getTimestamp() * 1000) - (long)(request.getTimestamp() * 1000);
                message.setFinishTime(endTime);
            }
        }

        //传输失败
        for(LoadingFailedEvent event: loadingFailedEvents) {
            NetworkAccessMessage message = messageMap.get(event.getRequestId());
            RequestWillBeSentEvent request = requestMap.get(event.getRequestId());
            if(message != null && request != null) {
                //必须先*1000转化为long再计算，否则精度会存在问题
                long endTime = (long)(request.getWallTime() * 1000) +
                        (long)(event.getTimestamp() * 1000) - (long)(request.getTimestamp() * 1000);
                message.setFinishTime(endTime);
                message.setBlockedReason(event.getBlockedReason());
                message.setErrorText(event.getErrorText());
            }
        }

        return new ArrayList<>(messageMap.values());
    }
}
