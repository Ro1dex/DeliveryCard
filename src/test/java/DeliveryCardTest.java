import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    @Test
    public void shouldDeliveryCard(){

        open("http://localhost:9999");

        $("[placeholder=\"Город\"]").setValue("Калининград");

        //по условиям дату нужно заполнять через прямой ввод, но завтра уже сборка упадёт
        //поэтому дату будем спрашивать у метода java.time
        //текущее локальное время и плюс 3 дня по условиям требования к содержимому поля "дата"
        LocalDate currentDate = LocalDate.now().plusDays(3);
        SelenideElement dateField = $("[placeholder=\"Дата встречи\"]");

        //к сожалению я не смог ничего удалить методом dateField.clear();
        //так как наверно оно имеет какие-то ограничение,из-за календаря (наверно)
        //поэтому сымитировал ручное удаление в поле ввода методом >Keys<
        dateField.click();
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateField.sendKeys(Keys.chord(Keys.BACK_SPACE));

        //вводимый паттерн даты толжен соответствовать >dd/MM/yyyy< по умолчанию >yyyy/MM/dd<
        dateField.setValue(currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


        $("[name='name']").setValue("Ширгин Александр");
        $("[name='phone']").setValue("+79814750000");
        $("[data-test-id='agreement']").click();

        //button не один, есть еще в выпадающем календаре, по умолчанию эти кнопки скрыты
        $$("button").find(Condition.exactText("Забронировать")).click();

        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));


    }

}
