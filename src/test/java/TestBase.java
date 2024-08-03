import core.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class TestBase {

    protected WebDriver driver;
    protected Waiter waiter;
    private String userMail = "xecid76597@leacore.com";
    private String userPass = "Test123Test!";

    public TestBase() {
        ChromeOptions ch = new ChromeOptions();
        ch.addArguments("--disable-search-engine-choice-screen");
        this.driver = new ChromeDriver(ch);
        this.waiter = new Waiter(driver);
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserPass() {
        return userPass;
    }
}