package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import testCase.WaitUtil;

import java.util.List;

public abstract class Page {
    protected WebDriver driver;
    protected String baseUrl;
    protected enum SelectorType { ID,XPATH,CSS,NAME,LINKTEXT,PARTIALLINKTEXT}

    public Page(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }
    /*封装查找元素的方法*/
    public WebElement getElementById(String eleId){
        return this.driver.findElement(By.id(eleId));
    }
    public WebElement getElementById(String eleId,long timeOutInSeconds){
        By by = By.id(eleId);
        if (!(WaitUtil.waitElementToBeDisplayed(driver,by, timeOutInSeconds))) {
            return null;
        }
        return this.driver.findElement(by);
    }
    public WebElement getElementByXpath(String xPath){
        return this.driver.findElement(By.xpath(xPath));
    }
    public WebElement getElementByCss(String cssExp){
        return this.driver.findElement(By.cssSelector(cssExp));
    }
    public WebElement getElementByCss(String cssExp,long timeOutInSeconds){
        By by = By.cssSelector(cssExp);
        if (!(WaitUtil.waitElementToBeDisplayed(driver,by, timeOutInSeconds))) {
            return null;
        }
        return this.driver.findElement(by);
    }
    public WebElement getElementByName(String name){
        return  this.driver.findElement(By.name(name));
    }
    public WebElement getElementByLinkText(String linkText){
        return this.driver.findElement(By.linkText(linkText));
    }
    public WebElement getElementByPartialLinkText(String linkText){
        return this.driver.findElement(By.partialLinkText(linkText));
    }
    public List<WebElement> getElementsByCss(String cssExp){
        return this.driver.findElements(By.cssSelector(cssExp));
    }
    public WebElement getElement(String exp, SelectorType type ){
       switch (type){
           case ID:
               return getElementById(exp);
           case XPATH:
               return getElementByCss(exp);
           case NAME:
               return getElementByName(exp);
           case CSS:
               return getElementByCss(exp);
           case LINKTEXT:
               return getElementByLinkText(exp);
           case PARTIALLINKTEXT:
               return getElementByPartialLinkText(exp);
           default:
                return getElementByCss(exp);
       }
    }
    /*元素交互*/
    // 文本框
    public WebElement setValue(String exp, SelectorType type, String text){
        WebElement element = getElement(exp,type);
        element.clear();
        element.sendKeys(text);
        return element;
    }
    public WebElement setValue(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
        return element;
    }
    public String getValue(WebElement element){
        return  element.getAttribute("value");
    }
    public String getValue(String exp,SelectorType type){
        return  getElement(exp,type).getAttribute("value");
    }
    //选择
    public WebElement setSelected(String exp, SelectorType type, boolean isSelected){
        WebElement element = getElement(exp,type);
        if(isSelected != element.isSelected()) element.click();
        return element;
    }
    public WebElement setSelected(WebElement element, boolean isSelected){
        if(!isSelected !=  element.isSelected()) element.click();
        return element;
    }

    //下拉列表
    public Select selectByValue(String exp, SelectorType type, String value){
        Select element = new Select(getElement(exp,type));
        element.selectByValue(value);
        return element;
    }
    public Select selectByValue(WebElement element, String value){
        Select selectObj = new Select(element);
        selectObj.selectByValue(value);
        return selectObj;
    }
    public void goToPage(){
        driver.get(baseUrl);
    }
    public boolean isElementExist(String exp,SelectorType type) {
        try {
            getElement(exp,type);
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException ex){
            return false;
        }
    }
    /**
     * 每隔一秒check一下页面加载是否完成，check次数是25
     */
    public void checkPageIsReady() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 25; i++) {
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
    //静态等待
    public void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*鼠标事件交互封装*/
    // 单击
    public void click(By by,long timeOutInSeconds){
        WebElement element = WaitUtil.waitWebElement(driver,by,timeOutInSeconds);
        WaitUtil.waitElementToBeClickable(driver,by,timeOutInSeconds);
        new Actions(driver).click(element).perform();

    }
    public void click(WebElement element){
        new Actions(driver).click(element).perform();

    }
    // 双击
    public void dbClick(WebElement element){
        new Actions(driver).doubleClick(element).perform();

    }
    // 悬停 到更多按钮实现
    public void hover(WebElement element){
        new Actions(driver).moveToElement(element).perform();

    }
     // 右击
    public void contextClick(WebElement element){
        new Actions(driver).contextClick(element).perform();

    }
    // 拖动
    public void drag(WebElement begin,WebElement end){
        new Actions(driver).dragAndDrop(begin, end).perform();
    }
    // 拖动
    public void drag(WebElement element,int xOffset, int yOffset){
        new Actions(driver).dragAndDropBy(element,xOffset,yOffset).perform();
    }


}
