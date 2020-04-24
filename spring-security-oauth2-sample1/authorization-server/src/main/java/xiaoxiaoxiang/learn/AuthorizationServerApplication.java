package xiaoxiaoxiang.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class AuthorizationServerApplication implements ApplicationListener<ApplicationReadyEvent> {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        String aaa = System.getenv("abc");
        System.out.println(aaa);
    }


}
