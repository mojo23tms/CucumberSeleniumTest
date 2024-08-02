import core.Waiter;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    protected WebDriver driver;
    protected Waiter waiter;
    private String userMail = "xecid76597@leacore.com";
    private String userPass = "Test123Test!";

    public TestBase() {
        this.driver = new ChromeDriver();
        this.waiter = new Waiter(driver);
    }


    public String getUserMail() {
        return userMail;
    }

    public String getUserPass() {
        return userPass;
    }
}
