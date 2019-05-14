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
 * 截图demo
 * @author francis.xjl@qq.com
 * @date 2019-05-07 19:44
 **/
public class Selenium03Test {

    private static Logger logger = LoggerFactory.getLogger(Selenium03Test.class);

    // 下载chromedriver: https://npm.taobao.org/mirrors/chromedriver/
    private static final String CHROME_DRIVER_PATH = "/Users/xiajinlong/IdeaProjects/selenium_lab/install/74.0.3729.6/chromedriver";

    private static final String SNAPSHOT_DIR = "/Users/xiajinlong/Downloads/snapshots";
    private static final String SNAPSHOT_PREFIX = "selenium-test-03-";

    @Test
    public void test() {
        // 设置chromedriver路径
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.xxx.com/shanghai/");
        saveSnapshot(driver, SNAPSHOT_DIR, SNAPSHOT_PREFIX);

        // 录入建筑面积：80
        driver.findElement(By.cssSelector("[name=area]")).sendKeys("80");
        saveSnapshot(driver, SNAPSHOT_DIR, SNAPSHOT_PREFIX);
//        driver.findElement(By.className("Jia_area_input")).sendKeys("80");

        // 输入手机号
        driver.findElement(By.cssSelector("[name=mobile]")).sendKeys("138001380000");
        saveSnapshot(driver, SNAPSHOT_DIR, SNAPSHOT_PREFIX);

        // 判断成功提示没有弹出
        assert !driver.findElement(By.className("Jia_online_quote")).isDisplayed();

        // 点击“立即获取”
        driver.findElement(By.className("Jia_obtain_btn")).click();
        saveSnapshot(driver, SNAPSHOT_DIR, SNAPSHOT_PREFIX);

        // 判断成功提示弹出
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Jia_online_quote")));
        saveSnapshot(driver, SNAPSHOT_DIR, SNAPSHOT_PREFIX);

        driver.close();
    }

    /**
     * 保存截图至对应的文件夹，返回对应的文件名
     * @param driver
     * @param folder
     * @return
     */
    public static String saveSnapshot(ChromeDriver driver, String folder, String fileNamePrefix) {
        String fileName = fileNamePrefix + System.currentTimeMillis() + ".png";
        // 使用getScreenshotAs截图
        try {
            File srcFile = driver.getScreenshotAs(OutputType.FILE);// 执行屏幕截取
            FileUtils.copyFile(srcFile, new File(folder, fileName));
            return fileName;
        } catch (Exception e) {
            logger.error("截图功能出错："  + e.getMessage(), e);
        }

        return fileName;
    }
}
