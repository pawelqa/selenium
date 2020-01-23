import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SelectorsCSS {
    ChromeDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(800, 600));
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverClose(){
        driver.close();
        driver.quit();
    }

    @Test
    public void getElementsByCssLocators(){
        driver.findElement(By.cssSelector("input#username"));
        driver.findElement(By.cssSelector("input#reg_email"));
        driver.findElement(By.cssSelector("[name='password'][id='password'][autocomplete='current-password']"));
        driver.findElement(By.cssSelector("button[name='login']"));
    }

}

//        Skorzystaj ze strony https://fakestore.testelka.pl/cwiczenia-z-selektorow-fragmenty-wartosci-atrybutow/
////        Zestawy obiektów do zlokalizowania jednym selektorem:
////        1. Button1, Button 2, Btn 3, Btn 4  - a[class*='button']
////        2. Btn 3, Btn 4, Btn 7              - [id*='btn']
////        3. Btn 3, Btn 7                     - [style*='#db456f']
////        4. Button1, Button 2, Button 5      - [id*='button-']
////        5. Button1, Btn 3, Btn 5            - [class ~='accent']
////        6. Button 2, Button6, Btn 7         - [class ~='primary']
////        7. Button1, Btn 3, Btn 4, Button 5  - [class *='button accent']
//
// --------------------------------------------------------------------------------------------------------
//
//        wykop.pl - a[title='wykop to znalezisko']>span:first-child
//
// -------------------------------------------------------------------------------------------------------
//
//        Skorzystaj ze strony: https://fakestore.testelka.pl/wyszukiwanie-elementow-poprzez-relacje/.
//        Wyszukaj poniższe elementy.
//        1. Znajdź pole do wpisania imienia w pierwszym formularzu.
//        2. Znajdź pole do wpisania adresu email w trzecim formularzu.
//        3. Znajdź pole do wpisania adresu email w trzecim formularzu używając selektorów pseudoklas.
//        4. Znajdź przycisk „Subscribe” w piątym formularzu.
//        5. Znajdź przycisk „Subscribe” w piątym formularzu używając selektorów pseudoklas.
//        6. Znajdź przycisk „Subscribe” w drugim formularzu.
//        7. Znajdź przycisk „Subscribe” w drugim formularzu używając selektorów pseudoklas.
//
//        1. dl [id='name']  lub dd#name-element>input#name
//        2. div[class='second-div']>#email lub div.second-div>input#email
//        3. div.entry-content>p:nth-child(3)
//        4. div[class='second-div']>div[class='div']>input#submit lub div.second-div input#submit
//        5. div.second-div input:last-child
//        6. div:not([class='second-div'])>button#submit
//        7. div:not([class='second-div'])>button:only-of-type

