package io.github.tpalucki.bbpromo.controller;

import io.github.tpalucki.bbpromo.model.ProductDetails;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

@Log
@Controller
public class ProductDetailsController {

    @GetMapping("/products/{productNumber}")
    public ModelAndView displayProductDetails(@PathVariable String productNumber, Map<String, Object> model) {
        log.info("Show details of product " + productNumber);

        model.put("pageTitle", "BB Promos");
        model.put("productDetails", provideProductDetails());

        return new ModelAndView("product", model);
    }

    private ProductDetails provideProductDetails() {
        return new ProductDetails(
                "LEGO® 10775 Disney - Farma Mikiego i Donalda",
                "Mickey Mouse & Donald Duck's Farm",
                10775,
                "LEGO Disney",
                118,
                "2021",
                BigDecimal.valueOf(13999L, 2),
                BigDecimal.valueOf(12799L, 2),
                "Allegro, 26.05.2021",
                Currency.getInstance("PLN"),
                4
        );
    }
}
