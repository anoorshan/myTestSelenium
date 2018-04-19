package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
    // 用于测试执行过程中暂停程序执行的休眠方法
    public static void sleep(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 显示等待页面元素出现的封装方法，参数为表现页面元素的By对象
    public static WebElement waitWebElement(WebDriver driver, final By by, long timeOutInSeconds) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            // 创建一个新的ExpectedCondition接口，实现apply方法
            element = wait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {
                    return d.findElement(by);
                }
            });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not exist until " + timeOutInSeconds);
        }
        return element;
    }

    // watiWebElement这个方法，返回的WebElement对象包括隐藏的，如果是隐藏的，那么在操作的时候，自然而然会报错，所以，我们得把隐藏的去掉，只显示displayed的元素对象
    public static boolean waitElementToBeDisplayed(WebDriver driver, final By by, long timeOutInSeconds) {
        boolean wait = false;
        WebElement element = driver.findElement(by);
        if (element == null) return wait;
        try {
            wait = new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(by).isDisplayed();
                }
            });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not displayed");
        }
        return wait;
    }

    // 等待元素消失
    public static boolean waitElementToBeNoDisplayed(WebDriver driver, final By by, long timeOutInSeconds) {
        boolean wait = false;
        WebElement element = driver.findElement(by);
        if (element == null) return wait;
        try {
            wait = new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return !d.findElement(by).isDisplayed();
                }
            });
        } catch (Exception e) {
            System.out.println("Locator [" + element.toString() + "] is also displayed");
        }
        return wait;
    }
    // 等待元素可点击
    public static void waitElementToBeClickable(WebDriver driver, final By by, long timeOutInSeconds) {
        try {
            new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(by).isEnabled();
                }
            });
        } catch (Exception e) {
            System.out.println(by.toString() + " is not clickable");
        }
    }
    // 等待元素可点击
    public static void waitElementToBeClickable1(WebDriver driver, final By by, long timeOutInSeconds) {
        try {
            new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(by));
          } catch (Exception e) {
            System.out.println(by.toString() + " is not clickable");
        }
    }

}