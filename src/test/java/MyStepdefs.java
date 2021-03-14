import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;

import java.util.List;

public class MyStepdefs {

    @Дано("^у клиента есть (.*)$")
    public void уКлиентаЕстьЛогинПароль(List<String> args) {

        throw new io.cucumber.java.PendingException();
    }

    @Когда("^пользователь вводит корректный (.*)$")
    public void пользовательВводитКорректныйЛогинПароль(List<String> args) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Тогда("авторизация на сайте заканчивается успешно")
    public void авторизацияНаСайтеЗаканчиваетсяУспешно() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
