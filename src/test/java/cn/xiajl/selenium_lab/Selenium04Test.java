package cn.xiajl.selenium_lab;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 页面源码demo
 * @author francis.xjl@qq.com
 * @date 2019-05-07 19:44
 **/
public class Selenium04Test {

    private static Logger logger = LoggerFactory.getLogger(Selenium04Test.class);

    // 下载chromedriver: https://npm.taobao.org/mirrors/chromedriver/
    private static final String CHROME_DRIVER_PATH = "/Users/xiajinlong/IdeaProjects/selenium_lab/install/74.0.3729.6/chromedriver";

    @Test
    public void test() {
        // 设置chromedriver路径
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.jia.com/shanghai/");

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

        logger.info("page source:\n" + driver.getPageSource());

        driver.close();
    }
}
