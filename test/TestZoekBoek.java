import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class TestZoekBoek {

    private WebDriver driver;
    String url = "http://localhost:8080/FinaalWeb2Project_war_exploded/";

    @Before
    public void setUp() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\...\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sébastien\\Documents\\UCLL\\fase 1\\2e semester\\Webontwikkeling 2\\2e poging\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url);
    }


    @Test
    public void test_zoek_formulier_toont_info_bij_juist_boek() {
        driver.get(url + "zoek.jsp");
        fillOutForm("abc");

        driver.findElement(By.id("submit")).click();

        assertEquals("Zoek resultaten", driver.getTitle());
        ArrayList<WebElement> abc = (ArrayList<WebElement>) driver.findElements(By.tagName("p"));
        assertTrue(containsWebElementsWithText(abc, "Je vroeg naar volgende gegevens: abc, Marc, Kind, 20 pagina's, rating: 5/10"));
    }


    @Test
    public void test_zoek_formulier_geeft_niet_gevonden_pagina_bij_niet_gevonden_boek() {
        driver.get(url + "zoek.jsp");
        fillOutForm("testFout");

        driver.findElement(By.id("submit")).click();

        assertEquals("Zoek resultaten", driver.getTitle());
        ArrayList<WebElement> abc = (ArrayList<WebElement>) driver.findElements(By.tagName("p"));
        assertTrue(containsWebElementsWithText(abc, "Helaas, het boek waar je naar vraagt is niet gevonden."));
    }

    @Test
    public void test_zoek_formulier_toont_foutmeldingen_bij_lege_input() {
        driver.get(url + "zoek.jsp");
        fillOutForm("");

        driver.findElement(By.id("submit")).click();

        assertEquals("Zoek een boek", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een titel in."));
    }


    @After
    public void clean() {
        driver.quit();
    }


    private void fillOutForm(String titel){
        driver.findElement(By.id("titel")).sendKeys(titel);
    }

    private boolean containsWebElementsWithText(ArrayList<WebElement> elements, String text) {
        for (int i = 0; i < elements.size(); i ++){
            if (elements.get(i).getText().equals(text)){
                return true;
            }
        }
        return false;
    }
}
