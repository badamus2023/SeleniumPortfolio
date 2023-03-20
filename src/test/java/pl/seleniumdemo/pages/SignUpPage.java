package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpPage {
    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmpasswordInput;

    @FindBy(xpath = "//button[text() = ' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class = 'alert alert-danger']//p")
    private List<WebElement> ReqList;

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void setlastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void setPhoneNumber(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void setConfirmPassword(String confirmpassword) {
        confirmpasswordInput.sendKeys(confirmpassword);
    }

    public void signUpButton(){
        signUpButton.click();
    }

    public List<String> getRequirements() {
        return   ReqList.stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());
    }
}
