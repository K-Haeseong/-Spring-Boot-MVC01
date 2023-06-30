package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info = {}", name);
        log.warn("warn={}", name);
        log.error("error={}", name);

        log.trace("String concat log=" + name);
        // trace로그를 사용하지도 않는데 문자열 더하기가 일어난다.
        // 쓸모 없는 리소스를 사용하게 된다.
        return "OK";
    }

}
