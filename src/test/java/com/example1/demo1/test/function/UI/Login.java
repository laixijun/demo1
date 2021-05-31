package com.example1.demo1.test.function.UI;

import com.example1.demo1.test.function.BaseUI;
import com.example1.demo1.test.test.lib.ReadExcel;
import com.example1.demo1.test.test.lib.YmlUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Login {
    public static void main(String[] args){
        BaseUI baseUI = new BaseUI();
        YmlUtil ymlUtil = new YmlUtil();
        Object loginUrl = null;
        Object useremail = null;
        Object userpassword = null;
        // 记录逻辑判断标识
        String logiccontroll = "001";
        // 记录是否元素存在
        Boolean logiccontrollll = false;
        String js = "js";
        String filePath = "/Users/moka/Documents/dataCreate/readexcel/writeExcel.xlsx";
        ArrayList<String> columns = new ArrayList<>(Arrays.asList("sceneid", "scenename", "testid", "testname",
                "description", "xpathdata", "action", "inputdata", "logiccontrol", "iswait","js","exceptresult","executeresult","resultdetail"));
        ReadExcel re = new ReadExcel();
        ArrayList<Map<String,String>> testlist = null;
        testlist = re.readExcel(filePath,columns);
        Map<String, Object> appLogin = ymlUtil.getResMap("app");
        System.out.println(appLogin);
        for (String key : appLogin.keySet()) {
            System.out.println(key);
            if ( key.equals("appLogin")) {
                loginUrl = appLogin.get(key);
            } else if(key.equals("useremail")) {
                useremail = appLogin.get(key);
            } else if (key.equals("userpassword")){
                userpassword = appLogin.get(key);
            }
        }
        System.out.print(testlist);
        WebDriver driver = baseUI.driver((String)loginUrl);
        for (Map<String, String> testmap : testlist){
            System.out.println(testmap);
            System.out.println(testmap.get("testname"));
            System.out.println(testmap.get("iswait"));
            System.out.println(testmap.get("description"));
            // 判断 logiccontrol 是否需要进入逻辑判断
            if ("001".equals(testmap.get("logiccontrol"))){
                System.out.println("100");
                if ("Y".equals(testmap.get("iswait"))){
                    String xpathh = testmap.get("xpathdata");
                    driver = waitt(xpathh , driver);
                } else if ("sendKeys".equals(testmap.get("action"))){
                    System.out.println(testmap.get("action"));
                    driver.findElement(By.xpath(testmap.get("xpathdata"))).sendKeys(testmap.get("inputdata"));
                } else if ("click".equals(testmap.get("action"))){
                    driver.findElement(By.xpath(testmap.get("xpathdata"))).click();
                } else if ("click_action".equals(testmap.get("action"))){
                    System.out.println("click_action");
                    WebElement element = driver.findElement(By.xpath(testmap.get("xpathdata")));
                    Actions action=new Actions(driver);
                    action.click(element).perform();
                    String elementdata=driver.getPageSource();
                    System.out.print(elementdata);
                }
            }
            else if (!logiccontroll.equals(testmap.get("logiccontrol"))){
                if (!js.equals(testmap.get("js"))){
                    driver=findElement(driver, testmap.get("xpathdata"), testmap.get("js"));
                } else {
                    logiccontrollll = false;
                    String xpathhh = testmap.get("xpathdata");
                    boolean isJudgingElement = isJudgingElement(driver,xpathhh);
                    // 记录逻辑判断标识
                    logiccontroll = testmap.get("logiccontrol");
                    // 记录是否元素存在
                    logiccontrollll = isJudgingElement;
                }
                // 判断是否和上次的逻辑标识是否一样，如果一样走逻辑
            } else if (logiccontroll.equals(testmap.get("logiccontrol")) && true == logiccontrollll){
                if ("sendKeys".equals(testmap.get("action"))){
                    driver.findElement(By.xpath(testmap.get("xpathdata"))).sendKeys(testmap.get("inputdata"));
                } else if ("click".equals(testmap.get("action"))) {
                    driver.findElement(By.xpath(testmap.get("xpathdata"))).click();
                } else if ("Y".equals(testmap.get("iswait"))) {
                    String xpathh = testmap.get("xpathdata");
                    driver = waitt(xpathh, driver);
                }
            }
        }


//            if ("sendKeys".equals(testmap.get("action")) && ("".equals(testmap.get("logiccontrol")) || null == testmap.get("logiccontrol"))){
//                System.out.println(testmap.get("action"));
//                driver.findElement(By.xpath(testmap.get("xpathdata"))).sendKeys(testmap.get("inputdata"));
//            }
            // 是否等待用例
//            if ((!"".equals(testmap.get("iswait")) || null != testmap.get("iswait")) && ("".equals(testmap.get("logiccontrol")) || null == testmap.get("logiccontrol"))){
//                String xpathh = testmap.get("xpathdata");
//                driver = waitt(xpathh , driver);
//            } else if ("sendKeys".equals(testmap.get("action")) && ("".equals(testmap.get("logiccontrol")) || null == testmap.get("logiccontrol"))){
//                System.out.println(testmap.get("action"));
//                driver.findElement(By.xpath(testmap.get("xpathdata"))).sendKeys(testmap.get("inputdata"));
//            } else if ("click".equals(testmap.get("action")) && ("".equals(testmap.get("logiccontrol")) || null == testmap.get("logiccontrol"))){
//                driver.findElement(By.xpath(testmap.get("xpathdata"))).click();
//            } else if ((!"".equals(testmap.get("logiccontrol")) || null != testmap.get("logiccontrol")) && (!"".equals(testmap.get("iswait")) || null != testmap.get("iswait"))
//                    && (!logiccontroll.equals(testmap.get("logiccontrol")))){
//                String xpathhh = testmap.get("xpathdata");
//                boolean isJudgingElement = isJudgingElement(driver,xpathhh);
//                // 记录逻辑判断标识
//                logiccontroll = testmap.get("logiccontrol");
//                // 判断是否进入逻辑判断
//                logiccontrolll = true;
//                // 记录是否元素存在
//                logiccontrollll = isJudgingElement;
//                // 判断是否和上次的逻辑标识是否一样，如果一样走逻辑
//            } else if (logiccontroll.equals(testmap.get("logiccontrol")) && true == logiccontrollll){
//                if ("sendKeys".equals(testmap.get("action"))){
//                    driver.findElement(By.xpath(testmap.get("xpathdata"))).sendKeys(testmap.get("inputdata"));
//                } else if ("click".equals(testmap.get("action"))) {
//                    driver.findElement(By.xpath(testmap.get("xpathdata"))).click();
//                }
//            }
//        }




    }

    public static void mainback(String[] args){
        BaseUI baseUI = new BaseUI();
        YmlUtil ymlUtil = new YmlUtil();
        Object loginUrl = null;
        Object useremail = null;
        Object userpassword = null;
        Map<String, Object> appLogin = ymlUtil.getResMap("app");
        System.out.println(appLogin);
        for (String key : appLogin.keySet()) {
            System.out.println(key);
            if ( key.equals("appLogin")) {
                loginUrl = appLogin.get(key);
            } else if(key.equals("useremail")) {
                useremail = appLogin.get(key);
            } else if (key.equals("userpassword")){
                userpassword = appLogin.get(key);
            }
        }
        WebDriver driver = baseUI.driver((String)loginUrl);
        String xpathh = "//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/div[1]";
        driver = waitt(xpathh , driver);
        driver.findElement(By.xpath("//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/form/div[1]/label/input")).sendKeys((String)useremail);
        driver.findElement(By.xpath("//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/form/div[2]/label/input")).sendKeys((String)userpassword);
        driver.findElement(By.xpath("//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/form/div[3]/button/span")).click();
        String xpathhh = "//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/div[3]";
        boolean isJudgingElement = isJudgingElement(driver,xpathhh);
        if (true == isJudgingElement ){
            System.out.println("100");
            driver.findElement(By.xpath("//*[@id=\"login-app\"]/div/div[4]/div[4]/div[1]/div/button/span")).click();
        }

    }

    public static WebDriver waitt(String xpathh, WebDriver driver){
        //显示等待时间10s 等   全部产品>>  出现
        WebDriverWait w=new WebDriverWait(driver,10);
        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathh)));
        return driver;
    }
    /*
    *1、判断是否有元素
    *2、如果有元素就执行操作
    *3、如果没有元素就滑动界面
    * */
    public static WebDriver findElement(WebDriver webDriver, String xpathhh, String jss){
        boolean isJudgingE = false;
        isJudgingE = isJudgingElement(webDriver, xpathhh);
        while (false == isJudgingE) {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript(jss);
            isJudgingE = isJudgingElement(webDriver, xpathhh);
        }
        return webDriver;
    }
    /**
     * 判断某个元素是否存在
     */
    public static boolean isJudgingElement(WebDriver webDriver, String xpathhh) {
        try {
            waitt(xpathhh , webDriver);
            webDriver.findElement(By.xpath(xpathhh));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     *
     * @param driver 浏览器驱动
     * @param xpath xpath定位表达式
     */
    public static void javaScriptClick(WebDriver driver, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        try{
            if(element.isEnabled() && element.isDisplayed()){
                System.out.println("使用JS进行也面元素单击");
                //执行JS语句arguments[0].click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            }else {
                System.out.println("页面上元素无法进行单击操作");
            }
        }catch (StaleElementReferenceException e){
            System.out.println("页面元素没有附加在页面中" + Arrays.toString(e.getStackTrace()));
        }catch (NoSuchElementException e){
            System.out.println("在页面中没有找到要操作的元素" + Arrays.toString(e.getStackTrace()));
        }catch (Exception e){
            System.out.println("无法完成单击操作" + Arrays.toString(e.getStackTrace()));
        }
    }
}
