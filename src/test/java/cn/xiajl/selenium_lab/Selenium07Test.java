package cn.xiajl.selenium_lab;

import cn.xiajl.selenium_lab.chrome.network.ChromeDriverProxy;
import cn.xiajl.selenium_lab.utils.PerformanceLogs;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * 网络返回体数据 demo
 * @author francis.xjl@qq.com
 * @date 2019-05-12 19:44
 **/
public class Selenium07Test {

    private static Logger logger = LoggerFactory.getLogger(Selenium07Test.class);

    // 下载chromedriver: https://npm.taobao.org/mirrors/chromedriver/
    private static final String CHROME_DRIVER_PATH = "/Users/xiajinlong/IdeaProjects/selenium_lab/install/74.0.3729.6/chromedriver";

    @Test
    public void test() {
        // 设置chromedriver路径
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        LoggingPreferences preference = new LoggingPreferences();
        preference.enable(LogType.BROWSER, Level.ALL);
        preference.enable(LogType.PERFORMANCE, Level.ALL);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, preference);

        ChromeDriverProxy driver = new ChromeDriverProxy(chromeOptions);

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("http://www.jia.com/shanghai/");

        // 录入建筑面积：80
        driver.findElement(By.cssSelector("[name=area]")).sendKeys("80");

        // 输入手机号
        driver.findElement(By.cssSelector("[name=mobile]")).sendKeys("138001380000");

        // 判断成功提示没有弹出
        assert !driver.findElement(By.className("Jia_online_quote")).isDisplayed();

        // 点击“立即获取”
        driver.findElement(By.className("Jia_obtain_btn")).click();

        // 判断成功提示弹出
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Jia_online_quote")));

        List<String> networkLogs = getNetworkLogs(driver);

        for(String networkLog : networkLogs) {
            System.out.println("networkLog:" + networkLog);
        }

        driver.close();
    }

    /**
     * 获取网络访问日志
     * @param driver
     * @return
     */
    private static List<String> getNetworkLogs(ChromeDriverProxy driver) {
        Logs logs = driver.manage().logs();
        List<String> result = new ArrayList<String>();

        Set<String> availableLogTypes = logs.getAvailableLogTypes();

        if(availableLogTypes.contains(LogType.PERFORMANCE)) {
            LogEntries logEntries = logs.get(LogType.PERFORMANCE);
            result.addAll(PerformanceLogs.doBuildPerformanceLogsFriendly(driver, logEntries));
        }

        return result;
    }
}