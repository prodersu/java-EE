package javaee.hometask7.controllers;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Countries;
import javaee.hometask7.entities.Items;
import javaee.hometask7.services.ItemService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request) {
        func(model, request);
        List<Items> items = itemService.getInTopPageItems();
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping(value = "/all")
    public String all(Model model, HttpServletRequest request) {
        func(model, request);
        return "all_items";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") String _id, HttpServletRequest request) {
        func(model, request);
        Long id = Long.parseLong(_id.split("_")[0]);
        model.addAttribute("item", itemService.getItem(id));

        return "details";
    }

    @GetMapping(value = "/edit/{id}")
    public String editPage(Model model, HttpServletRequest request, @PathVariable(name = "id") String _id) {
        func(model, request);
        Long id = Long.parseLong(_id.split("_")[0]);
        model.addAttribute("item", itemService.getItem(id));

        return "edit";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") Long id) {
        itemService.deleteItem(itemService.getItem(id));
        return "redirect:/";
    }

    @PostMapping(value = "/add_item")
    public String add_item(@RequestParam("name") String name,
                           @RequestParam("desc") String desc,
                           @RequestParam("price") double price,
                           @RequestParam("in_top_page") boolean in_top_page,
                           @RequestParam("small_pic_url") String small_pic,
                           @RequestParam("large_pic_url") String large_pic,
                           @RequestParam("rating") int stars,
                           @RequestParam("brand_id") Long brand_id
    ) {
        itemService.addItem(new Items(null, name, desc, price, stars, small_pic, large_pic,
                new Date(), in_top_page, itemService.getBrand(brand_id)));
        return "redirect:/all";
    }

    @PostMapping(value = "/edit")
    public String edit(@RequestParam("name") String name,
                       @RequestParam("desc") String desc,
                       @RequestParam("price") double price,
                       @RequestParam("in_top_page") boolean in_top_page,
                       @RequestParam("small_pic_url") String small_pic,
                       @RequestParam("large_pic_url") String large_pic,
                       @RequestParam("rating") int stars,
                       @RequestParam("id") Long id,
                       @RequestParam("brand_id") Long brand_id
    ) {
        itemService.saveItem(new Items(id, name, desc, price, stars, small_pic, large_pic, new Date(), in_top_page, itemService.getBrand(brand_id)));
        return "redirect:/";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "query", defaultValue = "") String query,
                         @RequestParam(value = "order", defaultValue = "asc") String order,
                         Model model, HttpServletRequest request) {
        func(model, request);
        List<Items> items;
        if (order.equals("desc"))
            items = itemService.getItemsByQueryOrderByPriceDesc(query);
        else
            items = itemService.getItemsByQueryAsc(query);

        model.addAttribute("items1", items);
        model.addAttribute("name", query);
        model.addAttribute("query", request.getQueryString());
        return "search";
    }

    @GetMapping(value = "/search/advanced")
    public String search(Model model, @RequestParam(value = "query", defaultValue = "") String name,
                         @RequestParam(value = "price_from", defaultValue = "0") double price1,
                         @RequestParam(value = "price_to", defaultValue = "1000000") double price2,
                         @RequestParam(value = "order", defaultValue = "asc") String order,
                         @RequestParam(value = "brand", defaultValue = "1") Long brand,
                         HttpServletRequest request) {
        func(model, request);
        List<Items> items;
        if (order.equals("desc"))
            items = itemService.getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceDesc(name, itemService.getBrand(brand), price1, price2);
        else
            items = itemService.getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceAsc(name, itemService.getBrand(brand), price1, price2);
        model.addAttribute("items1", items);
        model.addAttribute("brand_id", brand);
        model.addAttribute("name", name);
        model.addAttribute("price1", price1);
        model.addAttribute("price2", price2);
        model.addAttribute("query", request.getQueryString());
        return "search";
    }

    @GetMapping(value = "/country")
    public String country(Model model, HttpServletRequest request,
                          @RequestParam(value = "id") Long country_id) {
        func(model, request);
        List<Brands> brands = itemService.getAllByCountriesIs(itemService.getCountry(country_id));
        model.addAttribute("brands1", brands);
        Countries country = itemService.getCountry(country_id);
        model.addAttribute("country", country);
        return "country";
    }

    public void func(Model model, HttpServletRequest request) {
        List<Brands> brands = itemService.getAllBrands();
        List<Items> items = itemService.getAllItems();
        model.addAttribute("brands", brands);
        model.addAttribute("items", items);
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("language")){
                model.addAttribute("lng", c.getValue());
                break;
            }
        }
    }
}
