package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        Locale.setDefault(Locale.KOREA);
        String result = ms.getMessage("hello", null, null);
        System.out.println(result);
        assertThat(result).isEqualTo("hello");
    }
    @Test
    void notFoundMessageCode(){
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }
    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        System.out.println(result);
        assertThat(result).isEqualTo("기본 메시지");
    }
    @Test
    void argsMessage(){
        String result = ms.getMessage("hello.name", new Object[]{"spring"}, null);
        assertThat(result).isEqualTo("hello spring");
    }

}
