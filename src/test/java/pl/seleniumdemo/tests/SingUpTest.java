package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SingUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        driver.findElements(By.xpath("//li[@id = 'li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text() = '  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        String lastName = "Adamus";
        int randomNumber = (int) (Math.random()*1000);
        String email = "bartosz.adamus" + randomNumber + "@gmail.com";
        driver.findElement(By.name("firstname")).sendKeys("Bartosz");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("785072264");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("QWERTY123123");
        driver.findElement(By.name("confirmpassword")).sendKeys("QWERTY123123");
        driver.findElement(By.xpath("//button[text() = ' Sign Up']")).click();
        WebElement heading = driver.findElement(By.xpath("//h3[text() = 'Hi, Bartosz Adamus']"));
        Assert.assertTrue(heading.getText().contains(lastName));
    }
    @Test
    public void singUpTestNoInf() {
        driver.findElements(By.xpath("//li[@id = 'li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text() = '  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElement(By.xpath("//button[text() = ' Sign Up']")).click();
        List<String> Req = driver.findElements(By.xpath("//div[@class = 'alert alert-danger']//p"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        Assert.assertEquals("The Email field is required.",Req.get(0));
        Assert.assertEquals("The Password field is required.",Req.get(1));
        Assert.assertEquals("The Password field is required.",Req.get(2));
        Assert.assertEquals("The First name field is required.",Req.get(3));
        Assert.assertEquals("The Last Name field is required.",Req.get(4));
    }
    @Test
    public void signUpTestWrongEmail() {
        driver.findElements(By.xpath("//li[@id = 'li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text() = '  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        String lastName = "Adamus";
        int randomNumber = (int) (Math.random()*1000);
        String email = "bartosz.adamus" + randomNumber;
        driver.findElement(By.name("firstname")).sendKeys("Bartosz");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("785072264");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("QWERTY123123");
        driver.findElement(By.name("confirmpassword")).sendKeys("QWERTY123123");
        driver.findElement(By.xpath("//button[text() = ' Sign Up']")).click();

        String WrongEmail = driver.findElement(By.xpath("//p[text() = 'The Email field must contain a valid email address.']")).getAttribute("textContent");
        Assert.assertEquals("The Email field must contain a valid email address.",WrongEmail);
      }
    }
