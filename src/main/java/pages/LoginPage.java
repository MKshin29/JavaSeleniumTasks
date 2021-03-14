package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(name = "login")
    private WebElement loginInput;

    //@FindBy(xpath = "//*[@id=\"mailbox\"]/form[1]/button[1]")
    @FindBy(xpath = "//button[contains(text(), \"Ввести пароль\")]")
    private WebElement prePasswordButton;

    @FindBy(name = "password")
    private WebElement passwordInput;

    //@FindBy(xpath = "//*[@id=\"mailbox\"]/form[1]/button[2]")
    @FindBy(xpath = "//button[contains(text(), \"Войти\")][@data-testid='login-to-mail']")
    private WebElement submitCredentialsButton;

    public LoginPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);

        PageFactory.initElements(webDriver, this);
    }

    public void enterLogin(String login) {
        loginInput.sendKeys("");
        loginInput.sendKeys(login);
        prePasswordButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
    }

    public void enterPassword(String pass) {
        passwordInput.sendKeys("");
        passwordInput.sendKeys(pass);
    }

    public void submitCredentials() {
        submitCredentialsButton.click();
    }


}
