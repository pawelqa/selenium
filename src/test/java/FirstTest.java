import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    // inicjalizacja drivera, WebDRiver to jest interface
    WebDriver driver;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");

        //ChromweDriver - klasa implementująca drivera, może byc m.in FirefoXDriver
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @AfterEach
    public void driverQuit() {
        driver.quit(); //zamyka okna chrome oraz zamyka sesję ChromeDriver
        // driver.close(); // zamyka aktywne okno przegladarki bez zamykania sesji, zostaje proces ChromeDriver
    }

    @Test
    public void getMethod() {
        driver.get("https://google.pl");
    }

    @Test
    public void navigate() {
        driver.navigate().to("https://google.pl");
        driver.navigate().to("https://amazon.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
    }
}

