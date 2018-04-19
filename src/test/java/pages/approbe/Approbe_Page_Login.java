package pages.approbe;

import org.openqa.selenium.WebDriver;
import pages.Page;

public class Approbe_Page_Login extends Page {
    private static String baseUrl = "http://139.196.102.196:8080/approbe-control/user/enter.do";
    public Approbe_Page_Login(WebDriver driver) {
        super(driver, baseUrl);
    }
    public Approbe_Page_Login setUserName(String userName){
        setValue("uname",SelectorType.ID,userName);
        return this;
    }
    public Approbe_Page_Login setPassword(String pwd){
        setValue("pwd",SelectorType.ID,pwd);
        return this;
    }
    public  void submit(){
        sleep(10000);
        this.getElementById("submit_login").click();
    }
}
