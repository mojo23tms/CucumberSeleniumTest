
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AccountPage;
import pages.AddressesPage;
import pages.HomePage;
import pages.NewAddressPage;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class TaskOneSteps extends TestBase {
    protected HomePage homePage;
    protected AccountPage accPage;
    protected AddressesPage addressesPage;
    protected NewAddressPage newAddressPage;
    protected final String EMAIL = getUserMail();
    protected final String PASS = getUserPass();

    @Given("I'm on https:\\/\\/mystore-testlab.coderslab.pl\\/ logined as new user")
    public void loginAsCreatedUser() {
        this.homePage = new HomePage(driver, waiter);
        homePage.waitForLoad();
        this.accPage = homePage.openHomePage()
                    .goToLoginPage()
                    .logIntoUser(EMAIL, PASS);
    }

    @When("Clicks on the Addresses tile after logging in")
    public void navigateToAddressPage(String section) {
        this.addressesPage = accPage.openSection(section, AddressesPage.class);
        addressesPage.waitForLoad();
    }

    @Then("The address we should be at: {string})")
    public void checkThatAddressIsValid(String expectedUrl) {
        final var url = driver.getCurrentUrl();
        assertThat(url)
                .as("Actual url doesn't match with expected!")
                .isEqualTo(expectedUrl);
    }

    @When("Clicks on + Create new address,")
    public void clickOnCreateNewAddress() {
        this.newAddressPage = addressesPage.clickCreateNewAddress();
        newAddressPage.waitForLoad();
    }

    @When("Fills out the {string} form")
    public void fills_out_the_form(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("Fills out the New address form with {string}, {string}, {string}, {string}, {string}, {string}")
    public void fills_out_the_new_address_form_with(String alias, String address, String city, String zipPostalCode, String country, String phone) {
        final var data = Map.of("Alias", alias,
                "Address", address,
                "City", city,
                "Postcode", zipPostalCode,
                "Country", country,
                "Phone", phone);
        this.addressesPage = newAddressPage.setNewAddress(data);
    }

    @Then("Checks if the data in the added address is correct..")
    public void checks_if_the_data_in_the_added_address_is_correct() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("Deletes the above address by clicking Delete")
    public void deletes_the_above_address_by_clicking_delete() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Checks if the address has been deleted.")
    public void checks_if_the_address_has_been_deleted() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
