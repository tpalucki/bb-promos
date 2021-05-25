package io.github.tpalucki.bbpromo.controller;

import io.github.tpalucki.bbpromo.model.Article;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Log
@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView displayHomePage(Map<String, Object> model) {
        log.info("Display home page");

        model.put("pageTitle", "BB Promos");
        model.put("articles", List.of(
                new Article("Set A", "01.04.2021", "1234", "This is some piece of description"),
                new Article("Set B", "01.04.2021", "72863", "Lorem ipsum et dolores summit")
        ));

        return new ModelAndView("index", model);
    }

}
