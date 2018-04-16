package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SeleniumByBrowsers {
    private WebDriver driver;
    private String baseUrl = "http://www.sogou.com";
    @Parameters("browser")
    @BeforeTest
    public void launchApp(String browser)
    {

        if (browser.equalsIgnoreCase("firefox"))
        {
            System.out.println(" Executing on FireFox");
            //设置浏览器安装路径
            System.setProperty("webdriver.firefox.bin", "E:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            //设置webdriver的路径
            System.setProperty("webdriver.gecko.driver", "E:\\Program Files (x86)\\webdriver\\Mozilla Firefox\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("chrome"))
        {
            System.out.println(" Executing on CHROME");
            System.setProperty("webdriver.chrome.driver", "E:\\Program Files (x86)\\webdriver\\Chrome\\chromedriver.exe");
            driver = new ChromeDriver();
         }
        else if (browser.equalsIgnoreCase("ie"))
        {
            System.setProperty("webdriver.ie.driver", "E:\\Program Files (x86)\\webdriver\\IE\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        else
        {
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }
        //打开搜狗首页
        driver.get(baseUrl+"/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void test() {
        //在搜索框输入XX
        driver.findElement(By.id("query")).sendKeys("hello world");
        //单击搜索按钮
        driver.findElement(By.id("stb")).click();
        System.out.println("Page Title is " + driver.getTitle());
        driver.close();
        //driver.quit();
    }
    @Test(enabled = false)
    public void testChrome() {
        System.setProperty("webdriver.chrome.driver", "E:\\Program Files (x86)\\webdriver\\Chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();//窗口最大化
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);//设置隐式等待,下面设置5秒，根据页面加载速度决定
        String baseUrl="http://www.sogou.com";
        //打开搜狗首页
        driver.get(baseUrl+"/");
        //在搜索框输入XX
        driver.findElement(By.id("query")).sendKeys("hello world");
        //单击搜索按钮
        driver.findElement(By.id("stb")).click();
        System.out.println("Page Title is " + driver.getTitle());
        //Assert.assertEquals("百度一下，你就知道",driver.getTitle());
        driver.close();
        driver.quit();
    }
    @Test(enabled = false)
    public void testFirefox() {
        //设置浏览器安装路径
        System.setProperty("webdriver.firefox.bin", "E:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        //设置webdriver的路径
        System.setProperty("webdriver.gecko.driver", "E:\\Program Files (x86)\\webdriver\\Mozilla Firefox\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        String baseUrl = "http://www.sogou.com";
        //打开搜狗首页
        driver.get(baseUrl + "/");
        //在搜索框输入hello world
        driver.findElement(By.id("query")).sendKeys("hello world");
        //单击搜索按钮
        driver.findElement(By.id("stb")).click();
        //driver.close();
        driver.quit();

    }
    @Test(enabled = false)
    public void testIE(){
        System.setProperty("webdriver.ie.driver", "E:\\Program Files (x86)\\webdriver\\IE\\IEDriverServer.exe");
        System.setProperty("sun.net.client.defaultConnectTimeout", "95000");
        System.setProperty("sun.net.client.defaultReadTimeout", "95000");

        WebDriver driver = new InternetExplorerDriver();
        driver.get("https://www.baidu.com/");

        System.out.println("Page Title is " + driver.getTitle());
        Assert.assertEquals("百度一下，你就知道",driver.getTitle());
        driver.close();
        // driver.quit();
    }
}
