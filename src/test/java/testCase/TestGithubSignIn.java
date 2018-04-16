package testCase;

import github.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Github_Page_SignIn;

public class TestGithubSignIn extends TestWebsite {
    Github_Page_SignIn pageObj;
    @DataProvider(name = "test1")
    public static Object[][] users() {
        return new Object[][] {
                { new User("","",""),false },
                { new User("shan","123456",""),false },
                { new User("css1","A123456",""),false },
                { new User("anoorshan","anoor881128",""),true }};
    }
    @BeforeMethod
    public void initPage(){
        pageObj = new Github_Page_SignIn(driver);
    }
    @Test(dataProvider = "test1")
    public void test(User user, boolean isSuccess){
        pageObj.goToPage();
        pageObj.setUserName(user.getUserName());
        pageObj.setPassword(user.getPassword());
        pageObj.submit();
        try {
            Thread.sleep(10000);
            Assert.assertNotEquals(isSuccess,pageObj.isCurrentPage(),"test github sign in failed!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
