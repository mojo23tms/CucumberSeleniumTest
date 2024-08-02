package pages;

import core.BasePage;
import core.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesPage extends BasePage {

    @FindBy(css = "body#addresses")
    protected WebElement root;

    @FindBy(css = "a#[data-link-action='add-address']")
    protected WebElement createNewAddressBtn;

    public AddressesPage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    public NewAddressPage clickCreateNewAddress() {
        waiter.forElementSafelyClicked(createNewAddressBtn);
        waiter.waitForLoad();
        return new NewAddressPage(driver, waiter);
    }
}
