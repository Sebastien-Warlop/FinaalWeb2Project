import domain.model.Boek;
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

public class TestUpdateBoek {

    private WebDriver driver;
    String url = "http://localhost:8080/FinaalWeb2Project_war_exploded/";

    @Before
    public void setUp() throws Exception {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\...\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sébastien\\Documents\\UCLL\\fase 1\\2e semester\\Webontwikkeling 2\\2e poging\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(url + "add.jsp");
    }


    @Test
    public void test_boek_heeft_andere_auteur_na_update() { //niet werkend
        driver.get(url + "update.jsp");
        fillOutForm("De meeste mensen deugen", "TestAuteur", "Informatief", 7, 335);
        driver.findElement(By.id("submit")).click();

        assertEquals("Overzicht Boeken", driver.getTitle());
        ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementsWithText(tds, "TestAuteur"));
    }


    @Test
    public void test_boek_update_zonder_titel_toont_foudboodschap() {
        driver.get(url + "update.jsp");
        fillOutForm("", "Rutger Bregman", "Informatief", 7, 335);
        driver.findElement(By.id("submit")).click();

        assertEquals("Update", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul een titel in."));
    }

    @After
    public void clean() {
        driver.quit();
    }


    private void fillOutForm(String titel, String auteur, String genre, int rating, int aantalPaginas){
        driver.findElement(By.id("titel")).sendKeys(titel);
        driver.findElement(By.id("auteur")).sendKeys(auteur);
        driver.findElement(By.id("genre")).sendKeys(genre);
        driver.findElement(By.id("rating")).sendKeys(String.valueOf(rating));
        driver.findElement(By.id("aantalPaginas")).sendKeys(String.valueOf(aantalPaginas));
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
