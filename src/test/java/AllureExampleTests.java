import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class AllureExampleTests extends TestBase {

    @Test
    @Owner("olgakos")
    //@Severity(SeverityLevel.MINOR)
    @Feature("Задачи (issue) в репозитории")
    @DisplayName("Проверка наличия Issue с названием \"TestTitle: generate docs with TestOps\": через листнер")
    void listenerAllureTest() {
        // Открыть https://github.com/olgakos/allure-examples
        open(baseUrl);

        //Перейти в репозиторий
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("olgakos/allure-examples");
        $(".header-search-input").submit();

        $(By.linkText("olgakos/allure-examples")).click();

        // Перейти в issue
        $(By.partialLinkText("Issues")).click();

        // check: существует issue с названием "TestTitle: generate docs with TestOps"
        $("#issue_1_link").shouldHave(Condition.text("TestTitle: generate docs with TestOps"));
    }

    @Test
    @Owner("olgakos")
    //@Severity(SeverityLevel.CRITICAL)
    @Feature("Задачи (issue) в репозитории")
    @DisplayName("Проверка существования Issue с названием \"TestTitle: generate docs with TestOps\": через лямбда степы")
    void LambdaStepsAllureTest() {

        String REPOSITORY = "olgakos/allure-examples";
        String ISSUE_DATA = "TestTitle: generate docs with TestOps";

        step("Открываем главную страницу https://github.com", () -> {
            open(baseUrl);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Открываем репозиторий " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Перейти во вкладку Issues", () -> {
            $(By.partialLinkText("Issues")).click();
        });
        step("Проверка наличия Issue с названием:" + ISSUE_DATA, () -> {
            $("#issue_1_link").shouldHave(Condition.text(ISSUE_DATA));
        });

    }}