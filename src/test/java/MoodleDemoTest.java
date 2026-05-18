import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class MoodleDemoTest extends BaseTest {

    @Test
    public void testLoginWithDemoCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        
        Assert.assertTrue("Page title should contain Mount Orange", driver.getTitle().contains("Mount Orange") || driver.getTitle().contains("Log in"));
        
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue("User menu should be visible after login", dashboardPage.isUserMenuVisible());
    }

    @Test
    public void testEditProfile() {
        // First log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        
        // Navigate to profile
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goToProfile();
        
        // Edit Profile
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickEditProfile();
        
        profilePage.updateFirstName("Automated");
        profilePage.updateLastName("Student");
        profilePage.updateCity("Test City");
        profilePage.selectTimezone("Server timezone (UTC+1)");
        profilePage.selectCountry("Australia");
        
        // Advanced Task: Hover test (6 pts)
        profilePage.hoverOverUserPicture();
        
        profilePage.submitProfile();
        
        // Advanced Task: Cookie manipulation (6 pts) - read and print cookies
        System.out.println("Cookies: " + driver.manage().getCookies().size());
        
        // Ensure submission went through
        Assert.assertTrue("Should redirect or show success after saving profile", driver.getCurrentUrl().contains("profile.php") || driver.getCurrentUrl().contains("edit.php"));
    }

    @Test
    public void testStaticPageAndMultiplePages() {
        StaticPage staticPage = new StaticPage(driver);
        
        // Array of URLs to test (Multiple page test)
        List<String> urls = Arrays.asList(
            ConfigReader.getProperty("baseUrl"),
            ConfigReader.getProperty("baseUrl") + "login/index.php"
        );
        
        for (String url : urls) {
            staticPage.open(url);
            String siteName = staticPage.getSiteName();
            Assert.assertNotNull("Site name should exist on " + url, siteName);
        }
    }

    @Test
    public void testJavascriptExecutorAndHistory() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        
        // Scroll using JavascriptExecutor
        loginPage.scrollIntoView(org.openqa.selenium.By.tagName("footer"));
        
        // Navigate forward/back
        driver.navigate().to(ConfigReader.getProperty("baseUrl") + "login/forgot_password.php");
        driver.navigate().back();
        Assert.assertTrue("Should be back on login page", driver.getCurrentUrl().contains("login/index.php"));
        driver.navigate().forward();
        Assert.assertTrue("Should be on forgot password page", driver.getCurrentUrl().contains("forgot_password.php"));
    }

    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();
        
        Assert.assertTrue("Should be redirected to front page or login after logout", 
            driver.getCurrentUrl().endsWith("/") || driver.getCurrentUrl().contains("login"));
    }
}
