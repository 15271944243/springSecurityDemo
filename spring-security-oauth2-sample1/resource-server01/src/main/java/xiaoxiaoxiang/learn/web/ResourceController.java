package xiaoxiaoxiang.learn.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @description:
 * @author: xiaoxiaoxiang
 * @date: 2019/12/31 18:23
 */
@RestController
public class ResourceController {

    @GetMapping("/messages")
    public String getMessages(Principal principal) {
        System.out.println(principal.getName());
        return "success";
    }
}
