package pages;

import org.openqa.selenium.WebDriver;

public class Email163_Page_SignUp extends Page{
    private static String baseUrl = "http://reg.email.163.com/unireg/call.do?cmd=register.entrance";
    public Email163_Page_SignUp(WebDriver driver) {
        super(driver, baseUrl);
    }
    public void setUserName(String userName){
        this.setValue("nameIpt",SelectorType.ID,userName);
    }
    public void setEmailDomain(String emailDomain){
        this.selectByValue("mainDomainSelect",SelectorType.ID,emailDomain);
    }
    public void setPassword(String pwd){
        this.setValue("mainPwdIpt",SelectorType.ID,pwd);
    }
    public void setCfmPassword(String pwd){
        this.setValue("mainCfmPwdIpt",SelectorType.ID,pwd);
    }
    public void setMobileIpt(String mainMobileIpt){
        this.setValue("mainMobileIpt",SelectorType.ID,mainMobileIpt);
    }
    public void setSelectedAccept(boolean isSelected){
        this.setSelected("mainAcceptIpt",SelectorType.ID,isSelected);
    }
    public  void submit(){
        this.getElementById("mainRegA").click();
    }
    public boolean isCurrentPage(){
        return isElementExist("mainRegA",SelectorType.ID);
    }
}
