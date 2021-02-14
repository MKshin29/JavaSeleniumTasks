import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MailboxPage;

import java.util.List;

public class App {

    public static void main(String[] args) throws Exception{
        String login = "login";
        String password = "password";
        //как обычно, раскомментируй что бы проверить :)
        //тут понадобятся логин и пароль для авторизации
        //seleniumTaskPart1(login, password);

        seleniumTaskPart2(login, password);
    }

    public static void seleniumTaskPart1(String login, String password) throws Exception{
        WebDriver webDriver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        webDriver.get("https://mail.ru");
        //авторизация
        webDriver.findElement(By.name("login")).sendKeys("");
        webDriver.findElement(By.name("login")).sendKeys(login);
        webDriver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/button[1]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));

        webDriver.findElement(By.name("password")).sendKeys("");
        webDriver.findElement(By.name("password")).sendKeys(password);
        webDriver.findElement(By.xpath("//*[@id=\"mailbox\"]/form[1]/button[2]")).click();

        //ждем загрузки страницы
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dataset__items")));
        WebElement messagesTable = webDriver.findElement(By.className("dataset__items"));

        //читаем количество непрочитанных из бабла наверху
        int numberOfUnreadMessages = Integer.parseInt(webDriver.findElement(By.id("g_mail_events")).getText());

        System.out.println("Количество непрочитанных писем: " + numberOfUnreadMessages);

        //фильтруем по непрочитанным
        webDriver.findElement(By.xpath("//*[@id=\"app-canvas\"]/div/div[1]/div[1]/div/div[1]/span/div[2]/table/tbody/tr/td[2]/div/div/div/div/div[1]/div")).click();
        webDriver.findElement(By.xpath("//*[@id=\"app-canvas\"]/div/div[1]/div[1]/div/div[1]/span/div[2]/table/tbody/tr/td[2]/div/div/div/div/div[2]/div")).findElements(By.tagName("div")).get(2).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dataset__items")));

        //получаем список непрочитанных писем
        List<WebElement> unreadMessages = webDriver.findElement(By.className("dataset__items")).findElements(By.className("js-letter-list-item"));

        while(unreadMessages.size() > 0){
            unreadMessages.get(0).findElement(By.className("ll-rs_is-active")).click();
            Thread.sleep(1000);
            unreadMessages = webDriver.findElement(By.className("dataset__items")).findElements(By.className("js-letter-list-item"));
        }
    }

    public static void seleniumTaskPart2(String login, String password){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://mail.ru");

        LoginPage loginPage = new LoginPage(webDriver);

        //выполняем вход
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.submitCredentials();

        MailboxPage mailboxPage = new MailboxPage(webDriver);

        //фильтруем письма
        mailboxPage.filterAndGetNewMessages();
        System.out.println("Непрочитанных сообщений: " + mailboxPage.getNewMessagesCount());

        //отмечаем письма как прочитанные
        mailboxPage.markMessagesReadOneByOne();

    }

}