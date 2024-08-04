package pages;

import core.BasePage;
import core.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(css = "div#cart")
    protected WebElement root;

    public CheckoutPage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    @Override
    public WebElement getRoot() {
        return root;
    }
}
