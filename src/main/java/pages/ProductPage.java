package pages;

import core.BasePage;
import core.Waiter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static org.openqa.selenium.By.*;

public class ProductPage extends BasePage {

    @FindBy(css = "body#product")
    protected WebElement root;

    @FindBy(css = "div.product-prices")
    protected WebElement productPricePanel;

    @FindBy(css = "div.product-variants-item")
    protected WebElement productSize;

    @FindBy(css = "div.product-quantity")
    protected WebElement productQuantity;

    @FindBy(css = "button[data-button-action='add-to-cart']")
    protected WebElement addToCartBtn;

    @FindBy(css = "div#blockcart-modal")
    protected WebElement cartContentRoot;

    public ProductPage(WebDriver driver, Waiter waiter) {
        super(driver, waiter);
    }

    @Override
    public WebElement getRoot() {
        return root;
    }

    public boolean verifyDiscount(int discountPercent) {
        final var initialPrice = waiter.forChildElementVisibleBy(productPricePanel, cssSelector("span.regular-price"))
                .getText()
                .replace("€", "");
        final var discountPrice = waiter.forChildElementVisibleBy(productPricePanel, cssSelector("span.current-price-value"))
                .getText()
                .replace("€", "");
        final var actualDiscount = getProductDiscount().replace("%", "");
        final var intPriceDouble = Double.parseDouble(initialPrice);
        final var discPriceDouble = Double.parseDouble(discountPrice);
        final var discountInt = Integer.parseInt(actualDiscount);

        if(discountPercent == discountInt) {
            if(intPriceDouble / 100 * (100 - discountInt) == discPriceDouble) {
                return true;
            }
        }

        return false;
    }

    public void setSize(String size) {
        Select sizeDropdown = new Select(waiter.forChildElementVisibleBy(productSize, cssSelector("select[aria-label='Size']")));
        sizeDropdown.selectByVisibleText(size.toUpperCase());
        try{
            waiter.forChildElementVisibleBy(Duration.ofSeconds(10), productSize, cssSelector("option[title='" + size.toUpperCase() + "'][selected]"));
        } catch (TimeoutException | NoSuchElementException e) {
            throw new AssertionError("Option is NOT selected!");
        }
    }

    public void setQuantity(int amount) {
        final var element = waiter.forChildElementSafelyClicked(productQuantity, cssSelector("input#quantity_wanted"));
        element.sendKeys(Keys.BACK_SPACE);
        element.sendKeys(String.valueOf(amount));

    }

    public void addToCart() {
        waiter.forElementSafelyClicked(addToCartBtn);
        waiter.forElementVisible(cartContentRoot);
    }

    public CheckoutPage proceedToCheckout() {
        waiter.forChildElementSafelyClicked(cartContentRoot, cssSelector("a[href*='controller=cart']"));
        return new CheckoutPage(driver, waiter);
    }

    public String getProductDiscount() {
        return waiter.forChildElementVisibleBy(productPricePanel, cssSelector("span.discount-percentage"))
                .getText()
                .toLowerCase()
                .replace("save ", "");
    }
}
