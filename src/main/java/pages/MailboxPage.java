package pages;

import javafx.animation.ScaleTransition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailboxPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    private int newMessagesCount;

    private List<WebElement> unreadMessages;

    @FindBy(className = "dataset__items")
    private WebElement messagesTable;

    @FindBy(id = "g_mail_events")
    private WebElement newMessagesBubble;

    @FindBy(xpath = "//*[@id=\"app-canvas\"]/div/div[1]/div[1]/div/div[1]/span/div[2]/table/tbody/tr/td[2]/div/div/div/div/div[1]/div")
    private WebElement filterDialog;

    @FindBy(xpath = "//*[@id=\"app-canvas\"]/div/div[1]/div[1]/div/div[1]/span/div[2]/table/tbody/tr/td[2]/div/div/div/div/div[2]/div")
    private WebElement filterMessagesContainer;

    public MailboxPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }

    private void waitMessagesDownload() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dataset__items")));
    }

    public int getNewMessagesCount() {
        return Integer.parseInt(newMessagesBubble.getText());
    }

    public void filterAndGetNewMessages() {
        waitMessagesDownload();

        filterDialog.click();
        filterMessagesContainer.findElements(By.tagName("div")).get(2).click();

        waitMessagesDownload();
    }

    public void markMessagesReadOneByOne() {
        unreadMessages = messagesTable.findElements(By.className("js-letter-list-item"));

        try {
            while (unreadMessages.size() > 0) {
                unreadMessages.get(0).findElement(By.className("ll-rs_is-active")).click();
                Thread.sleep(1000);
                unreadMessages = messagesTable.findElements(By.className("js-letter-list-item"));
            }
        }
        catch(InterruptedException e){
            System.out.println(e.toString());
        }
    }

}
