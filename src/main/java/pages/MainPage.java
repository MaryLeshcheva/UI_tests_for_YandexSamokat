package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static config.WebDriverConfig.WAIT_SECONDS_TIMEOUT;

public class MainPage {

    //Локатор кнопки "Заказать"
    private final By buttonOrder  = By.className("Button_Button__ra12g");

    //Локатор вопроса в разделе "Вопросы о важном"
    private final By questions = By.className("accordion__button");

    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод получения ответов
    public String[] getAnswers() {
        List<WebElement> questionElements = driver.findElements(questions);
        List<String> answers = new ArrayList<String>() {};
        for (int i = 0; i < questionElements.size(); i++) {
            WebElement questionElement = questionElements.get(i);
            scroll(questionElement);
            questionElement.click();
            By answerLocator = By.id("accordion__panel-" + i);
            WebElement answerElement = driver.findElement(answerLocator);
            new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS_TIMEOUT))
                    .until(ExpectedConditions.textMatches(answerLocator, Pattern.compile(".+")));
            String answer = answerElement.getText();
            answers.add(answer);
        }

        return answers.toArray(new String[0]);
    }

    //Метод клика по одной из кнопок "Заказать"
    public void clickButtonOrder(int orderButtonIndex) {
       List<WebElement> orderButtons = driver.findElements(buttonOrder);
       WebElement orderButton = orderButtons.get(orderButtonIndex);
       scroll(orderButton);
       orderButton.click();
    }

    //Метод скролла до элемента
    private void scroll(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
