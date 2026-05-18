import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends BasePage {

    private By editProfileLink = By.partialLinkText("Edit profile");
    private By firstNameInput = By.id("id_firstname");
    private By lastNameInput = By.id("id_lastname");
    private By descriptionTextarea = By.id("id_description_editor");
    private By timezoneDropdown = By.id("id_timezone");
    private By submitButton = By.id("id_submitbutton");
    private By userPictureHover = By.className("userpicture");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickEditProfile() {
        click(editProfileLink);
    }

    public void updateFirstName(String firstName) {
        type(firstNameInput, firstName);
    }
    
    public void updateLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void updateCity(String city) {
        type(By.id("id_city"), city);
    }

    public void selectCountry(String countryText) {
        scrollIntoView(By.id("id_country"));
        Select select = new Select(waitForElement(By.id("id_country")));
        select.selectByVisibleText(countryText);
    }

    public void selectTimezone(String timezoneText) {
        scrollIntoView(timezoneDropdown);
        Select select = new Select(waitForElement(timezoneDropdown));
        select.selectByVisibleText(timezoneText);
    }

    public void hoverOverUserPicture() {
        org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
        actions.moveToElement(waitForElement(userPictureHover)).perform();
    }

    public void submitProfile() {
        scrollIntoView(submitButton);
        click(submitButton);
    }
}
