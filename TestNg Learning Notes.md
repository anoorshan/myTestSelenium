# 学习TestNG和Selenium
## 0 遇到的问题
- firefox浏览器，执行`driver.quit();` 时，会报错
- firefox浏览器，执行`driver.close();` 时，会报错
- `WebDriver driver = new InternetExplorerDriver();`过程中报错，IE浏览器异常关闭

## 1 TestNG
### 1.1相关配置
#### 1.1.1 pom.xml添加相关依赖
```xml
      <!-- https://mvnrepository.com/artifact/org.testng/testng -->
      <dependency>
          <groupId>org.testng</groupId>
          <artifactId>testng</artifactId>
          <version>6.14.2</version>
          <scope>test</scope>
      </dependency>
```
#### 1.1.2  pom.xml配置testNG.xml
```
            <!-- 添加插件 关联testNg.xml -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.5</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                    <suiteXmlFiles>
                        <file>res/testNG.xml</file>
                    </suiteXmlFiles>
                    <!--<workingDirectory>target/</workingDirectory>-->
                </configuration>
            </plugin>
```
### 1.2 认识注解

| 注解 | 描述 |
| ---- | :---- |
| @BeforeSuite |	在该套件的所有测试都运行在注释的方法之前，仅运行一次。|
| @AfterSuite	| 在该套件的所有测试都运行在注释方法之后，仅运行一次。
| @BeforeClass	| 在调用当前类的第一个测试方法之前运行，注释方法仅运行一次。
| @AfterClass	| 在调用当前类的第一个测试方法之后运行，注释方法仅运行一次
| @BeforeTest	| 注释的方法将在属于<test>标签内的类的所有测试方法运行之前运行。
| @AfterTest	| 注释的方法将在属于<test>标签内的类的所有测试方法运行之后运行。
| @BeforeGroups	| 配置方法将在之前运行组列表。 此方法保证在调用属于这些组中的任何一个的第一个测试方法之前不久运行。
| @AfterGroups	| 此配置方法将在之后运行组列表。该方法保证在调用属于任何这些组的最后一个测试方法之后不久运行。
| @BeforeMethod	| 注释方法将在每个测试方法之前运行。
| @AfterMethod	| 注释方法将在每个测试方法之后运行。
| @DataProvider	| 标记一种方法来提供测试方法的数据。 注释方法必须返回一个Object [] []，其中每个Object []可以被分配给测试方法的参数列表。  要从该DataProvider接收数据的@Test方法需要使用与此注释名称相等的dataProvider名称。
| @Factory	    | 将一个方法标记为工厂，返回TestNG将被用作测试类的对象。 该方法必须返回Object []。
| @Listeners	| 定义测试类上的侦听器。
| @Parameters	| 描述如何将参数传递给@Test方法。
| @Test	        | 将类或方法标记为测试的一部分。

### 1.3 重要知识点

  - 参数化
  - 忽略
  - 依赖
  - 分组
  - 预期异常
  - 超时
  - 负载测试

#### 1.3.1 参数化
参数化有两个途径实现
  - testNG.xml 中配置parameter，配置简单的参数，遵循
  ```xml
  <test name="test2">

        <parameter name = "myName" value="css"/>
        <parameter name = "id" value="1"/>
        <parameter name="isFemale" value="true"/>
        <parameter name="score" value="A"/>
        <parameter name="time" value="1523284190"/>

        <classes>
            <class name="ParameterizedTest1"/>
            <class name="ParamTestWithDataProvider1"/>
            <class name="TestCalculatorExcel" />
        </classes>
    </test>
  ```
