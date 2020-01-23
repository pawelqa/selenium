import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelectorsXPath {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(700,500));
    }

    @AfterEach
    public void driverClose(){
        driver.close();
        driver.quit();
    }


    @Test
    public void getElementByXPath(){
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-atrybuty-w-xpath");
        //    Zestawy obiektów do zlokalizowania jednym selektorem:
        driver.findElement(By.xpath(".//*[text()='Button']"));  //Przyciski 1, 2, 5, 6.
        driver.findElement(By.xpath(".//*[contains(@style,'background-color: #db456f')]"));  //Przyciski 3 i 7.
        driver.findElement(By.xpath(".//*[contains(text(),'Btn')]"));  //Przyciski 3, 4, 7.
        driver.findElement(By.xpath(".//*[contains(@id,'button-')]"));  //    Przyciski 1, 2, 5.
        driver.findElement(By.xpath(".//*[@class='button primary test']"));  //    Przyciski 2, 6, 7.
        driver.findElement(By.xpath(".//*[contains(@class,'button accent')]"));  //    Przyciski 1, 3, 4, 5.
        driver.findElement(By.xpath(".//*[contains(@class,'button accent') and not(@id='btn-4')]"));  //    Przyciski 1, 3, 5.
        driver.findElement(By.xpath(".//*[contains(@id,'button-') and not(@id='button-2')]"));  //    Przyciski 1 i 5.

    }

    @Test
    public void getElementByXPath2(){
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-xpath/");
        driver.findElement(By.xpath(".//table/thead/tr[2]/td[2]")); // lub .//strong[text()='Nabywca:']/parent::td
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/../td[2]")); // lub .//*[text()='Bloczek samoprzylepny']/following-sibling::td[1]
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/../td[3]")); // lub .//*[text()='Bloczek samoprzylepny']/following-sibling::td[2]
        driver.findElement(By.xpath(".//*[text()='Bloczek samoprzylepny']/../td[4]")); // lub .//*[text()='Bloczek samoprzylepny']/following-sibling::td[3]
    }
}
