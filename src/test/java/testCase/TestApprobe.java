package testCase;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.approbe.Approbe_Page_Login;

public class TestApprobe extends TestWebsite {
    private Approbe_Page_Login pageObj;
    private static final long TimeOutInSeconds = 20;
    @BeforeClass(dependsOnMethods = "init")
    public void testInit(){
        pageObj = new Approbe_Page_Login(driver);
    }
    @Test
    public void testFrame(){
        /*
        //api
        driver.switchTo().parentFrame();//切换到父iframe
        driver.switchTo().defaultContent();//跳出iframe，回到顶层
        driver.switchTo().frame("mainView")// 切换到iframe，参数可以是frame的String类型的id/name/index或者 WebElement
        */


        pageObj.goToPage();
        pageObj.setUserName("admin");
        pageObj.setPassword("admin");
        WebDriverWait wait1 = new WebDriverWait(driver, 20);
        try {
            wait1.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return pageObj.getElementById("checkNum").getAttribute("value").length() == 4;
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("您没有输入密码");
        }

        pageObj.submit();

//        WebDriverWait wait = new WebDriverWait(driver, 1);
//        wait.until(ExpectedConditions.elementToBeClickable(By.id("btn_timeRanges")));
        //WaitUtil.waitWebElement(driver,By.id("btn_timeRanges"),TimeOutInSeconds);
       // WaitUtil.waitElementToBeClickable(driver,By.id("btn_timeRanges"),TimeOutInSeconds);
//        pageObj.getElementById("btn_timeRanges").click();
//        pageObj.getElementByCss("#timeRangesDropdown > li:nth-child(6)").click();
        sleep(2000);//等待ajax数据
        pageObj.click(By.id("btn_timeRanges"),TimeOutInSeconds);
        pageObj.click(By.cssSelector("#timeRangesDropdown > li:nth-child(6)"),TimeOutInSeconds);

        sleep(3000);//等待ajax数据，或者采用等待loading的图标消失
        driver.switchTo().frame("mainView");//切换到iframe

       /* WaitUtil.waitElementToBeDisplayed(driver,By.cssSelector("#appsList > tbody > tr:nth-child(2) > td:nth-child(2) > a"),TimeOutInSeconds);
        WaitUtil.waitElementToBeClickable(driver,By.cssSelector("#appsList > tbody > tr:nth-child(2) > td:nth-child(2) > a"),TimeOutInSeconds);
        pageObj.getElementByCss("#appsList > tbody > tr:nth-child(2) > td:nth-child(2) > a").click();*/

        pageObj.click(By.cssSelector("#appsList > tbody > tr:nth-child(2) > td:nth-child(2) > a"),TimeOutInSeconds);
        pageObj.click(By.cssSelector("#applicationId > div.container-fluid > div > div > div.appliction-mainview > div > div > div.dashboard-tabbar-div > ul > li:nth-child(3) > a"),TimeOutInSeconds);

        driver.switchTo().defaultContent();
        pageObj.getElementById("tab4_a").click();
        driver.switchTo().activeElement();
        driver.switchTo().frame("mainView");//必须依次切换
        driver.switchTo().frame("mainTable");

        pageObj.click(By.cssSelector("#tbody-sparkline > tr:nth-child(1)"),TimeOutInSeconds);
        pageObj.click(By.cssSelector("#viewButton"),TimeOutInSeconds);

        driver.switchTo().frame("dashBdtab1");
        //创建一个javascript 执行实例
        JavascriptExecutor je = (JavascriptExecutor) driver;//同步方法，用它执行js代码会阻塞主线程执行，直到JS代码执行完毕；
        je.executeScript("window.scrollTo(0,document.body.scrollHeight)");//控制滚动条的位置scrollTo(x,y);
        sleep(2000);//为了看看控制滚动条的位置有没有生效
        driver.switchTo().parentFrame();//切换到父iframe
        pageObj.getElementById("tab2").click();

        sleep(2000);//为了看看切换效果
        pageObj.getElementById("tab1").click();

        sleep(2000);//为了看看切换效果

        driver.switchTo().defaultContent();
        pageObj.getElementById("tab7_a").click();
        sleep(2000);//为了看看切换效果

    }
    @Test
    public void testAlert(){
        //https://www.cnblogs.com/qiaoyeye/p/5593428.html  对弹出的alert弹框进行了捕获和处理
        /* api
        driver.switchTo().alert().accept(); // 点击确认
        driver.switchTo().alert().dismiss(); //点击取消
        driver.switchTo().alert().getText();// 弹框取值
        driver.switchTo().alert().sendKeys("123");//弹框里填值，//这个方法在chromedriver中不起作用,firefox可以

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("123");
        alert.getText();
        alert.accept();
        alert.dismiss();
        */
        pageObj.goToPage();
        pageObj.checkPageIsReady();

        //confirm
        javascript("confirm('test confirm 1')");
        sleep(3000);
        driver.switchTo().alert().accept(); // 点击确认

        javascript("confirm('test confirm 2')");
        sleep(3000);
        driver.switchTo().alert().dismiss(); // 点击确认

        //alert
        javascript("alert('test alert 1')");
        sleep(3000);
        driver.switchTo().alert().accept();//点击确认

        //prompt
        javascript("prompt('test prompt 1')");
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("chaishanshan");//这个方法在chromedriver中不起作用,firefox可以
        String text = alert.getText();
        System.out.println(text);
        sleep(3000);
        //alert.accept();
    }
    @Test
    public void testWindow(){
        //api
/*        driver.getWindowHandle();//获取当前窗口句柄(返回值是String)
        driver.getWindowHandles();//获取所有窗口句柄(返回值是Set)
        driver.switchTo().window(handle);//切换到新的窗口(返回值是driver)
        driver.getPageSource();//获取页面资源(返回值是String)
        driver.getPageSource().contains("hi");//判断页面资源是否包括
        driver.getCurrentUrl();//获取当前url
        driver.getTitle();//获取当前标题*/

        driver.get("https://github.com/login");
        /*Navigation navigation = driver.navigate();
        navigation.to("http://www.baidu.com");*/
        javascript("window.open('http://www.sogou.com')");//打开一个新的窗口
        javascript("window.location.href='http://www.baidu.com'");//在原来窗口中打开新的页面
        driver.navigate().refresh();//刷新当前页面
        //通过窗口句柄来识别弹出窗口
       String parentWindow = driver.getWindowHandle();//将父窗口句柄保存在parentWindow变量中
       //保存所有窗口的句柄
        String[] handles=new String[driver.getWindowHandles().size()];
        driver.getWindowHandles().toArray(handles);

        //根据句柄
        for(String handle : handles){
            if(!handle.equals(parentWindow)){
                driver.switchTo().window(handle);
                System.out.println(driver.getTitle());
                driver.findElement(By.id("query")).sendKeys("你是新窗口");
            }
        }
        sleep(2000);

        //根据内容
        for(String handle : handles){
            driver.switchTo().window(handle);
            if(driver.getPageSource().contains("百度")){
                System.out.println(driver.getTitle());
                driver.findElement(By.id("kw1")).sendKeys("hello 百度");
               // break;
            }
            if(driver.getPageSource().contains("搜狗")){
                System.out.println(driver.getTitle());
                driver.findElement(By.id("query")).sendKeys("hello 搜狗");
                // break;
            }
        }
    }
    @Test
    public void testScroll(){

        //执行js的api控制滚动条
        /*JavascriptExecutor je1 = (JavascriptExecutor) driver;
        je1.executeScript("document.getElementById('textarea').scrollTop = document.getElementById('textarea').scrollHeight");//JS控制TextArea滚动条自动滚动到最下部
        je1.executeScript("document.getElementById('textarea').scrollLeft = document.getElementById('textarea').scrollWidth");//JS控制TextArea滚动条自动滚动到最右边
        je1.executeScript("arguments[0].scrollIntoView(true);", element);//将元素滚动到可视窗口顶部
        je1.executeScript("arguments[0].scrollIntoView(false);", element);//将元素滚动到可视窗口底部

        je1.executeScript("window.scrollTo(0,0)");//滚动条滚到最顶部
        je1.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");//滚动条滚到最中间
        je1.executeScript("window.scrollTo(0,document.body.scrollHeight)");//滚动条滚到最底部*/

        driver.get("https://github.com/business");
        pageObj.checkPageIsReady();

        WebElement element = driver.findElement(By.cssSelector("body > div.application-main > div.py-7.py-md-8.py-lg-9.mt-6 > div > p > a"));

        //创建一个javascript 执行实例
        JavascriptExecutor je = (JavascriptExecutor) driver;//同步方法，用它执行js代码会阻塞主线程执行，直到JS代码执行完毕；
        sleep(2000);//查看效果
        je.executeScript("arguments[0].scrollIntoView(true);", element);//将元素滚动到可视窗口顶部时，被导航栏盖住了，所以后面就没有办法click
        sleep(2000);//查看效果
        je.executeScript("arguments[0].scrollIntoView(false);", element);//将元素滚动到可视窗口底部
        sleep(2000);//查看效果

        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border = \"5px solid yellow\"",element);//设置边框
        sleep(2000);//查看效果

