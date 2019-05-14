package cn.xiajl.selenium_lab;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * findElement Demo
 * @author francis.xjl@qq.com
 * @date 2019-05-07 19:44
 **/
public class Selenium01Test {

    // 下载chromedriver: https://npm.taobao.org/mirrors/chromedriver/
    private static final String CHROME_DRIVER_PATH = "/Users/xiajinlong/IdeaProjects/selenium_lab/install/74.0.3729.6/chromedriver";

    @Test
    public void test() {
        // 设置chromedriver路径
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.xxx.com/shanghai/");

        // 录入建筑面积：80
        driver.findElement(By.cssSelector("[name=area]")).sendKeys("80");
//        driver.findElement(By.className("Jia_area_input")).sendKeys("80");

        // 输入手机号
        driver.findElement(By.cssSelector("[name=mobile]")).sendKeys("138001380000");

        // 判断成功提示没有弹出
        assert !driver.findElement(By.className("Jia_online_quote")).isDisplayed();

        // 点击“立即获取”
        driver.findElement(By.className("Jia_obtain_btn")).click();

        // 判断成功提示弹出
        assert driver.findElement(By.className("Jia_online_quote")).isDisplayed();

        driver.close();
    }

}
