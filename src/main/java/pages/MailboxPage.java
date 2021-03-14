package pages;

import exceptions.UnreadMessageException;
import javafx.animation.ScaleTransition;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailboxPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    private int newMessagesCount;

    @FindBys({
            @FindBy(className = "ll-rs_is-active")
    })
    private List<WebElement> unreadMessages;

    @FindBy(className = "dataset__items")
    private WebElement messagesTable;

    @FindBy(id = "g_mail_events")
    private WebElement newMessagesBubble;

    //@FindBy(xpath = "//*[@id=\"app-canvas\"]/div/div[1]/div[1]/div/div[1]/span/div[2]/table/tbody/tr/td[2]/div/div/div/div/div[1]/div")
    @FindBy(xpath = "//div[@class='filters-control__text filters-control_default-filter']")
    private WebElement filterDialog;

//    @FindBy(xpath = "//*[@id=\"app-canvas\"]/div/div[1]/div[1]/div/div[1]/span/div[2]/table/tbody/tr/td[2]/div/div/div/div/div[2]/div")
//    private WebElement filterMessagesContainer;

    @FindBy(xpath = "//div[@class='list list_hover-support']/div[2]")
    private WebElement unreadMessagesFilterChoice;

//    @FindBy(xpath = "//a[@class='llc js-tooltip-direction_letter-bottom js-letter-list-item llc_normal']")
//    private WebElement topUnreadMessage;

    @FindBy(xpath = "//span[@class='ll-rs ll-rs_is-active']")
    private WebElement topUnreadMessageMark;

//----------methods-----------------------------------------------------------------------------------------

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
        //filterMessagesContainer.findElements(By.tagName("div")).get(2).click();
        unreadMessagesFilterChoice.click();

        waitMessagesDownload();
    }

    public void markMessagesReadOneByOne(int timeout) {

        int messagesCount = unreadMessages.size();

        while (unreadMessages.size() > 0){
            try {
                topUnreadMessageMark.click();
                Long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < timeout * 1000 && messagesCount == unreadMessages.size()) {
                }

                if (messagesCount == unreadMessages.size()) {
                    throw new UnreadMessageException("Сообщение не было отмечено как прочитанное за таймаут");
                }
                else {
                    messagesCount = unreadMessages.size();
                }
            }
            catch (UnreadMessageException e){
                System.out.println(e);
                break;
            }
        }
        
    }

}
