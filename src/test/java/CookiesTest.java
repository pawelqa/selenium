import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.GregorianCalendar;

public class CookiesTest {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.navigate().to("http://wikipedia.pl");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void driverClose() {
        driver.close();
        driver.quit();
    }

    @Test
    public void getCookies() {
        Assertions.assertEquals(3, driver.manage().getCookies().size());
       // Cookie cookie = new Cookie("Test cookie name", "test cookie value");
        Cookie cookie1 = new Cookie("Test cookie name", "test cookie value",".wikipedia.org","/",
                new GregorianCalendar(2020,02,01).getTime(),true,true);

        driver.manage().addCookie(cookie1);
        Assertions.assertEquals(4, driver.manage().getCookies().size());
        Assertions.assertEquals("Test cookie name", driver.manage().getCookieNamed("Test cookie name").getName());
        //driver.manage().deleteCookie(cookie1);
       // driver.manage().deleteCookieNamed("GeoIP");
      //  driver.manage().deleteAllCookies();
       // Assertions.assertEquals(3, driver.manage().getCookies().size());
    }

}
