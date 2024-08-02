package pages;

import core.BasePage;
import core.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

import static org.openqa.selenium.By.*;

public class NewAddressPage extends BasePage {

    @FindBy(css = "body#address")
    protected WebElement root;

    @FindBy(css = "input#field-alias")
    protected WebElement aliasInput;

    @FindBy(css = "input#field-firstname")
    protected WebElement firstNameInput;

    @FindBy(css = "input#field-lastname")
    protected WebElement lastNameInput;

    @FindBy(css = "input#field-company")
    protected WebElement companyInput;

    @FindBy(css = "input#field-vat_umber")
    protected WebElement vatNumberInput;

    @FindBy(css = "input#field-address1")
    protected WebElement address_1Input;

    @FindBy(css = "input#field-city")
    protected WebElement cityInput;

    @FindBy(css = "input#field-postcode")
    protected WebElement postCodeInput;

    @FindBy(css = "input#field-country")
    protected WebElement countryInput;

    @FindBy(css = "input#field-phone")
    protected WebElement phoneInput;

    @FindBy(css = "button[type='submit']")
    protected WebElement submitAddressBtn;


    public NewAddressPage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    public AddressesPage setNewAddress(Map<String, String> addressData) {
        addressData.forEach((field, value) -> {
            waiter.forElementSafelyClicked(cssSelector("input[name='" + field.toLowerCase() + "']"))
                    .sendKeys(value);
        });
        waiter.forElementSafelyClicked(submitAddressBtn);
        return new AddressesPage(driver, waiter);
    }


}
