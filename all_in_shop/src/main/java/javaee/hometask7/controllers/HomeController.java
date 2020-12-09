package javaee.hometask7.controllers;

import javaee.hometask7.entities.*;
import javaee.hometask7.services.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.sax.SAXResult;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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
    private PicturesService picturesService;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    private List<BasketItem> basket = new ArrayList<>();
    private List<BasketItem> soldBasket = new ArrayList<>();

    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.avatar.defaultPicture}")
    private String defPicture;

    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            response.addCookie(new Cookie("language", "en"));
        }
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
    public String category(Model model, @PathVariable(name = "cat_id") Long cat_id, HttpServletRequest request) {
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
        List<Pictures> pictures = picturesService.getPicturesByItem(itemService.getItem(id));
        model.addAttribute("pictures", pictures);
        model.addAttribute("added", request.getRequestURI().contains("added"));
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
    public String code403(HttpServletRequest request) {
        lng_config(request);
        return "403";
    }

    @GetMapping(value = "/login")
    @PreAuthorize("isAnonymous()")
    public String login(HttpServletRequest request) {
        lng_config(request);
        if (request.getParameter("error") != null) {
            request.setAttribute("error", "999");
        }
        return "login";
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String admin() {
        return "redirect:/admin/items";
    }


    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(HttpServletRequest request) {
        lng_config(request);
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }
        if (request.getParameter("success") != null) {
            request.setAttribute("success", "999");
        }
        return "profile";
    }

    @PostMapping(value = "/update_profile")
    @PreAuthorize("isAuthenticated()")
    public String updateProfile(@RequestParam("email") String email,
                                @RequestParam("name") String fullName) {
        Users user = getUserData();
        user.setEmail(email);
        user.setFullName(fullName);
        userService.saveUser(user);
        User user1 = new User(email, user.getPassword(), user.getRoles());
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user1, authentication1.getCredentials(), authentication1.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return "redirect:/profile";
    }

    @GetMapping(value = "/register")
    @PreAuthorize("isAnonymous()")
    public String registerPage(HttpServletRequest request) {
        lng_config(request);
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }
        return "register";
    }

    @GetMapping(value = "/viewphoto/{url}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public @ResponseBody
    byte[] viewProfilePhoto(@PathVariable(name = "url") String url) throws IOException {
        String pictureURL = viewPath + defPicture;
        if (url != null && !url.equals("null")) {
            pictureURL = viewPath + url + ".jpg";

        }
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(pictureURL);
            in = resource.getInputStream();
        } catch (Exception e) {
            ClassPathResource resource = new ClassPathResource(viewPath + defPicture);
            in = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(in);
    }


    @PostMapping(value = "/register")
    @PreAuthorize("isAnonymous()")
    public String register(@RequestParam("email") String email,
                           @RequestParam("name") String fullName,
                           @RequestParam("new_pass") String pass1,
                           @RequestParam("confirm_pass") String pass2) {
        if (pass1.equals(pass2)) {
            List<Roles> roles = new ArrayList<>();
            Roles role = userService.getRole("ROLE_USER");
            roles.add(role);
            pass1 = encoder.encode(pass1);
            Users user = new Users(null, email, pass1, fullName, null, roles);
            userService.addUser(user);
            return "redirect:/login";
        }
        return "redirect:/register?error=confirm";
    }

    @PostMapping(value = "/update_avatar")
    @PreAuthorize("isAuthenticated()")
    public String updateAvatar(@RequestParam("avatar") MultipartFile file) {
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                String picName = DigestUtils.sha1Hex("avatar_" + getUserData().getId() + "_!Picture");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);
                Users user = getUserData();
                user.setPictureUrl(picName);
                userService.saveUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/profile?success";
        }
        return "redirect:/profile?error=image";
    }

    @PostMapping(value = "/update_password")
    @PreAuthorize("isAuthenticated()")
    public String passUpdate(@RequestParam("old_pass") String oldPass,
                             @RequestParam("new_pass") String newPass,
                             @RequestParam("confirm_pass") String newPass2) {
        Users user = getUserData();
        if (!encoder.matches(oldPass, user.getPassword())) {
            return "redirect:/profile?error=pass";
        } else {
            if (!(newPass.equals(newPass2))) {
                return "redirect:/profile?error=confirm";
            } else {
                newPass = encoder.encode(newPass);
                user.setPassword(newPass);
                userService.saveUser(user);
                User user1 = new User(user.getEmail(), user.getPassword(), user.getRoles());
                Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
                Authentication authentication = new UsernamePasswordAuthenticationToken(user1, authentication1.getCredentials(), authentication1.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return "redirect:/profile?success";
            }
        }
    }

    @GetMapping(value = "/add_basket")
    public String addBasket(@RequestParam("id") Long item_id,
                            HttpServletResponse response) {
        Items item = itemService.getItem(item_id);
        System.out.println(item.getName());
        BasketItem item1 = new BasketItem(item_id.toString()+"_"+item.getName(), item.getPrice(), 1, null);
        basket.add(item1);
        response.addCookie(new Cookie(item_id.toString(), Double.toString(item.getPrice())));
        return "redirect:/details/" + item_id + "_added" + item.getName();
    }

    @GetMapping(value = "/basket")
    public String Basket(HttpServletRequest request) {
        request.setAttribute("basket", basket);
        String success = request.getParameter("success");
        if (success!=null)
            request.setAttribute("success", "true");
        double total = 0;
        for (BasketItem item : basket
        ) {
            total += item.getPrice() * item.getAmount();
        }
        request.setAttribute("total", total);
        lng_config(request);
        return "basket";
    }
    @GetMapping(value = "/sold")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String soldBasket(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("sold_basket", soldBasket);
        double total = 0;
        for (BasketItem item : soldBasket
        ) {

            total += item.getPrice() * item.getAmount();
        }
        request.setAttribute("total", total);
        lng_config(request);
        return "buy_log";
    }

    @GetMapping(value = "/clear_basket")
    public String clearBasket(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies
             ) {
            if (c.getName().equals("language"))
                continue;
            else{
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        basket.clear();
        return "redirect:/basket";
    }
    @PostMapping(value = "/check_in")
    public String checkIn(HttpServletRequest request,
                          HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies
        ) {
            if (c.getName().equals("language"))
                continue;
            else{
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        for (BasketItem item: basket
             ) {
            item.setDate(new Date());
            soldBasket.add(item);
        }
        basket.clear();
        return "redirect:/basket?success";
    }

    @GetMapping(value = "/inc_amount")
    public String incAmount(@RequestParam("name") String name) {
        for (BasketItem item : basket) {
            if (item.getName().equals(name)) {
                item.setAmount(item.getAmount() + 1);
            }
        }
        return "redirect:/basket";
    }

    @GetMapping(value = "/dec_amount")
    public String decAmount(@RequestParam("name") String name,
                            HttpServletResponse response,
                            HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (BasketItem item : basket) {
            if (item.getName().equals(name)) {
                if (item.getAmount() > 1) {
                    item.setAmount(item.getAmount() - 1);
                    break;
                } else if (item.getAmount() == 1) {
                    for (Cookie c: cookies
                         ) {
                        if (c.getName().equals(name.split("_")[0])){
                            c.setMaxAge(0);
                            response.addCookie(c);
                            break;
                        }
                    }
                    basket.remove(item);
                    break;
                }
            }
        }
        return "redirect:/basket";
    }


    private Users getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
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
        request.setAttribute("basketsize", basket.size());
        String lng_param = request.getParameter("lng");
        Cookie[] cookies = request.getCookies();
        if (lng_param != null) {
            request.setAttribute("lng", lng_param);
        } else {
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("language")) {
                        request.setAttribute("lng", c.getValue());
                        break;
                    }
                }
            }
        }

    }
}
