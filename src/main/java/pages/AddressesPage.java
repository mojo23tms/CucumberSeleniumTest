package pages;

import core.BasePage;
import core.Waiter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.*;

public class AddressesPage extends BasePage {

    @FindBy(css = "body#addresses")
    protected WebElement root;

    @FindBy(css = "a[data-link-action='add-address']")
    protected WebElement createNewAddressBtn;

    @FindBy(css = "article[data-alert='success']")
    protected WebElement successAlert;

    @FindBy(css = "article.address")
    protected WebElement addressTile;

    public AddressesPage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    public WebElement getRoot() {
        return root;
    }

    public void checkIfSuccessAlertIsPresent() {
        try {
            waiter.forElementVisible(Duration.ofSeconds(10), successAlert);
        } catch(TimeoutException e) {
            throw new AssertionError("There is no success message on the page!");
        }
    }

    public List<List<String>> getAllAddresses() {
        return waiter.forAllElementsVisible(addressTile)
                .stream()
                .map(tile -> Arrays.stream(
                        tile.findElement(cssSelector(".address-body"))
                                .getText()
                                .split("\\n")).toList())
                .collect(Collectors.toList());
    }

    public NewAddressPage clickCreateNewAddress() {
        waiter.forElementSafelyClicked(createNewAddressBtn);
        waiter.waitForLoad();
        return new NewAddressPage(driver, waiter);
    }
}
