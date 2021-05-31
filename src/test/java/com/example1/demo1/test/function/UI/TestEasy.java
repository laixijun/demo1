package com.example1.demo1.test.function.UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestEasy {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/moka/IdeaProjects/demo1/src/test/java/com/example1/demo1/test/driver/chromedriver");
        WebDriver driver = new ChromeDriver();
//        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://app.mokahr.com/login");
        //登陆
        String logind = "//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/form/div[1]/label/input";
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(logind)));
        WebElement element = driver.findElement(By.xpath(logind));
        String data = element.getAttribute("outerHTML");
        System.out.println(data);
        element.sendKeys("yangzhiyong@mokahr.com");
        driver.findElement(By.xpath("//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/form/div[2]/label/input")).sendKeys("Zhy.yang123");
        driver.findElement(By.xpath("//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/form/div[3]/button/span")).click();
        String logindq = "//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/button/span";
        WebDriverWait waitlogindq = new WebDriverWait(driver,10);
        waitlogindq.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(logindq)));
        //强退已登陆的其他账号
        driver.findElement(By.xpath(logindq)).click();
        String logindother = "//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/div[3]/div[1]";
        WebDriverWait waitlogindother = new WebDriverWait(driver,10);
        waitlogindother.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(logindother)));
        //选择公司进入
        driver.findElement(By.xpath(logindother)).click();
//        String logindinto = "//*[@id=\"main-app\"]/div/div[7]/div[2]/div/div[1]";
//        WebDriverWait waitlogindinto = new WebDriverWait(driver,10);
//        waitlogindinto.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(logindinto)));
//        WebElement element3 = driver.findElement(By.xpath(logindinto));
//        String data3 = element3.getAttribute("outerHTML");
//        System.out.println(data3);
        // 进入设置界面
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions action = new Actions(driver);

        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"main-app\"]/div/div[1]/nav/ul/li[9]/a"));
        action.moveToElement(element2).perform();     //鼠标悬浮在 设置  元素上面
        WebElement element3 = driver.findElement(By.xpath("//*[@id=\"main-app\"]/div/div[1]/nav/ul/li[9]/a"));
        String data2 = element3.getAttribute("outerHTML");
        System.out.println(data2);
        element3.click();
        action.release(element3);
        //关闭弹窗
        driver.findElement(By.xpath("//*[@id=\"main-app\"]/div/div[7]")).click();
        String logindjiemian = "//*[@id=\"main-app\"]/div/div[7]/div[2]/div/div[1]/div/div[2]/div/div[15]/div[1]/div[1]";
        WebDriverWait waitlogindjiemian = new WebDriverWait(driver,10);
        waitlogindjiemian.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(logindjiemian)));
        Thread.sleep(20000);
        // 点击offer管理
        driver.findElement(By.xpath(logindjiemian)).click();
        String logindmanager = "//*[@id=\"main-app\"]/div/div[7]/div[2]/div/div[1]/div/div[2]/div/div[15]/div[2]/div[1]/div/div";
        WebDriverWait waitlogindmanager = new WebDriverWait(driver,10);
        waitlogindmanager.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(logindmanager)));
        //点击offer字段管理
        driver.findElement(By.xpath(logindmanager)).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
