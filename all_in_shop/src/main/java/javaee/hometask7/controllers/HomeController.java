package javaee.hometask7.controllers;

import javaee.hometask7.entities.*;
import javaee.hometask7.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Items> items = itemService.getInTopPageItems();
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping(value = "/all")
    public String all(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Items> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "all_items";
    }

    @GetMapping(value = "/cat/{cat_id}")
    public String category(Model model, @PathVariable(name = "cat_id") Long cat_id, HttpServletRequest request){
        lng_config(request);
        Categories category = categoryService.getCategory(cat_id);
        List<Items> items = itemService.getItemsByCategoriesContains(category);
        model.addAttribute("items", items);
        model.addAttribute("cat_name", category.getName());
        return "category";
    }

    @GetMapping(value = "/details/{id}")
    public String details(Model model, @PathVariable(name = "id") String _id, HttpServletRequest request) {
        lng_config(request);
        Long id = Long.parseLong(_id.split("_")[0]);
        model.addAttribute("item", itemService.getItem(id));

        return "details";
    }

    @GetMapping(value = "/search")
    public String search(@RequestParam(value = "query", defaultValue = "") String query,
                         @RequestParam(value = "order", defaultValue = "asc") String order,
                         Model model, HttpServletRequest request) {
        lng_config(request);
        List<Items> items;
        if (order.equals("desc"))
            items = itemService.getItemsByQueryOrderByPriceDesc(query);
        else
            items = itemService.getItemsByQueryAsc(query);

        model.addAttribute("items", items);
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
        lng_config(request);
        List<Items> items;
        if (order.equals("desc"))
            items = itemService.getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceDesc(name, brandService.getBrand(brand), price1, price2);
        else
            items = itemService.getItemsByNameContainsAndBrandsIsAndPriceBetweenOrderByPriceAsc(name, brandService.getBrand(brand), price1, price2);
        model.addAttribute("items", items);
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
        lng_config(request);
        List<Brands> brands = brandService.getAllByCountriesIs(countryService.getCountry(country_id));
        model.addAttribute("brands1", brands);
        Countries country = countryService.getCountry(country_id);
        model.addAttribute("country", country);
        return "country";
    }

    @GetMapping(value = "/403")
    public String code403(HttpServletRequest request){
        lng_config(request);
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(HttpServletRequest request){
        lng_config(request);
        return "login";
    }

    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User) authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;

    }


    public void lng_config(HttpServletRequest request) {
        List<Brands> brands = brandService.getAllBrands();
        List<Categories> categories = categoryService.getAllCategories();
        request.setAttribute("user", getUserData());
        request.setAttribute("brands", brands);
        request.setAttribute("categories", categories);
        String lng_param = request.getParameter("lng");
        Cookie[] cookies = request.getCookies();
        if (lng_param!=null){
            request.setAttribute("lng", lng_param);
        }
        else{
            for (Cookie c: cookies) {
                if (c.getName().equals("language")){
                    request.setAttribute("lng", c.getValue());
                    break;
                }
            }
        }

    }
}