在代码中使用参数：
```java
    @Test
    @Parameters("myName")
    public void parameterTest(String myName) {
        System.out.println("Parameterized value is : " + myName);
    }
```
  - 使用DataProvider构造复杂数据
  ```java
  import org.testng.annotations.DataProvider;
  import org.testng.annotations.Test;
  @DataProvider(name = "test1")
  public class TestValidateUserInfWIthDataProvider {
      @DataProvider(name = "test1")
      public static Object[][] users() {
          return new Object[][] {
                  { new User("cssrrr",11,"anoorshan@163.com", "15216610825"),true },
                  { new User("css12366#",11,"anoorshan@163.com", "15216610825"),false },
                  { new User("css1",11,"anoorshan@163.com", "15216610825"),false }};
      }
      @Test(dataProvider = "test1")
      public void testMethod( User user, Boolean isValid) {
          Boolean flg = ValidateUserInf.validateUserName(user.getUserName());
          Assert.assertEquals(isValid,flg,user.getUserName()+" fail");
      }
  }
  ```

#### 1.3.2 忽略
如果使用`@Test(enabled = false)`注释在测试方法上，则会绕过这个未准备好测试的测试用例。
#### 1.3.3 依赖
在需要以特定顺序调用测试用例中的方法，为方法添加依赖，TestNG允许指定依赖关系：

  - 在@Test注释中使用属性dependsOnMethods，`@Test(dependsOnMethods = { "method1" })`
  - 在@Test注释中使用属性dependsOnGroups，`@Test(dependsOnMethods = { "initDB" }, groups="db")`
  说明:
  - 二者可以单独使用，也可以混合使用，例如`@Test(dependsOnMethods = { "initDB" }, groups="db")`
  - 当测试所依赖的方法或者组失败时，测试则跳过不执行

#### 1.3.4 分组
&emsp;&emsp;分组测试是TestNG中的一个新的创新功能，它在JUnit框架中是不存在的。 它允许您将方法调度到适当的部分，并执行复杂的测试方法分组。组测试提供了如何分区测试的最大灵活性，如果您想要背靠背运行两组不同的测试，则不需要重新编译任何内容。

&emsp;&emsp;使用`<groups>`标记在testng.xml文件中指定分组。 它可以在`<test>`或`<suite>`标签下找到。 `<suite>`标签中指定分组适用于其下的所有`<test>`标签。
说明：
- 测试方法也可以同时属于多个分组,`@Test(groups = {"mysql","database"})`
- 组的测试控制在testNG.xml中配置
```xml
<test name = "testMessage">
        <groups>
            <define name = "all">
                <include name = "functest"/>
                <include name = "checkintest"/>
            </define>
            <define name = "func">
                <include name = "functest"/>
            </define>
            <run>
                <include name = "all" />
            </run>
        </groups>
        <classes>
            <class name = "TestMessageUtil"/>
            <class name="ExpectedExceptionTest"/>
        </classes>
    </test>
```

#### 1.3.5 预期异常
使用`@Test(expectedExceptions = )`，例如`@Test(expectedExceptions = ArithmeticException.class)`可测试运行时异常，如除0
#### 1.3.6 超时
使用 `@Test(timeOut = 5000) // time in mulliseconds`设置超时,“超时”表示如果单元测试花费的时间超过指定的毫秒数，那么TestNG将会中止它并将其标记为失败。
#### 1.3.7 负载测试
使用`@Test(invocationCount = ?, threadPoolSize = ?)`，定义测试次数和线程。
- invocationCount，确定TestNG应该运行这个测试方法的次数
- threadPoolSize，属性告诉TestNG创建一个线程池以通过多个线程运行测试方法。 使用线程池，会大大降低测试方法的运行时间  

