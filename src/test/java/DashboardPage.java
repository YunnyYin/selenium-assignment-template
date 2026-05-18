import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    // Complex XPath example: Find the logout link inside the user menu carousel/dropdown
    private By userMenuToggle = By.id("user-menu-toggle");
    private By logoutLink = By.xpath("//div[@id='carousel-item-main']//a[contains(@href, 'logout.php')]");
    private By profileLink = By.xpath("//div[@id='carousel-item-main']//a[contains(@href, 'profile.php') or contains(text(), 'Profile')]");
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void openUserMenu() {
        click(userMenuToggle);
    }

    public void logout() {
        openUserMenu();
        click(logoutLink);
    }

    public void goToProfile() {
        openUserMenu();
        click(profileLink);
    }
    
    public boolean isUserMenuVisible() {
        return waitForElement(userMenuToggle).isDisplayed();
    }
}
