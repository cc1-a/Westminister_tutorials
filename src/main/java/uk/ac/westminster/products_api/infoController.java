package uk.ac.westminster.products_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class infoController {

    @GetMapping("/getinfo")
    public String getINfo(){
        return "My first Springboot application";
    }
}