        je.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        sleep(2000);//查看效果

        je.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
        sleep(2000);//查看效果
        //element.click();
    }
    @Test
    public void testScroll2(){

        driver.get("https://github.com/business");
        pageObj.checkPageIsReady();

        WebElement element = driver.findElement(By.cssSelector("body > div.application-main > div.py-7.py-md-8.py-lg-9.mt-6 > div > p > a"));
        Javascript.scrollIntoViewToTop(driver,element);//将元素滚动到可视窗口顶部时，被导航栏盖住了，所以后面就没有办法click
        sleep(2000);//查看效果
        Javascript.scrollIntoViewToBottom(driver,element);//将元素滚动到可视窗口底部
        sleep(2000);//查看效果

        Javascript.css(driver,element,"border","5px solid yellow");
        sleep(2000);//查看效果

        Javascript.scrollToBottom(driver);//底部
        sleep(2000);//查看效果

        Javascript.scrollToTop(driver);//顶部
        sleep(2000);//查看效果
        //element.click();
    }
    @Test
    public void testScroll3(){

        driver.get("file:///C:/Users/zhkj/Desktop/shanshan/btst_5_medialoot/index.html");
        pageObj.checkPageIsReady();

        WebElement element = driver.findElement(By.cssSelector("div.col-md-8 div.panel-body"));
        Javascript.scrollIntoViewToTop(driver,element);//将元素滚动到可视窗口顶部时，被导航栏盖住了，所以后面就没有办法click
        sleep(2000);//查看效果
       Javascript.scrollToBottom(driver,element);
    }
    @Test
    public void testForm(){
        //http://www.cnblogs.com/tobecrazy/p/4817946.html
        //driver.get("file:///C:/Users/zhkj/Desktop/shanshan/btst_5_medialoot/forms.html");
        driver.get("http://www.sogou.com");
        pageObj.checkPageIsReady();
        WebElement element = driver.findElement(By.id("query"));

        Javascript.css(driver,element,"color","#FF0000");
        Javascript.css(driver,element,"border","solid 1px orange");
        System.out.println(Javascript.css(driver,element,"color"));

        Javascript.val(driver,element,"hello world");
        System.out.println(Javascript.val(driver,element));

        Javascript.attr(driver,element,"height","100px");
        System.out.println(Javascript.attr(driver,element,"height"));
        sleep(2000);
        Javascript.prop(driver,element,"disabled",true);//禁用按钮
        System.out.println(Javascript.prop(driver,element,"disabled"));//获取禁用的状态
        sleep(5000);
        Javascript.prop(driver,element,"disabled",false);//取消禁用

    }
    @Test
    public void testWaite() {
        driver.get("https://www.baidu.com");
        //标题是不是“百度一下，你就知道”
        new WebDriverWait(driver,5).until(ExpectedConditions.titleIs("百度一下，你就知道"));
        //标题是不是包含“百度一下”
        new WebDriverWait(driver,5).until(ExpectedConditions.titleContains("百度一下"));
        //判断该元素是否被加载在DOM中，并不代表该元素一定可见
        new WebDriverWait(driver,5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='kw']")));
        //判断元素(定位后)是否可见
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='kw']"))));
        //判断元素是否可见（非隐藏，并且元素的宽和高都不等以0）
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='kw']")));
        //只要存在一个就是true
        ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='kw']"));
        //元素中的text是否包含预期的字符串
        ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='kw']"), "百度一下");
        //元素的value属性中是否包含预期的字符串
        ExpectedConditions.textToBePresentInElementValue(By.xpath("//*[@id='kw']"), "***");
        //判断该表单是否可以切过去，可以就切过去并返回true，否则放回false
        ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("**"));
        //判断某个元素是否不存在于DOM或不可见
        ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='kw']"));
        //判断元素是否可以点击
        ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='kw']"));
        //等到一个元素从DOM中移除
        ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id='kw']")));
        //判断某个元素是否被选中，一般用在下拉列表
        ExpectedConditions.elementToBeSelected(By.xpath("//*[@id='kw']"));
        //判断某个元素的选中状态是否符合预期
        ExpectedConditions.elementSelectionStateToBe(By.xpath("//*[@id='kw']"), true);
        //判断某个元素(已定位)的选中状态是否符合预期
        ExpectedConditions.elementSelectionStateToBe(driver.findElement(By.xpath("//*[@id='kw']")), false);
        //判断页面中是否存在alert
        new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        //--------------------自定义判断条件-----------------------------
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return !driver.findElement(By.xpath("//*[@id='kw']")).getAttribute("class").contains("x-form-invalid-field");
            }
        });

        //自定义异常
        WebDriverWait wait2 = new WebDriverWait(driver,5);

        WebElement hello = wait2.until(

                new ExpectedCondition<WebElement>(){

                    public WebElement apply(WebDriver driver){

                        return driver.findElement(By.cssSelector("#username"));

                    }

                }

        );

    }
    @Test
    public void testJs(){
        //http://www.cnblogs.com/tobecrazy/p/4817946.html
      //https://www.cnblogs.com/Ming8006/p/5727542.html#c4.1(重点看这个)


        driver.get("http://www.baidu.com");
        sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // 用js弹出alert
        js.executeScript("alert('Test Case Execution Is started Now..');");
        driver.switchTo().alert().accept();

        // 用js判断页面加载完毕，返回complete
        System.out.println("readyState: "
                + js.executeScript("return document.readyState").toString());
        // 用js得到页面的title
        String title = (String) js.executeScript("return document.title");
        System.out.println("current page title get by js: " + title);
        // 用js得到页面的domain name
        System.out.println("current page domain name get by js: "
                + js.executeScript("return document.domain"));
        // 用js操作页面元素，如高亮显示某个元素
        WebElement element = driver.findElement(By.id("kw"));
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: orange; border: 4px solid orange;");
        sleep(2000);
        js.executeScript("arguments[0].style.color='red'",element);

    }
    @Test
    public void setWindowTest(){
        driver.get("https://www.baidu.com");
        sleep(2000);
        // 设置窗口的 宽度为：800，高度为600
        Dimension d = new Dimension(800, 600);
        driver.manage().window().setSize(d);
        sleep(2000);

        // 设置窗口最大化
        driver.manage().window().maximize();
        sleep(2000);

        // 设置窗口出现在屏幕上的坐标
        Point p = new Point(500, 300);
        // 执行设置
        driver.manage().window().setPosition(p);
        sleep(2000);
    }
}

