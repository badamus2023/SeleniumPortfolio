package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SingUpTest extends BaseTest {

    @Test
    public void signUpTest() {

        String lastName = "Adamus";
        int randomNumber = (int) (Math.random()*1000);
        String email = "bartosz.adamus" + randomNumber + "@gmail.com";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bartosz");
        signUpPage.setlastName(lastName);
        signUpPage.setPhoneNumber("123123123");
        signUpPage.setEmail(email);
        signUpPage.setPassword("QWERTY123");
        signUpPage.setConfirmPassword("QWERTY123");
        signUpPage.signUpButton();

        WebElement heading = driver.findElement(By.xpath("//h3[text() = 'Hi, Bartosz Adamus']"));
        Assert.assertTrue(heading.getText().contains(lastName));
    }
    @Test
    public void singUpTestNoInf() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUpButton();
        List<String> Req = signUpPage.getRequirements();

        Assert.assertEquals(Req.get(0),"The Email field is required.");
        Assert.assertEquals(Req.get(1),"The Password field is required.");
        Assert.assertEquals(Req.get(2),"The Password field is required.");
        Assert.assertEquals(Req.get(3),"The First name field is required.");
        Assert.assertEquals(Req.get(4),"The Last Name field is required.");
    }
    @Test
    public void signUpTestWrongEmail() {


        String lastName = "Adamus";
        int randomNumber = (int) (Math.random()*1000);
        String email = "bartosz.adamus" + randomNumber;

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Bartosz");
        signUpPage.setlastName(lastName);
        signUpPage.setPhoneNumber("123123123");
        signUpPage.setEmail(email);
        signUpPage.setPassword("QWERTY123");
        signUpPage.setConfirmPassword("QWERTY123");
        signUpPage.signUpButton();

        String WrongEmail = driver.findElement(By.xpath("//p[text() = 'The Email field must contain a valid email address.']")).getAttribute("textContent");
        Assert.assertEquals("The Email field must contain a valid email address.",WrongEmail);
      }
    }
