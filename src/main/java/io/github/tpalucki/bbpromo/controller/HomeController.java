package io.github.tpalucki.bbpromo.controller;

import io.github.tpalucki.bbpromo.model.SetInfo;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log
@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView displayHomePageWithSearch(Map<String, Object> model,
                                                  @RequestParam(required = false) String query,
                                                  @RequestParam(required = false) Integer page) {
        log.info("Display home page");
        Optional.ofNullable(query).ifPresent(value -> log.info("Query: " + value));
        Optional.ofNullable(page).ifPresent(value -> log.info("Page: " + value));

        model.put("pageTitle", "BB Promos");
        model.put("sets", provideSetsInfo());

        return new ModelAndView("index", model);
    }

    private List<SetInfo> provideSetsInfo() {
        return List.of(
                new SetInfo("LEGO Disney 10775 - Farma Mikiego i Donalda", "2021", 118,
                        BigDecimal.valueOf(12799L, 2), Currency.getInstance("PLN"), 10775),
                new SetInfo("LEGO Marvel Superheroes 76193 - Statek strażników", "2021", 1901,
                        BigDecimal.valueOf(56999L, 2), Currency.getInstance("PLN"), 76193)
        );
    }

}
