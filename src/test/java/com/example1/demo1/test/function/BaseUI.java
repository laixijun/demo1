package com.example1.demo1.test.function;

import com.example1.demo1.test.test.lib.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseUI {
//    public static void main(String[] args){
//        BaseUI base = new BaseUI();
//        WebDriver driver=base.driver("https://www.baidu.com/");
//        System.out.println("This test page title is :" + driver.getTitle());
//    }

    public WebDriver driver(String url){
        BaseUI baseUI = new BaseUI();
        String path = baseUI.path("/src/test/java/com/example1/demo1/test/driver/chromedriver");
        System.setProperty("webdriver.chrome.driver", path);
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        return driver;
    }
    public String path(String pathFile){
        Tools tool = new Tools();
        String path = tool.path();
        System.out.println(path);
        path = path + pathFile;
        return path;
    }
}
