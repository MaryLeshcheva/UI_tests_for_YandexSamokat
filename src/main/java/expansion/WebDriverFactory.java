package expansion;


import config.AppConfig;
import config.WebDriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver get() {
        String browserName = System.getenv().get("browser");

        WebDriver driver;
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "yandex":
                driver = createYandexDriver();
                break;
            default: throw new RuntimeException("Browser " + browserName + " not exist");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfig.WAIT_SECONDS_TIMEOUT));
        driver.navigate().to(AppConfig.URL);
        return driver;
    }

    private static WebDriver createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", String.format("%s/%s", System.getenv("BROWSER_DRIVERS"),
                System.getenv("YANDEX_BROWSER_DRIVER_FILENAME")));
        ChromeOptions options = new ChromeOptions();
        System.out.println(System.getenv("YANDEX_BROWSER_PATH"));
        options.setBinary(System.getenv("YANDEX_BROWSER_PATH"));
        return new ChromeDriver(options);
    }
}
