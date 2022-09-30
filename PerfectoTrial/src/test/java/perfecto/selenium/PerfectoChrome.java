package perfecto.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import perfecto.utils.Utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class PerfectoChrome {

    public static void main(String[] args) throws MalformedURLException {
//        String host = Utils.hostName;
        String securityToken = Utils.securityToken;
        String browserName = "mobileOS";

        Map<String, Object> perfectoOptions = new HashMap<>();
        perfectoOptions.put("platformVersion", "10");
        perfectoOptions.put("securityToken", securityToken);

        ChromeOptions options = new ChromeOptions();
        options.setPlatformName("Windows");
        options.setBrowserVersion("latest");
        options.setCapability("perfecto:options", perfectoOptions);

        URL url = new URL(Utils.URL);

        RemoteWebDriver driver = new RemoteWebDriver(url, options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        driver.get("https://www.saucedemo.com/");
        System.out.println(driver.getTitle());

        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.close();
    }
}
