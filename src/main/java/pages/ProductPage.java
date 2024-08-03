package pages;

import core.BasePage;
import core.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.*;

public class ProductPage extends BasePage {

    @FindBy(css = "body#product")
    protected WebElement root;

    @FindBy(css = "div.product-prices")
    protected WebElement productPricePanel;

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

    public String getProductDiscount() {
        return waiter.forChildElementVisibleBy(productPricePanel, cssSelector("span.discount-percentage"))
                .getText()
                .toLowerCase()
                .replace("save ", "");
    }
}
