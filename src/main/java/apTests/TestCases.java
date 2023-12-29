
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
// Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);



        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.wikipedia.org");
        String title = driver.getTitle();
        if (title.contains("Wikipedia")) {
            System.out.println("the current URL contains the expected title 'wikipedia' ");
        } else {
            System.out.println("the current URL is wrong");
        }

        System.out.println("end Test case: testCase01");
    }



    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.wikipedia.org");
        WebElement headerText = driver.findElement(
                By.xpath("//span[@class='central-textlogo__image sprite svg-Wikipedia_wordmark']"));
        if (headerText.getText().contains("Wikipedia")) {
            System.out.println("Verify if the header text is Wikipedia");
        } else {
            System.out.println("Something is wrong");
        }
        List<WebElement> footerText =
                driver.findElements(By.xpath("//p[@class='site-license']//a"));
        for (WebElement text : footerText) {
            if (text.getText().contains("Privacy Policy")) {
                System.out.println("Confirm that the footer links contain Privacy Policy");
            }
            if (text.getText().contains("Terms of Use")) {
                System.out.println("Confirm that the footer links contain Terms of Use");

            }
        }
        System.out.println("end Test case: testCase02");

    }

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.wikipedia.org");
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys("apple");
        Thread.sleep(2000);
        driver.findElement(
                By.xpath("//div[@class='suggestions-dropdown']//a[@href='https://en.wikipedia.org/wiki/Apple_Inc.']")).click();
        Thread.sleep(3000);
        List<WebElement> founderList =
                driver.findElements(By.xpath("//table[@class='infobox vcard']//tr[9]//td//li"));
        for (WebElement founder : founderList) {
            if (founder.getText().contains("Steve Jobs")) {
                System.out.println("Steve Jobs is listed as a founder");
            }
        }
        System.out.println("end Test case: testCase03");
    }

    public void testCase04() throws InterruptedException {

        System.out.println("Start Test case: testCase04");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        driver.get("https://www.wikipedia.org");
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys("microsoft");
        Thread.sleep(2000);
        driver.findElement(
                By.xpath("//div[@class='suggestions-dropdown']//h3//em[text()='Microsoft']"))
                .click();
        Thread.sleep(3000);
        List<WebElement> founderList =
                driver.findElements(By.xpath("//table[@class='infobox vcard']//tr[8]//td//li//a"));
        for (WebElement founder : founderList) {
            if (founder.getText().contains("Bill Gates")) {
                founder.click();
                System.out.println("Bill Gates is listed as a founder");
                break;



            }
        }
        if (driver.getCurrentUrl().contains("Bill_Gates")) {
            System.out.println("the opened URL contains Bill_Gates");
        }
        System.out.println("end Test case: testCase04");


    }

    public void testCase05() throws InterruptedException {
        System.out.println("Start Test case: testCase05");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        driver.get("https://en.wikipedia.org/");
        WebElement menuBtn = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        menuBtn.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@title='Learn about Wikipedia and how it works']"))
                .click();
        Thread.sleep(2000);
        if (driver.getCurrentUrl().contains("About")) {
            System.out.println("the opened URL contains 'About'");
        }
        System.out.println("end Test case: testCase05");


    }

}
