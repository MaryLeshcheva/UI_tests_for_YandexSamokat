import expansion.WebDriverFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.Order;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class TestCheckOrder {
    private WebDriver driver;

    private final int buttonOrderIndex;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStationIndex;
    private final String phone;
    private final String date;
    private final int durationIndex;
    private final String [] colors;
    private final String comment;


    public TestCheckOrder(int buttonOrderIndex, String firstName, String lastName, String address, int metroStationIndex, String phone, String date, int durationIndex, String [] colors, String comment) {
        this.buttonOrderIndex = buttonOrderIndex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStationIndex = metroStationIndex;
        this.phone = phone;
        this.date = date;
        this.durationIndex = durationIndex;
        this.colors = colors;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getFormData() {
        return new Object[][] {
           {0, "Анна", "Иванова", "улица Ленина, дом 1", 0, "+71234567891", "10.03.2023", 0, new String[] {}, null},
           {2, "Олег", "Иванов", "Петрова 23", 54, "+89999999999", null, 2, new String[] {"black", "grey"}, "Домофон не работает. Звоните по телефону."},
        };
    }

    @Before
    public void setup() {
        driver = WebDriverFactory.get();
    }

    @Test
    public void checkOrder() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickButtonOrder(buttonOrderIndex);

        Order objOrder = new Order(driver);
        objOrder.fillFirstName(firstName);
        objOrder.fillLastName(lastName);
        objOrder.fillAddress(address);
        objOrder.selectMetroStation(metroStationIndex);
        objOrder.fillPhone(phone);
        objOrder.clickNextButton();
        if (date == null) {
            objOrder.selectDateCalendar();
            objOrder.clickTitleAboutRent();
        }
        else {
            objOrder.fillDateCalendar(date);
            objOrder.clickTitleAboutRent();
        }
        objOrder.selectDuration(durationIndex);
        objOrder.selectColors(colors);
        if (comment != null) {
            objOrder.fillComment(comment);
        }
        objOrder.clickOrderValidButton();
        objOrder.clickButtonYes();
        assertThat(objOrder.getModalTitleText(), startsWith("Заказ оформлен"));
    }

    @After
    public void tearDown() {
        // Закрой браузер
        System.out.print("Test is closed");
        driver.quit();
    }
}
