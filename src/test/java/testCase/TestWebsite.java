package testCase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public abstract class TestWebsite {
    public WebDriver driver;
    private JavascriptExecutor javascript;
    @BeforeClass
    public void init(){
        System.out.println(" Executing on CHROME");
        System.setProperty("webdriver.chrome.driver", "E:\\Program Files (x86)\\webdriver\\Chrome\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();//窗口最大化
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);//设置隐式等待,下面设置10秒，根据页面加载速度决定

        javascript = (JavascriptExecutor) driver;
    }
    @AfterClass
    public void close(){
        //driver.close();//Close the current window, quiting the browser if it is the last window currently open.
       // driver.quit();//Quit this dirver, closing every associated windows;
    }

    //静态等待
    public static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void javascript(String script){
/*        executeScript方法如果有返回值，有以下几种情况：
        1、如果返回一个页面元素（document element), 这个方法就会返回一个WebElement
        2、如果返回浮点数字，这个方法就返回一个double类型的数字
        3、返回非浮点数字，方法返回Long类型数字
        4、返回boolean类型，方法返回Boolean类型
        5、如果返回一个数组，方法会返回一个List<Object>
        6、其他情况，返回一个字符串
        7、如果没有返回值，此方法就会返回null*/

        javascript.executeScript(script);//同步方法，用它执行js代码会阻塞主线程执行，直到JS代码执行完毕；
        //javascript.executeAsyncScript(script);//异步的方法
    }
}
