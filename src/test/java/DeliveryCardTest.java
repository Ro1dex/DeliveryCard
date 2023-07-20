import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    @Test
    public void shouldDeliveryCard(){

        open("http://localhost:9999");

        $("[placeholder=\"Город\"]").setValue("Калининград");

        SelenideElement dateField = $("[placeholder=\"Дата встречи\"]");

        dateField.click();
        dateField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateField.sendKeys(Keys.chord(Keys.BACK_SPACE));

        String generateNewDate = DataGenerate.generate();
        dateField.setValue(generateNewDate);

        $("[name='name']").setValue("Ширгин Александр");

        $("[name='phone']").setValue("+79814750000");

        $("[data-test-id='agreement']").click();

        //button не один, есть еще в выпадающем календаре, по умолчанию эти кнопки скрыты
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

        $(withText(generateNewDate)).shouldBe(Condition.visible, Duration.ofSeconds(15));


    }

}
