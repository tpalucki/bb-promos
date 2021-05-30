package io.github.tpalucki.bbpromo.controller;

import io.github.tpalucki.bbpromo.model.PriceDetails;
import io.github.tpalucki.bbpromo.model.ProductDetails;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

@Log
@Controller
public class ProductDetailsController {

    @GetMapping("/products/{productNumber}")
    public ModelAndView displayProductDetails(@PathVariable String productNumber, Map<String, Object> model) {
        log.info("Show details of product " + productNumber);

        model.put("pageTitle", "BB Promos");
        model.put("productDetails", provideProductDetails());
        model.put("priceComparison", providePriceComparison());

        return new ModelAndView("product", model);
    }

    private List<PriceDetails> providePriceComparison() {
        return List.of(
                new PriceDetails("Allegro", List.of("Darmowa dostawa z Allegro Smart! dla zamówień od 40zł"),
                        BigDecimal.valueOf(12799L, 2), Currency.getInstance("PLN"), true),
                new PriceDetails("OLX", List.of(),
                        BigDecimal.valueOf(13999L, 2), Currency.getInstance("PLN"), false),
                new PriceDetails("Smyk.com", List.of("Darmowy odbiór w salonach Smyk."),
                        BigDecimal.valueOf(13999L, 2), Currency.getInstance("PLN"), false),
                new PriceDetails("Bonito.pl", List.of("Darmowy odbiór w punktach odbioru bonito.pl", "Darmowa wysyłka od 100zł."),
                        BigDecimal.valueOf(12799L, 2), Currency.getInstance("PLN"), true)
        );
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
