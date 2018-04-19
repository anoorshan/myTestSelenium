package testCase;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Set;

public class TestApiOne extends TestWebsite {
    String url1="http://www.sougou.com";
    String url2="http://www.baidu.com";
    //获取页面的源代码和URL
    @Test
    public void getPageSourceAndUrl(){
        driver.get(url1);
        String pageSource = driver.getPageSource();
        System.out.println(pageSource);
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);

    }

    //模拟浏览器的后退功能
    @Test
    public void visitNextUrl(){
        driver.navigate().to(url1);
        System.out.println(driver.getTitle());
        driver.navigate().to(url2);
        System.out.println(driver.getTitle());
        driver.navigate().back();
        System.out.println(driver.getTitle());
        driver.navigate().forward();
        System.out.println(driver.getTitle());
    }

    //刷新当前页面
    @Test
    public void freshCurrentPage(){
        driver.get(url1);
        driver.navigate().refresh();
    }

    //操作浏览器窗口
    @Test
    public void operateBrowser(){
        Point point = new Point(150,150);
        Dimension dimension = new Dimension(500,500);
        driver.manage().window().setPosition(point);
        driver.manage().window().setSize(dimension);
        System.out.println(driver.manage().window().getPosition());
        System.out.println(driver.manage().window().getSize());
        driver.manage().window().maximize();
        driver.get(url1);
    }

    //获取页面的tilte属性
    @SuppressWarnings("deprecation")
    @Test
    public void getTitle(){
        driver.get(url1);
        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals("xxx网-首页", title);
    }

    //使用title属性识别和操作新弹出的浏览器窗口
    @Test
    public void identifyPopWindowByTitle(){
        driver.get(url1);
        String parentWindowHandle = driver.getWindowHandle();
        WebElement link = driver.findElement(By.linkText("我的账户"));
        link.click();

        Set allWindowsHandles = driver.getWindowHandles();
        String[] handles=new String[allWindowsHandles.size()];
        allWindowsHandles.toArray(handles);

        if(!allWindowsHandles.isEmpty()){
            for(String windowsHandle:handles){
                try{
                    if("xxx- 用户登录".equals(driver.switchTo().window(windowsHandle).getTitle())){
                        driver.findElement(By.id("user_name")).sendKeys("wytest");;
                    }
                }catch(NoSuchWindowException e){
                    e.printStackTrace();
                }
            }
        }
        driver.get(url1);
        System.out.println(driver.getTitle());
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getTitle(), "xxx网-首页");
    }

    //杀掉浏览器进程
    @Test
    public void operateWindowsProcess(){
        driver.get(url1);
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        //WindowsUtils.tryToKillByName("firefox.exe");
        //WindowsUtils.tryToKillByName("iexplore.exe");
        WindowsUtils.killByName("chrome.exe");
    }

    //当前浏览器窗口截屏
    @Test
    public void captureScreenInCurrentWindow(){
        driver.get(url1);
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        /*try{
            FileUtils.copyFile(srcFile, new File("D:\\ScreenShot\\test.png"));
        }catch(Exception e){
            e.printStackTrace();
        }*/
    }

    //拖拽网页元素
    @Test
    public void dragPageElement(){
        driver.get(url1);
        WebElement dragable=driver.findElement(By.id("search_btn"));

        //向下移动10个
        for(int i=0;i<5;i++){
            new Actions(driver).dragAndDropBy(dragable,0,10).build().perform();
        }
        //向右移动10个
        for(int i=0;i<5;i++){
            new Actions(driver).dragAndDropBy(dragable,10,0).build().perform();
        }
    }

    //模拟键盘的操作
    @Test
    public void clickKeys(){
        driver.get(url1);
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL);
        action.keyDown(Keys.SHIFT);
        action.keyDown(Keys.ALT);
        action.keyUp(Keys.CONTROL);
        action.keyUp(Keys.SHIFT);
        action.keyUp(Keys.ALT);
        action.keyDown(Keys.SHIFT).sendKeys("aaaa").perform();
    }

    //模拟鼠标右键
    @Test
    public void rightClickMouse(){
        driver.get(url1);
        Actions action=new Actions(driver);
        action.contextClick(driver.findElement(By.id(""))).perform();
    }

    //在指定元素上方进行鼠标悬浮
    @Test
    public void hoverOnElement(){
        WebElement link1 = driver.findElement(By.id(""));
        WebElement link2 = driver.findElement(By.id(""));

        Actions action = new Actions(driver);
        action.moveToElement(link1).perform();
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        action.moveToElement(link2).perform();
    }

    //在指定元素上进行鼠标单机鼠标左键和释放操作
    @Test
    public void mouseClickAndRelease(){
        WebElement div = driver.findElement(By.id(""));
        Actions action = new Actions(driver);
        action.clickAndHold(div).perform();
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        action.release(div).perform();
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

