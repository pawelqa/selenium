import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageTitleUrlAndSource {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(700, 500));
    }

    @AfterEach
    public void driverClose() {
        driver.quit();
    }

    @Test
    public void getPageTitle() {
        String googleTitle = "Google";
        driver.get("http://google.pl");
        Assertions.assertEquals(googleTitle, driver.getTitle(), "Google title is not " + googleTitle);
    }

    @Test
    public void getPageUrl() {
        String googleURL = "https://www.google.pl/";
        driver.navigate().to("https://www.google.pl");
        Assertions.assertEquals(googleURL, driver.getCurrentUrl());
    }

    @Test
    public void getPageSource() {
        String googleSource = "/images/branding/googleg/1x/googleg_standard_color_128dp.png";
        driver.get("https:/www.google.pl");
        Assertions.assertTrue(driver.getPageSource().contains(googleSource));
    }
}
