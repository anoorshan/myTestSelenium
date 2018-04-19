package testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.Github_Page_SignIn;

public class TestGithub extends TestWebsite {
    String baseUrl = "https://github.com";
    @Test
    public void goHomePage(){
        driver.get(baseUrl+"/");
    }
    @Test(dependsOnMethods = "goHomePage")
    @Parameters({"userName","password"})
    public void signIn(String userName,String password){
        Github_Page_SignIn pageObj = new Github_Page_SignIn(driver);
        pageObj.goToPage();
        pageObj.setUserName(userName);
        pageObj.setPassword(password);
        pageObj.submit();
        //测试断言
        Assert.assertTrue(pageObj.isSucessSignIn(userName),"login failed !");
    }
    @Test(dependsOnMethods = "signIn")
    public void goYourFilePage(){
        //查看个人主页
        // driver.findElement(By.cssSelector("ul#user-links>li:nth-child(3)")).findElement(By.linkText("Your profile")).click();
        //用xpath的方法
        driver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/div[2]/ul/li[3]/details/ul/li[3]/a")).click();

    }
    @Test(dependsOnMethods = "goYourFilePage")
    public void viewRepositories(){
        //查看仓库
        driver.findElement(By.cssSelector("nav.UnderlineNav-body>a:nth-child(2)")).click();
    }
    @Test(dependsOnMethods = "viewRepositories")
    public void signOut(){
        WebElement userInfEle = driver.findElement(By.cssSelector("ul#user-links>li:nth-child(3)"));
        userInfEle.click();
        userInfEle.findElement(By.cssSelector("form.logout-form>button")).click();
        WebElement homeEle = driver.findElement(By.className("HeaderNavlink"));
        //断言
        //Assert.assertTrue(homeEle.isDisplayed(),"signOut failed !");

    }
}
