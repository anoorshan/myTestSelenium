package testCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestGithubPages extends TestWebsite {
    String baseUrl = "https://github.com";
    @Test
    public void goHomePage(){
        driver.get(baseUrl+"/");
    }
    @Test(dependsOnMethods = "goHomePage")
    @Parameters({"userName","password"})
    public void signIn(String userName,String password){
        //点击登陆超链接
        //WebElement loginUrlEle = ((FirefoxDriver) driver).findElementByLinkText("Sign in");//fireFox
        WebElement loginUrlEle = ((ChromeDriver) driver).findElementByLinkText("Sign in");//chrome
        loginUrlEle.click();
        //填写用户名
        WebElement userNameEle =  driver.findElement(By.id("login_field"));
        userNameEle.clear();
        userNameEle.sendKeys(userName);
        //填写密码
        WebElement pwdEle = driver.findElement(By.id("password"));
        pwdEle.clear();
        pwdEle.sendKeys(password);
        //登陆提交
        WebElement submitBtn = driver.findElement(By.cssSelector("#login input[name='commit']"));
        submitBtn.click();

        //测试断言
        //想要点击一个隐藏的元素，必须先把它显示出来
        WebElement userInfEle = driver.findElement(By.cssSelector("ul#user-links>li:nth-child(3)"));
        userInfEle.click();

        WebElement userNameTipEle = userInfEle.findElement(By.cssSelector("ul.dropdown-menu>li:first-child>strong"));
        Assert.assertEquals("anoorshan",userNameTipEle.getText(),"login failed !");


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
        // driver.findElement(By.cssSelector("nav.UnderlineNav-body>a:nth-child(2)")).click();
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

