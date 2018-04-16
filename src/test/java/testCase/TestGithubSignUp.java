package testCase;

import github.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Github_Page_SignUp;

public class TestGithubSignUp extends TestWebsite {
    Github_Page_SignUp pageObj;
    @DataProvider(name = "test1")
    public static Object[][] users() {
        return new Object[][] {
                { new User("","",""),false },
                { new User("shan","123456","anoorshan@163com"),false },
                { new User("css1","A123456","anoorshan@163.com"),false }};
    }
    @BeforeClass(dependsOnMethods = "init")
    public void goPage(){
        pageObj = new Github_Page_SignUp(driver);
        pageObj.goToPage();
    }
    @Test(dataProvider = "test1")
    public void test(User user, boolean isSuccess){
        pageObj.setUserName(user.getUserName());
        pageObj.setEmail(user.getEmail());
        pageObj.setPassword(user.getPassword());
        pageObj.submit();
        try {
            Thread.sleep(10000);
            Assert.assertNotEquals(isSuccess,pageObj.isCurrentPage(),"test github sign up failed!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