## 2 TestNG 与 Selenium
- [Selenium 教程](https://www.yiibai.com/selenium/)
- [testNG 教程](www.yiibai.com/testng/)
- http://gitbook.net/selenium/

按照以下步骤可实现简单的Selenium测试

### 2.1 pom.xml添加依赖
```xml
        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.11.0</version>
        </dependency>
```

###  2.2 webdriver配置
确定浏览器的安装路径以及相关驱动(webdriver)的路径
    - [chromedriver 下载](https://sites.google.com/a/chromium.org/chromedriver/downloads)
    - [firefox driver下载](https://github.com/mozilla/geckodriver/releases)
    - [IE driver下载](http://selenium-release.storage.googleapis.com/index.html)

### 2.3 java 代码
```
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class seleniumByChrome {
        @Test(invocationCount = 2)
        public void test() {
            System.setProperty("webdriver.chrome.driver", "D:\\Program Files (x86)\\webdriver\\Chrome\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get("http://www.baidu.com");
            System.out.println("Page Title is " + driver.getTitle());
            Assert.assertEquals("百度一下，你就知道",driver.getTitle());
            driver.close();
            driver.quit();
        }
    }
```
### 2.4 知识点
- selenium 获取元素的方法（8种）
- WebDriver
- WebElement
  - 元素交互
  - 动作交互
- Synchronization 同步
  - THREAD.SLEEP
  - 显式等待
  - 隐式等待
  - 流利等待
- Selenium页面对象模型（POM）
- excel参数化
- 测试快照
  - 捕捉屏幕截图
  - 捕捉视频

#### 2.4.1 selenium 获取元素的方法

| Method	    | Syntax	|  描述 |
| :---        | :---    | :---  |        
| By ID	        | driver.findElement(By.id(<element ID>))	  | 定位元素使用ID属性  |
| By name	    | driver.findElement(By.name(<element name>))	|  定位使用Name属性的元素 |
| By class name	| driver.findElement(By.className(<element class>))	| 定位使用类属性的元 |
| By tag name	| driver.findElement(By.tagName(<htmltagname>))	|  定位使用HTML标记元素 |
| By link text	| driver.findElement(By.linkText(<linktext>))	|  定位使用的链接文字链接 |
| By partial link text	| driver.findElement(By.partialLinkText(<linktext>))  |  定位链接使用链接的文字部分 |
| By CSS	    | driver.findElement(By.cssSelector(<css selector>))	      |  定位使用CSS选择器的元素    |
| By XPath	  | driver.findElement(By.xpath(<xpath>))	                      |  定位使用XPath查询元素      |

说明：xpath的获取方法：浏览器打开开发者工具，选择元素，然后再元素的html代码标签上点击右键，选择“copy”-“copy xpath”

#### 2.4.2 WebDriver

| 方法 | 参数 | 描述 |
| ---- | :----: | :----: |
| driver.navigate().to()| url | 设置 url |
| driver.get()| url |设置 url |
| driver.close()|  | 关闭当前窗口 |
| driver.quit()|  | 关闭相关窗口 |
| driver.findElement() | By | 查找元素 |
| driver.findElements() | By | 查找元素 |
| driver.manage() | | 设置 |
| driver.getTitle() | | 获取文档的title|
| driver.getPageSource() | | |
| driver.getWindowHandle() | | |
| driver.getWindowHandles() | | |
| driver.switchTo() |  |  |
| driver.getCurrentUrl() | | 获取当前路径|


#### 2.4.3 WebElement
##### 2.4.3.1 元素交互
- 文本元素(span,b):`getText()`
- **input[type=‘text’]**：`SendKeys("1234")`设置值， `getattribute("value")`获取文本框的值
- **radio：**`click`切换选择项，`isSelected()`获取元素是否被选中
- **checkbox:** `click`切换选择项，`isSelected()` 获取元素是否被选中
- **select:**`selectByVisibleText`或`selectByIndex`或`selectByValue`的方法选择一个选项,`isSelected`获取元素是否被选中

##### 2.4.3.2 动作交互
- 拖拽
```java
WebElement From = driver.findElement(By.xpath(".//*[@id='j3_7']/a"));
	WebElement To = driver.findElement(By.xpath(".//*[@id='j3_1']/a"));
	Actions builder = new Actions(driver);
	Action dragAndDrop = builder.clickAndHold(From)
			.moveToElement(To)
			.release(To)
			.build();
	dragAndDrop.perform();
```
- 键盘操作
  - `sendKeys`：发送键，在浏览器的键盘表示。特殊键都没有文字，表示按键都为字符，或单独序列的一部分的认可。

  - `pressKey`： 按键盘上不是文字的按键。键等功能键“F1”，“F2”或“Tab”或“Control”等，如果keyToPress是一个字符序列，不同的驱动程序实现可以选择抛出一个异常，或者在序列中读取的第一个字符。

  - `releaseKey`：执行按键事件后松开键盘上的一个键。它通常是拥有良好的非文本字符。
- 鼠标操作
  -  `click `： 进行点击。我们还可以执行基于坐标的点击。

  -  `contextClick `： 执行上下文点击/右键单击一个元素或基于坐标

  -  `doubleClick `： 执行双击webelement或基于坐标。如果留空它执行双击当前位置。

  -  `mouseDown `： 执行一个元素上按下鼠标操作或基于坐标。

  -  `mouseMove `： 执行元素上的鼠标移动操作或基于坐标。

  -  `mouseUp `：释放鼠标通常伴随着鼠标按下的动作和行为的基础上统筹。

#### 2.4.4 Synchronization 同步
##### 2.4.4.1 THREAD.SLEEP

`Thread.sleep`代码是一个静态的等待
```java
Thread.Sleep(1000); //Will wait for 1 second.
````

##### 2.4.4.2  显式等待
一个明确的等待，等待某个条件进一步处理之前发生。它主要用于当我们想要点击或采取行动的对象，一旦它是可见的。
```java
WebDriver driver = new FirefoxDriver();
driver.get("Enter an URL"S);
WebElement DynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("DynamicElement")));
```
##### 2.4.4.3  隐式等待
隐式等待的情况下，如果网络驱动器找不到，因为它的不可用性的立即的对象。webdriver将等待指定的隐含的等待时间，也不会尝试在指定时间内找到的元素了。一旦指定的时间限制被超越，webdriver将尝试再次搜索该元素的最后一面。如果成功，将继续进行执行，但如果失败，它会抛出异常。这是一种全局的等待，这意味着这种等待是适用于整个驱动程序。因此，硬编码这种等待更长的时间时期将阻碍该脚本执行时间。
```java
WebDriver driver = new FirefoxDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get("Enter an URL");
WebElement DynamicElement = driver.findElement(By.id("DynamicElement"));
```

##### 2.4.4.4  流利等待
FluentWait用于当webelement可以出现在5秒或者甚至它可以采取90秒。在这种情况下，我们定义的时间等待的状态的最大数量，以及与该查询的对象状态的是否存在等的频率。

让我们假定，我们将60秒可用一个元素在网页上，但每10秒检查一次它的存在。
```java
Wait wait = new FluentWait(driver)
  .withTimeout(60, SECONDS)
  .pollingEvery(10, SECONDS)
  .ignoring(NoSuchElementException.class);
WebElement dynamicelement = wait.until(new Function<webdriver,webElement>()
{
  public WebElement apply(WebDriver driver)
  {
  return driver.findElement(By.id("dynamicelement"));
  }
 }
);
```

#### 2.4.5 Selenium页面对象模型（POM）
思路：

每一个页面对应一个页面类，页面的元素写到这个页面类中。- 页面类主要包括该页面的元素定位，和这些元素相关的业务操作代码封装的方法。把页面元素和业务逻辑和测试脚本分离出来到两个不同类文件。ClassA只写页面元素定位，和业务逻辑代码操作的封装，ClassB只写测试脚本，不关心如何元素定位，只写调用ClassA的代码去覆盖不同的测试场景。如果前端页面发生变化，只需要修改ClassA的元素定位，而不需要去修改ClassB中的测试脚本代码。

优点：
- 页面的对象模型是其中测试对象和功能被彼此分开，把web ui对象仓库从测试脚本分离，业务代码和测试脚本分离，从而保持代码干净的实现。
- 对象保持独立的测试脚本。一个目的可以通过一个或多个测试脚本进行访问，因此，POM可以帮助我们创建对象一次和多次使用。
- 由于创建对象后，很容易访问和易于更新一个对象的特定属性。
- 代码复用，从而减少测试脚本代码量。
- 层次清晰，同时支持多个编写自动化脚本开发，例如每个人写哪几个页面，不影响他人。
- 建议页面类和业务逻辑方法都给一个有意义的名称，方便他人快速编写脚本和维护脚本。

#### 2.4.6 excel参数化
#### 2.4.7 测试快照
  - 捕捉屏幕截图
  - 捕捉视频
