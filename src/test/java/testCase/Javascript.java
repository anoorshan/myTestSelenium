package testCase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Javascript {
    public static void exec(WebDriver driver,WebElement element,String script){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script,element);//同步方法，用它执行js代码会阻塞主线程执行，直到JS代码执行完毕；
    }
    public static void exec(WebDriver driver,String script){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);//同步方法，用它执行js代码会阻塞主线程执行，直到JS代码执行完毕；
    }
    //文本框
    public static WebElement val(WebDriver driver,WebElement element, String value){
        String script = "arguments[0].value='" + value +"';";
        exec(driver,element,script);
        return element;
    }
    public static String val(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "var result=arguments[0].value;return result;";
        String result = (String) js.executeScript(script, element);
        return result;
    }

    //style 样式表
    public static WebElement css(WebDriver driver,WebElement element, String attr,String value){
        String script = "arguments[0].style." + attr + "='" + value +"';";
        exec(driver,element,script);
        return element;
    }
    public static String css(WebDriver driver,WebElement element, String attr){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return arguments[0].style." + attr;
        String result = (String) js.executeScript(script, element);
        return result;
    }
    //属性值
    public static WebElement attr(WebDriver driver,WebElement element, String attr,String value){
        String script = "arguments[0].setAttribute('" + attr + "','" + value +"');";
        exec(driver,element,script);
        return element;
    }
    public static String attr(WebDriver driver,WebElement element, String attr){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return arguments[0].getAttribute('" + attr +"');";
        String result = (String) js.executeScript(script, element);
        return result;
    }
    //boolean 属性值
    public static WebElement prop(WebDriver driver,WebElement element, String attr,boolean value){
        String script;
        if(value){
            script = "if(!arguments[0].hasAttribute('"+ attr +"')) arguments[0].setAttribute('" + attr + "','" + attr +"');";
        }
        else{
            script = "if(arguments[0].hasAttribute('"+ attr +"')) arguments[0].removeAttribute('" + attr  +"');";
        }
        exec(driver,element,script);
        return element;
    }
    public static boolean prop(WebDriver driver,WebElement element, String attr){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return arguments[0].hasAttribute('"+ attr +"');";
        boolean result = (boolean)js.executeScript(script,element);
        return result;
    }
    /*滚动条*/
    public static void scrollTo(WebDriver driver,int x,int y){
        exec(driver,"window.scrollTo("+x + "," + y +")");
    }
    public static void scrollToTop(WebDriver driver){
        exec(driver,"window.scrollTo(0,0)");
    }
    public static void scrollToBottom(WebDriver driver){
        exec(driver,"window.scrollTo(0,document.body.scrollHeight);");
    }

    public static void scrollTo(WebDriver driver,WebElement element,int x,int y){
        exec(driver,element,"arguments[0].scrollTop=" + x +";arguments[0].scrollLeft=" + y +";");
    }
    public static void scrollToTop(WebDriver driver,WebElement element){
        exec(driver,element,"arguments[0].scrollTop=0;");
    }
    public static void scrollToBottom(WebDriver driver,WebElement element){
        exec(driver,element,"arguments[0].scrollTop = arguments[0].scrollHeight;");
    }
    public static void scrollIntoViewToTop(WebDriver driver,WebElement element){
        exec(driver,element,"arguments[0].scrollIntoView(true);");
    }
    public static void scrollIntoViewToBottom(WebDriver driver,WebElement element){
        exec(driver,element,"arguments[0].scrollIntoView(false);");
    }
    //检查文档的状态
    public void checkPageIsReady(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 30; i++) {
            if ("complete".equals(js.executeScript("return document.readyState").toString())) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
