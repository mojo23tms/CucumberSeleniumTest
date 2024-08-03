import core.Waiter;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class TestBaseJUnit {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    protected static WebDriver driver;
    protected static Waiter waiter;
    private String userMail = "xecid76597@leacore.com";
    private String userPass = "Test123Test!";

    @BeforeAll
    public static void init() {
        ChromeOptions ch = new ChromeOptions();
        ch.addArguments("--disable-search-engine-choice-screen");
        driverThreadLocal.set(new ChromeDriver());
        driver = getDriver();
        waiter = new Waiter(getDriver());
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }
}
