package other;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@NoArgsConstructor

@RestController
public class OtherController {


    @GetMapping("/other")
    public String other(String name) {
        log.trace("other({}) invoked.", name);

        return "Other, " + name;
    } // other

} // end class
