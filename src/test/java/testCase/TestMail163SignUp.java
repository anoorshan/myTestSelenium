package testCase;
import github.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Email163_Page_SignUp;

public class TestMail163SignUp extends TestWebsite{
    Email163_Page_SignUp pageObj;
    @DataProvider(name = "test1")
    public static Object[][] users() {
        return new Object[][] {
                { new User("","","126.com"),false },
                { new User("shan","123456","163.com"),false }};
    }
    @BeforeClass(dependsOnMethods = "init")
    public void goPage(){
        pageObj = new Email163_Page_SignUp(driver);
    }
    @Test(dataProvider = "test1")
    public void test(User user, boolean isSuccess){
        pageObj.goToPage();
        pageObj.setUserName(user.getUserName());
        pageObj.setEmailDomain(user.getEmail());
        pageObj.setPassword(user.getPassword());
        pageObj.setCfmPassword(user.getPassword());
        pageObj.setMobileIpt("1512555252");
        pageObj.setSelectedAccept(true);
        pageObj.submit();
        try {
            Thread.sleep(10000);
            Assert.assertNotEquals(isSuccess,pageObj.isCurrentPage(),"test 163 sign up failed!");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
