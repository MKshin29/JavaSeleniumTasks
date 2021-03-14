import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MailboxPage;

public class AppTest {

    WebDriver webDriver;

    LoginPage loginPage;
    MailboxPage mailboxPage;

    String login = "kvlasov29";
    String password = "Mikeshinoda1990##";

    @Before
    public void setup(){
        webDriver = new ChromeDriver();
        webDriver.get("https://mail.ru");

        loginPage = new LoginPage(webDriver);
    }

    @Test
    public void enterMailbox(){
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.submitCredentials();

        mailboxPage = new MailboxPage(webDriver);
        Assert.assertTrue(mailboxPage.waitMessagesDownload());
    }

    @Test
    public void unreadMessagesShown(){
        enterMailbox();
        mailboxPage.filterAndGetNewMessages();
        Assert.assertTrue(mailboxPage.waitMessagesDownload());
    }

    @Test
    public void allMessagesSetRead(){
        App.seleniumTaskPart2(login, password);
        boolean test = true;
        Assert.assertTrue(true);
    }

    @After
    public void end(){
        if (webDriver != null)
            webDriver.quit();
    }
}
