package cn.xiajl.selenium_lab;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * 控制台输出 demo
 * @author francis.xjl@qq.com
 * @date 2019-05-12 19:44
 **/
public class Selenium05Test {

    private static Logger logger = LoggerFactory.getLogger(Selenium05Test.class);

    // 下载chromedriver: https://npm.taobao.org/mirrors/chromedriver/
    private static final String CHROME_DRIVER_PATH = "/Users/xiajinlong/IdeaProjects/selenium_lab/install/74.0.3729.6/chromedriver";

    @Test
    public void test() {

        // 设置chromedriver路径
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeDriver driver = new ChromeDriver();
        driver.setLogLevel(Level.ALL);

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.xxx.com/shanghai/");

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

        List<String> consoleLogs = getConsoleLogs(driver);

        for(String consoleLog : consoleLogs) {
            System.out.println("consoleLog:" + consoleLog);
        }

        driver.close();
    }

    /**
     * 获取控制台输出
     * @param driver
     * @return
     */
    private static List<String> getConsoleLogs(WebDriver driver) {
        Logs logs = driver.manage().logs();

        List<String> result = new ArrayList<String>();

        Set<String> availableLogTypes = logs.getAvailableLogTypes();
        if(availableLogTypes.contains(LogType.BROWSER)) {
            for(LogEntry entry : logs.get(LogType.BROWSER)) {
                result.add(entry.toString());
            }
        }

        return result;
    }
}