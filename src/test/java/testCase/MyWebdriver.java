package testCase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MyWebdriver {

    private WebDriver driver;
    private String value = "";
    private boolean flag = true;

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }

    /**
     * 执行js方法
     *
     * @param js
     */
    public boolean excuteJS(String js) {
        if (flag) {
            try {
                ((JavascriptExecutor) driver).executeScript(js);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据id定位元素并输入内容
     *
     * @param id
     * @param value
     */
    public boolean inputById(String id, String value) {
        if (flag) {
            try {
                driver.findElement(By.id(id)).sendKeys(value);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素并输入内容
     *
     * @param xpath
     * @param value
     */
    public boolean inputByXpath(String xpath, String value) {
        if (flag) {
            try {
                driver.findElement(By.xpath(xpath)).sendKeys(value);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据css定位元素并输入内容
     *
     * @param css
     * @param value
     */
    public boolean inputByCss(String css, String value) {
        if (flag) {
            try {
                driver.findElement(By.cssSelector(css)).sendKeys(value);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    public boolean clickBindCard(String cardNo) {
        flag = false;
        String value = "";
        int i = 0;
        if (!driver.findElement(By.id("r_x")).isDisplayed()) {
            for (i = 0; i < 5; i++) {
                value = driver.findElement(By.id("t_b_" + i)).getText();
                if (value.contains(cardNo)) {
                    flag = true;
                    System.out.println("t_b_" + i);
                    driver.findElement(By.id("sel_img_" + i)).click();
                }
            }
        } else {
            while (driver.findElement(By.id("r_x")).isDisplayed()) {
                System.out.println("i>>" + i);
                if (i > 0 && i % 5 == 0) {
                    driver.findElement(By.id("r_x")).click();
                }
                value = driver.findElement(By.id("t_b_" + i)).getText();
                System.out.println(value + ">>" + value.contains(cardNo));
                if (value.contains(cardNo)) {
                    flag = true;
                    System.out.println("t_b_" + i);
                    driver.findElement(By.id("sel_img_" + i)).click();
                    break;
                }
                i++;
            }
        }
        return flag;
    }

    /**
     * 根据id定位元素并点击
     *
     * @param id
     */
    public boolean clickById(String id) {
        if (flag) {
            try {
                if (id.startsWith("sel_img_") && !id.contains("sel_img_ct")) {
                    int i = Integer.parseInt(id.substring(8, id.length()));
                    int j = 4;
                    while (i > 3) {
                        driver.findElement(By.id("sel_img_" + j)).click();
                        i -= 4;
                        j += 4;
                    }
                }
                driver.findElement(By.id(id)).click();
                System.out.println("click element by id>>" + id);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素并点击
     *
     * @param xpath
     */
    public boolean clickByXpath(String xpath) {
        if (flag) {
            try {
                driver.findElement(By.xpath(xpath)).click();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据css定位元素并点击
     *
     * @param css
     */
    public boolean clickByCss(String css) {
        if (flag) {
            try {
                driver.findElement(By.cssSelector(css)).click();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据id定位元素并连续点击
     *
     * @param id
     */
    public boolean clickById(String id, int count) {
        if (flag) {
            try {
                if (id.startsWith("sel_img_") && !id.contains("sel_img_ct")) {
                    int i = Integer.parseInt(id.substring(8, id.length()));
                    int j = 4;
                    while (i > 3) {
                        driver.findElement(By.id("sel_img_" + j)).click();
                        i -= 4;
                        j += 4;
                    }
                }
                for (int i = 0; i < count; i++) {
                    driver.findElement(By.id(id)).click();
                }
                System.out.println("click element by id>>" + id);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素并连续点击
     *
     * @param xpath
     */
    public boolean clickByXpath(String xpath, int count) {
        if (flag) {
            try {
                for (int i = 0; i < count; i++) {
                    driver.findElement(By.xpath(xpath)).click();
                }
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据css定位元素并连续点击
     *
     * @param css
     */
    public boolean clickByCss(String css, int count) {
        if (flag) {
            try {
                for (int i = 0; i < count; i++) {
                    driver.findElement(By.cssSelector(css)).click();
                }
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素获取值
     *
     * @param xpath
     * @return
     */
    public String getValueByXpath(String xpath) {
        if (flag) {
            value = driver.findElement(By.xpath(xpath)).getText();
            System.out.println();
            return value;
        } else {
            System.out.println("flag is false, function is not excuted");
            return null;
        }
    }

    /**
     * 根据id定位元素获取值
     *
     * @param id
     * @return
     */
    public String getValueById(String id) {
        if (flag) {
            value = driver.findElement(By.id(id)).getText();
            System.out.println(value);
            return value;
        } else {
            System.out.println("flag is false, function is not excuted");
            return null;
        }
    }

    /**
     * 根据css定位元素获取值
     *
     * @param css
     * @return
     */
    public String getValueByCss(String css) {
        if (flag) {
            value = driver.findElement(By.cssSelector(css)).getText();
            System.out.println(value);
            return value;
        } else {
            System.out.println("flag is false, function is not excuted");
            return null;
        }
    }

    /**
     * 根据id定位元素并清空值
     *
     * @param id
     */
    public boolean clearInputValueById(String id) {
        if (flag) {
            try {
                driver.findElement(By.id(id)).clear();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素并清空值
     *
     * @param xpath
     */
    public boolean clearInputValueByXpath(String xpath) {
        if (flag) {
            try {
                driver.findElement(By.xpath(xpath)).clear();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据css定位元素并清空值
     *
     * @param css
     */
    public boolean clearInputValueByCss(String css) {
        if (flag) {
            try {
                driver.findElement(By.cssSelector(css)).clear();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 获取网页的title值
     *
     * @return
     */
    public String getTitle() {
        if (flag) {
            return driver.getTitle();
        } else {
            System.out.println("flag is false, function is not excuted");
            return null;
        }
    }

    /**
     * 切换到frame框
     *
     * @param frameName
     */
    public boolean switchToFrame(String frameName) {
        if (flag) {
            try {
                driver.switchTo().frame(frameName);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据id定位元素并获取元素的显示状态
     *
     * @param id
     * @return boolean
     */
    public boolean getDisplayStatById(String id) {
        if (flag) {
            return driver.findElement(By.id(id)).isDisplayed();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素并获取元素的显示状态
     *
     * @param xpath
     * @return
     */
    public boolean getDisplayStatByXpath(String xpath) {
        if (flag) {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据css定位元素并获取元素的显示状态
     *
     * @param css
     * @return
     */
    public boolean getDisplayStatByCss(String css) {
        if (flag) {
            return driver.findElement(By.cssSelector(css)).isDisplayed();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据id定位元素并获取元素的可写状态
     *
     * @param id
     * @return
     */
    public boolean getEnableStatById(String id) {
        if (flag) {
            return driver.findElement(By.id(id)).isEnabled();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素并获取元素的可写状态
     *
     * @param xpath
     * @return
     */
    public boolean getEnableStatByXpath(String xpath) {
        if (flag) {
            return driver.findElement(By.xpath(xpath)).isEnabled();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据css定位元素并获取元素的可写状态
     *
     * @param css
     * @return
     */
    public boolean getEnableStatByCss(String css) {
        if (flag) {
            return driver.findElement(By.cssSelector(css)).isEnabled();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }

    }

    /**
     * 根据id定位元素并获取元素的选中状态
     *
     * @param id
     * @return
     */
    public boolean getSelectStatById(String id) {
        if (flag) {
            return driver.findElement(By.id(id)).isSelected();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据xpath定位元素并获取元素的选中状态
     *
     * @param xpath
     * @return
     */
    public boolean getSelectStatByXpath(String xpath) {
        if (flag) {
            return driver.findElement(By.xpath(xpath)).isSelected();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 根据css定位元素并获取元素的选中状态
     *
     * @param css
     * @return
     */
    public boolean getSelectStatByCss(String css) {
        if (flag) {
            return driver.findElement(By.cssSelector(css)).isSelected();
        } else {
            System.out.println("flag is false, function is not excuted");
            return false;
        }
    }

    /**
     * 获取当前焦点所在页面元素的属性值(name,value,id,src等等)
     *
     * @param attribute
     * @return
     */
    public String getFocusAttributeValue(String attribute) {
        try {
            Thread.sleep(333);
        } catch (Exception e) {
            e.printStackTrace();
        }
        value = driver.switchTo().activeElement().getAttribute(attribute);
        System.out.println("The focus Element's " + attribute
                + "attribute value is>>" + value);
        return value;
    }

    /**
     * 打开网页链接
     *
     * @param pageUrl
     */
    public boolean openPage(String pageUrl) {
        try {
            driver.get(pageUrl);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 进入测试，打开浏览器，输入网址，打开网页
     *
     * @param remoteUrl
     *            远程服务器地址
     * @param pageUrl
     *            测试页面地址
     */
    public boolean startTest(String remoteUrl, String pageUrl) {
        try {
            try {
                driver = new RemoteWebDriver(new URL(remoteUrl),
                        DesiredCapabilities.firefox());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            driver.get(pageUrl);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 进入测试，打开浏览器，输入网址，打开网页
     *
     * @param explore
     *            调用的浏览器，需要启动不同的server，如：firefox，需要运行selenium-server-standalone-
     *            2.33.0.jar。IE，则需运行IEDriverServer.exe
     *
     * @param remoteUrl
     *            远程服务器地址
     * @param pageUrl
     *            测试页面地址
     */
    public boolean startTest(String explore, String remoteUrl, String pageUrl) {
        try {
            try {
                if ("f".equals(explore)) {
                    System.out.println("firefox");
                    driver = new RemoteWebDriver(new URL(remoteUrl),
                            DesiredCapabilities.firefox());
                } else if ("ie".equals(explore)) {
                    System.out.println("internet explorer");
                    DesiredCapabilities cap = DesiredCapabilities
                            .internetExplorer();
                    driver = new RemoteWebDriver(new URL(remoteUrl), cap);
                } else {
                    System.out.println("firefox");
                    driver = new RemoteWebDriver(new URL(remoteUrl),
                            DesiredCapabilities.firefox());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.get(pageUrl);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 设置定位页面元素的超时时间
     *
     * @param second
     * @return
     */
    public boolean setTimeOut(int second) {
        try {
            driver.manage().timeouts().implicitlyWait(second, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 结束测试，关闭浏览器
     */
    public boolean endTest() {
        try {
            driver.quit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 休息间隔，单位毫秒
     *
     * @param millis
     * @return
     */
    public boolean sleep(Long millis) {
        try {
            Thread.sleep(millis);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 根据id等待对应的页面元素出现
     *
     * @param id
     * @return
     */
    public boolean waitForElementById(String id) {
        try {
            driver.findElement(By.id(id));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 根据css等待对应的页面元素出现
     *
     * @param css
     * @return
     */
    public boolean waitForElementByCss(String css) {
        try {
            driver.findElement(By.cssSelector(css));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 根据xpath等待对应的页面元素出现
     *
     * @param xpath
     * @return
     */
    public boolean waitForElementByXpath(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}

