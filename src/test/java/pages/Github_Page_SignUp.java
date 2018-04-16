package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Github_Page_SignUp  extends Page{

    private static String baseUrl = "https://github.com/join";
    public Github_Page_SignUp(WebDriver driver) {
        super(driver, baseUrl);
    }
    public void setUserName(String userName){
        //this.getElementById("user_login").sendKeys(userName);
        this.setValue("user_login",SelectorType.ID,userName);
    }
    public void setEmail(String email){
        //this.getElementById("user_email").sendKeys(email);
        this.setValue("user_email",SelectorType.ID,email);
    }
    public void setPassword(String pwd){
        //this.getElementById("user_password").sendKeys(pwd);
        this.setValue("user_password",SelectorType.ID,pwd);
    }
    public  void submit(){
        this.getElementById("signup_button").click();
    }
    public  String getUserNameErr(){
        WebElement ele = this.getElementByCss("#signup-form > auto-check:nth-child(5) > dl > dd.error");
        return ele.isDisplayed()?ele.getText():"";
    }
    public String getEmailErr(){
        WebElement ele = this.getElementByCss("#signup-form > auto-check:nth-child(6) > dl > dd.error");
        return  ele.isDisplayed()?ele.getText():"";
    }
    public String getPasswordErr(){
        WebElement ele = this.getElementByCss("#signup-form > auto-check:nth-child(7) > dl > dd.error");
        return  ele.isDisplayed()?ele.getText():"";
    }
    public boolean isCurrentPage(){
        return isElementExist("signup_button",SelectorType.ID);
    }
}