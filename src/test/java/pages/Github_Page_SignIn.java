package pages;

import org.openqa.selenium.WebDriver;

public class Github_Page_SignIn extends Page {
    private static String baseUrl = "https://github.com/login";
    public Github_Page_SignIn(WebDriver driver) {
        super(driver, baseUrl);
    }
    public void setUserName(String userName){
        setValue("login_field",SelectorType.ID,userName);
    }
    public void setPassword(String pwd){
        setValue("password",SelectorType.ID,pwd);
    }
    public  void submit(){
        this.getElementByName("commit").click();
    }
    public boolean isCurrentPage(){
        return isElementExist("login_field",SelectorType.ID);
    }
    public boolean isSucessSignIn(String userName){
        try {
            Thread.sleep(10000);
            //想要点击一个隐藏的元素，必须先把它显示出来
            this.getElementByCss("ul#user-links>li:nth-child(3)").click();
            return userName.equals(this.getElementByCss("ul.dropdown-menu>li:first-child>strong").getText());
        }
        catch (org.openqa.selenium.NoSuchElementException ex){
            return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
