package my_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class First_Test {

    WebDriver driver;

    @BeforeMethod

    public void BrowserOpenMethod(){
        System.setProperty("webdriver.chrome.driver","E:\\chrome/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test

    public void TestMethod1() throws InterruptedException {

        String mainWindow=driver.getWindowHandle();

        driver.get("https://www.rentalhomes.com/property/individual/AB-29131127?utm_source");
        driver.findElement(By.name("date_start")).click();

        WebElement webElement = driver.findElement(By.className("month1"));
        List<WebElement> webElementList = webElement.findElements(By.tagName("td"));
        for (WebElement data : webElementList) {
            if (data.getText().equals("25")) {
                data.findElement(By.xpath("//div[contains(text(),'25')]")).click();
            }
        }
        Thread.sleep(2000);
        for (WebElement data : webElementList) {
            if (data.getText().equals("26")) {
                data.findElement(By.xpath("//div[contains(text(),'26')]")).click();
            }
        }
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[@class='search-button js_redirect_btn2']")).click();

        List<String> set_Of_window = new ArrayList<String>(driver.getWindowHandles());
        System.out.println("                            _______________________________  WELCOME  __________________________\n");
        System.out.println("                            _________________________  Total Number Of Windows: "+set_Of_window.size()+"  _________________________\n");
        System.out.println("                            ______________________________  ALL WINDOWS URL  _________________________\n");

        for (int i = 0; i < set_Of_window.size(); i++) {
            int j=i+1;
            driver.switchTo().window(set_Of_window.get(i));
            Thread.sleep(5000);
            System.out.println("Window No: "+j);
            System.out.println("Page URL: "+driver.getCurrentUrl());

            if (!driver.findElements(By.xpath("//*[@id=\"IM_target_overlay\"]/div/div/div/div/div")).isEmpty()){
                driver.findElement(By.xpath("//*[@id=\"IM_target_overlay\"]/div/div/div/div/a/span")).click();
                System.out.println("Clicked On Overlay");
            }
            else {
                System.out.println("There is No Overlay in That Window\n");
            }
        }
       driver.switchTo().window(mainWindow);
    }

    @AfterMethod

    public  void BrowserCloseMethod() {
        driver.close();
    }
}
