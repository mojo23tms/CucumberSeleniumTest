package pages;

import core.BasePage;
import core.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    @FindBy(css = "body#index")
    protected WebElement root;

    @FindBy(css = "[title='Log in to your customer account']")
    protected WebElement loginBtn;

    public WebElement getRoot() {
        return root;
    }

    public HomePage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    public HomePage openHomePage() {
        driver.get("https://mystore-testlab.coderslab.pl/");
        waitForLoad();
        return this;
    }

    public LoginPage goToLoginPage() {
        waiter.forElementSafelyClicked(loginBtn);
        waiter.waitForLoad();
        return new LoginPage(driver, waiter);
    }
}
