import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StaticPage extends BasePage {

    private By pageHeading = By.tagName("h1");
    private By siteName = By.tagName("body");

    public StaticPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }

    public String getSiteName() {
        return getText(siteName);
    }
}
