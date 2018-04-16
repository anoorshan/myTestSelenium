package testCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public abstract class TestWebsite {
    public WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void init(String browser){
        if (browser.equalsIgnoreCase("firefox")){
            System.out.println(" Executing on FireFox");
            //设置浏览器安装路径
            System.setProperty("webdriver.firefox.bin", "E:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            //设置webdriver的路径
            System.setProperty("webdriver.gecko.driver", "E:\\Program Files (x86)\\webdriver\\Mozilla Firefox\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browser.equalsIgnoreCase("ie")){
            System.out.println(" Executing on IE");
            System.setProperty("webdriver.ie.driver", "E:\\Program Files (x86)\\webdriver\\IE\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        else{
            System.out.println(" Executing on CHROME");
            System.setProperty("webdriver.chrome.driver", "E:\\Program Files (x86)\\webdriver\\Chrome\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();//窗口最大化
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);//设置隐式等待,下面设置10秒，根据页面加载速度决定
    }
    @AfterClass
    public void close(){
        driver.close();//Close the current window, quiting the browser if it is the last window currently open.
        driver.quit();//Quit this dirver, closing every associated windows;
    }

}
