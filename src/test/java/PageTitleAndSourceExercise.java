import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageTitleAndSourceExercise {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1000, 700));
    }

    @AfterEach
    public void driverClose(){
        driver.quit();
    }

    @Test
    public void getTitle(){
        String wikiTitle = "Wikipedia, wolna encyklopedia";
        driver.navigate().to("http://wikipedia.pl/");
        Assertions.assertEquals(wikiTitle,driver.getTitle(),"Wrong title "+wikiTitle);

    }

    @Test
    public void getUrl(){
        String wikiUrl ="https://www.wikipedia.org/";
        driver.navigate().to("https://www.wikipedia.org/");
        Assertions.assertEquals(wikiUrl,driver.getCurrentUrl(),"wrong url "+ wikiUrl);
    }

    @Test
    public void getSourcePL(){
        String wikiSource = "lang=\"pl\"";
        driver.navigate().to("https://www.wikipedia.org/");
        Assertions.assertTrue(driver.getPageSource().contains(wikiSource),"source "+wikiSource+" can no be found");
    }

    @Test
    public void verifyESlang() throws InterruptedException {
        driver.navigate().to("http://wikipedia.pl/");
       // Thread.sleep(1000);
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        String wikiSource = "lang=\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(wikiSource),"source "+wikiSource+" can no be found");

    }




    


}
