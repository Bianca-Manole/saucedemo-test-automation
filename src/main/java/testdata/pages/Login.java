package testdata.pages;

import org.openqa.selenium.By;
import testdata.classes.User;

public class Login {



    public final static By USERNAME_INPUT = By.id("user-name");
    public final static By PASSWORD_INPUT = By.id("password");
    public final static By LOGIN_BUTTON = By.id("login-button");
    public final static By LOGO = By.xpath("//div[contains(text(), 'Swag Labs')]");
    public final static By ERROR = By.xpath("//h3");


    public final static String LOGO_TEXT = "Swag Labs";
    public final static String INVALID_USERNAME_PASSWORD = "Epic sadface: Username and password do not match any user in this service";
    public final static String LOCKED_OUT_USER_ERROR = "Epic sadface: Sorry, this user has been locked out.";
}
