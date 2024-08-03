
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import pages.*;

import static org.junit.jupiter.api.TestInstance.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TaskTwoSteps extends TestBaseJUnit {

    protected HomePage homePage;
    protected AccountPage accPage;
    protected AddressesPage addressesPage;
    protected NewAddressPage newAddressPage;
    protected TopPanel topPanel;

    protected final String EMAIL = getUserMail();
    protected final String PASS = getUserPass();

    @Test
    @Order(1)
    @DisplayName("Logs in to the same user from task")
    public void logs_in_to_the_same_user_from_task() {
        this.homePage = new HomePage(driver, waiter);
        this.accPage = homePage.openHomePage()
                .goToLoginPage()
                .logIntoUser(EMAIL, PASS);
    }

    @Test
    @Order(2)
    @DisplayName("Chooses the Hummingbird Printed Sweater for purchase")
    public void chooses_the_hummingbird_printed_sweater_for_purchase() {
        this.homePage = new TopPanel(driver, waiter).goToMainPage();
    }

    @Test
    @Order(3)
    @DisplayName("checks if the discount on it is {int}%")
    public void checks_if_the_discount_on_it_is() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(4)
    @DisplayName("Selects size S")
    public void selects_size_s() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(5)
    @DisplayName("Selects {int} pieces according to the parameter given in the test")
    public void selects_pieces_according_to_the_parameter_given_in_the_test() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(6)
    @DisplayName("Adds the product to the cart,")
    public void adds_the_product_to_the_cart() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(7)
    @DisplayName("Proceeds to the checkout option,")
    public void proceeds_to_the_checkout_option() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(8)
    @DisplayName("Confirms the address,")
    public void confirms_the_address() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(9)
    @DisplayName("Selects the delivery method - PrestaShop {string},")
    public void selects_the_delivery_method_presta_shop() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(10)
    @DisplayName("Selects the payment option - Pay by Check,")
    public void selects_the_payment_option_pay_by_check() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(11)
    @DisplayName("Clicks on {string},")
    public void clicks_on() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Test
    @Order(12)
    @DisplayName("Takes a screenshot with the order confirmation and the amount.")
    public void takes_a_screenshot_with_the_order_confirmation_and_the_amount() {
        // Write code here that turns the phrase above into concrete actions
    }
}
