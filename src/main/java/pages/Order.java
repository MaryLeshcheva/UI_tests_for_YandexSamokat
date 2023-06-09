package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Order {

    //Поле "Имя"
    private final By inputFirstName = By.xpath(".//input[@placeholder='* Имя']");

    //Поле "Фамилия"
    private final By inputLastName  = By.xpath(".//input[@placeholder='* Фамилия']");

    //Поле "Адрес: куда привезти заказ"
    private final By inputAddress  = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Кнопка "Станция метро"
    private final By buttonMetroStation  = By.className("select-search__input");

    //Вариант станции метро "Бульвар Рокоссовского"
    private final By selectOptionRokossovskyBoulevard = By.xpath("[data-index='0']");

    //Вариант станции метро "Киевская"
    private final By selectOptionKievskaya = By.xpath("[data-index='54']");

    //Поле "Телефон"
    private final By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка "Далее"
    private final By buttonNext = By.xpath(".//button[text()='Далее']");

    //Кнопка "Когда привезти самокат"
    private final By buttonCalendar = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");

    //Выбор сегодняшней даты в календаре
    private final By selectTodayOption = By.className("react-datepicker__day--today");

    //Кнопка "Срок аренды"
    private final By buttonRentPeriod = By.className("Dropdown-root");

    //Вариант срока аренды
    private final By dropdownOptionRentPeriod= By.className("Dropdown-option");

    //Поле "Комментарий для курьера"
    private final By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка "Заказать" после заполнения всех полей
    private final By buttonOrderValid = By.xpath(".//button[(@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");

    //Кнопка "Да" в поп-ап "Оформить заказ"
    private final By buttonYes = By.xpath(".//button[text()='Да']");

    //Кнопка "Нет" в поп-ап "Оформить заказ"
    private final By buttonNot = By.xpath(".//button[text()='Нет']");

    //Заголовок поп-ап "Заказ оформлен"
    private final By titleTextModal = By.xpath("/html/body/div/div/div[2]/div[5]/div[1]");

    //Заголовок "Про аренду"
    private final By titleAboutRent = By.xpath("/html/body/div/div/div[2]/div[1]");
    private final WebDriver driver;

    public Order(WebDriver driver) {
        this.driver = driver;
    }

    //Метод ввода Имени
    public void fillFirstName(String firstName) {
        driver.findElement(inputFirstName).sendKeys(firstName);
    }

    //Метод ввода Фамилии
    public void fillLastName(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    //Метод ввода адреса
    public void fillAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    //Метод выбора станции метро
    public void selectMetroStation(int metroStationIndex) {
        driver.findElement(buttonMetroStation).click();
        By metroStationSelectOption = By.xpath("//li[@data-index='" + metroStationIndex + "']");
        WebElement metroStationSelectOptionElement = driver.findElement(metroStationSelectOption);
        scroll(metroStationSelectOptionElement);
        metroStationSelectOptionElement.click();
    }

    //Метод ввода телефон
    public void fillPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    //Метод клика по кнопке "Далее"
    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }

    //Метод ввода даты в поле календаря
    public void fillDateCalendar(String date) {
        driver.findElement(buttonCalendar).sendKeys(date);
    }

    //Метод выбора сегодняшней даты в календаре
    public void selectDateCalendar() {
        driver.findElement(buttonCalendar).click();
        driver.findElement(selectTodayOption).click();
    }

    //Метод клика по заголовку "Про аренду", чтобы поле "Срок аренды" было доступно для клика
    public void clickTitleAboutRent() {
        driver.findElement(titleAboutRent).click();
    }

    //Метод выбора срока аренды
    public void selectDuration(int durationIndex) {
        driver.findElement(buttonRentPeriod).click();
        List<WebElement> options = driver.findElements(dropdownOptionRentPeriod);
        WebElement option = options.get(durationIndex);
        option.click();
    }

    //Метод выбора цвета самоката
    public void selectColors(String [] colors) {
        for (String color : colors) {
            By checkbox = By.id(color);
            driver.findElement(checkbox).click();
        }
    }

    //Метод добавления комментария
    public void fillComment(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    //Метод клика по кнопке "Заказать" при всех заполненных полях
    public void clickOrderValidButton() {
        driver.findElement(buttonOrderValid).click();
    }

    //Метод клика по кнопке "Да"
    public void clickButtonYes() {
        driver.findElement(buttonYes).click();
    }

    //Метод проверки заголовка в поп-ап "Заказ оформлен"
    public String getModalTitleText() {
        WebElement titleModal = driver.findElement(titleTextModal);
        return titleModal.getText();
    }

    //Метод скролла до элемента
    private void scroll(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
