package javaee.hometask7.controllers;

import javaee.hometask7.entities.*;
import javaee.hometask7.services.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private PicturesService picturesService;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.avatar.defaultPicture}")
    private String defPicture;

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String users(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Users> users = userService.getAllUsers();
        List<Roles> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        return "admin_users";
    }

    @GetMapping(value = "/roles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String roles(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Roles> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        return "admin_roles";
    }

    @GetMapping(value = "/categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String categories(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Categories> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin_categories";
    }

    @GetMapping(value = "/brands")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String brands(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Brands> brands = brandService.getAllBrands();
        List<Countries> countries = countryService.getAllCountries();
        model.addAttribute("brands", brands);
        model.addAttribute("countries", countries);
        return "admin_brands";
    }

    @GetMapping(value = "/countries")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String countries(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Countries> countries = countryService.getAllCountries();
        model.addAttribute("countries", countries);
        return "admin_countries";
    }

    @GetMapping(value = "/items")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String items(Model model, HttpServletRequest request) {
        lng_config(request);
        List<Items> items = itemService.getAllItems();
        List<Brands> brands = brandService.getAllBrands();
        List<Categories> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("items", items);
        return "admin_items";
    }

    @PostMapping(value = "/add_user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(@RequestParam("email") String email,
                          @RequestParam("name") String fullName,
                          @RequestParam("new_pass") String pass1,
                          @RequestParam("confirm_pass") String pass2,
                          @RequestParam("role") Long role_id) {
        if (pass1.equals(pass2)) {
            List<Roles> roles = new ArrayList<>();
            Roles role = userService.getRoleById(role_id);
            roles.add(role);
            pass1 = encoder.encode(pass1);
            Users user = new Users(null, email, pass1, fullName, null, roles);
            userService.addUser(user);
            return "redirect:/admin/users";
        }
        return "redirect:/admin/users?error";
    }

    @PostMapping(value = "/update_avatar")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String updateAvatar(@RequestParam("avatar") MultipartFile file,
                               @RequestParam("id") Long id) {
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                String picName = DigestUtils.sha1Hex("avatar_" + getUserData().getId() + "_!Picture");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);
                Users user = userService.getUserById(id);
                user.setPictureUrl(picName);
                userService.saveUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }return "redirect:/admin/user/"+id+"?success";
        }
        return "redirect:/admin/user/"+id+"?error";
    }
    @GetMapping(value = "/delete_pic")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deletePic(@RequestParam("id") Long id) {
        picturesService.deletePicture(picturesService.getPictureById(id));
        return "redirect:/admin/items";
    }
    @PostMapping(value = "/add_pic")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addPic(@RequestParam("pic") MultipartFile file,
                         @RequestParam("item_id") Long id) {
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
            try {
                String picName = DigestUtils.sha1Hex("picture_" + picturesService.getPicturesByItem(itemService.getItem(id)).size() + "_!Picture");
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);
                Pictures picture = new Pictures(null, picName, new Date(),itemService.getItem(id));
                picturesService.addPictures(picture);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/admin/details/"+id+"_item";
        }
        return "redirect:/admin/details/"+id+"_item";
    }
    @PostMapping(value = "/update_profile")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateProfile(@RequestParam("email") String email,
                                @RequestParam("name") String fullName,
                                @RequestParam("id") Long id) {
        Users user = userService.getUserById(id);
        user.setEmail(email);
        user.setFullName(fullName);
        userService.saveUser(user);
        if (user.equals(getUserData())) {
            User user1 = new User(email, user.getPassword(), user.getRoles());
            Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
            Authentication authentication = new UsernamePasswordAuthenticationToken(user1, authentication1.getCredentials(), authentication1.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return "redirect:/admin/user/" + id;
    }

    @PostMapping(value = "/update_password")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String passUpdate(@RequestParam("old_pass") String oldPass,
                             @RequestParam("new_pass") String newPass,
                             @RequestParam("confirm_pass") String newPass2,
                             @RequestParam("id") Long id) {
        Users user = userService.getUserById(id);
        if (!encoder.matches(oldPass, user.getPassword())) {
            return "redirect:/admin/user/" + id + "?error=pass";
        } else {
            if (!(newPass.equals(newPass2))) {
                return "redirect:/admin/user/" + id + "?error=confirm";
            } else {
                newPass = encoder.encode(newPass);
                user.setPassword(newPass);
                userService.saveUser(user);
                if (user.equals(getUserData())) {
                    User user1 = new User(user.getEmail(), user.getPassword(), user.getRoles());
                    Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user1, authentication1.getCredentials(), authentication1.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                return "redirect:/admin/user/" + id + "?success";
            }
        }
    }

    @PostMapping(value = "/add_brand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBrand(@RequestParam("name") String name,
                           @RequestParam("country") Countries country) {
        brandService.addBrand(new Brands(null, name, country));
        return "redirect:/admin/brands";
    }

    @PostMapping(value = "/edit_brand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editBrand(@RequestParam("name") String name,
                            @RequestParam("country") Long country_id,
                            @RequestParam("id") Long id) {
        brandService.saveBrand(new Brands(id, name, countryService.getCountry(country_id)));
        return "redirect:/admin/brands";
    }

    @GetMapping(value = "/delete_brand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBrand(@RequestParam("id") Long id) {
        brandService.deleteBrand(brandService.getBrand(id));
        return "redirect:/admin/brands";
    }

    @PostMapping(value = "/add_category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCategory(@RequestParam("name") String name,
                              @RequestParam("logo_url") String logo_url) {
        categoryService.addCategory(new Categories(null, name, logo_url));
        return "redirect:/admin/categories";
    }

    @PostMapping(value = "/edit_category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCategory(@RequestParam("name") String name,
                               @RequestParam("logo_url") String logoUrl,
                               @RequestParam("id") Long id) {
        categoryService.saveCategory(new Categories(id, name, logoUrl));
        return "redirect:/admin/categories";
    }

    @GetMapping(value = "/delete_category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(categoryService.getCategory(id));
        return "redirect:/admin/categories";
    }

    @PostMapping(value = "/add_country")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCountry(@RequestParam("name") String name,
                             @RequestParam("code") String code) {
        countryService.addCountry(new Countries(null, name, code));
        return "redirect:/admin/countries";
    }

    @PostMapping(value = "/edit_country")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCountry(@RequestParam("name") String name,
                              @RequestParam("code") String code,
                              @RequestParam("id") Long id) {
        countryService.saveCountry(new Countries(id, name, code));
        return "redirect:/admin/countries";
    }

    @PostMapping(value = "/add_role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addRole(@RequestParam("name") String name,
                          @RequestParam("desc") String desc) {
        userService.addRole(new Roles(null, name, desc));
        return "redirect:/admin/roles";
    }

    @PostMapping(value = "/edit_role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editRole(@RequestParam("name") String name,
                           @RequestParam("desc") String desc,
                           @RequestParam("id") Long id) {
        Roles role = userService.getRoleById(id);
        role.setRole(name);
        role.setDescription(desc);
        userService.saveRole(role);
        return "redirect:/admin/roles";
    }

    @GetMapping(value = "/delete_role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRole(@RequestParam("id") Long id) {
        Roles roles = userService.getRoleById(id);
        userService.deleteRole(roles);
        return "redirect:/admin/roles";
    }

    @GetMapping(value = "/delete_country")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCountry(@RequestParam("id") Long id) {
        countryService.deleteCountry(countryService.getCountry(id));
        return "redirect:/admin/countries";
    }


    @GetMapping(value = "/delete_item")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String delete(@RequestParam("id") Long id) {
        itemService.deleteItem(itemService.getItem(id));
        return "redirect:/admin/items";
    }

    @PostMapping(value = "/add_item")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String add_item(@RequestParam("name") String name,
                           @RequestParam("desc") String desc,
                           @RequestParam("price") double price,
                           @RequestParam("in_top_page") boolean in_top_page,
                           @RequestParam("small_pic_url") String small_pic,
                           @RequestParam("large_pic_url") String large_pic,
                           @RequestParam("rating") int stars,
                           @RequestParam("brand_id") Long brand_id,
                           @RequestParam("categories") List<Long> cat_ids
    ) {
        List<Categories> categories = new ArrayList<>();
        for (Long id : cat_ids) {
            categories.add(categoryService.getCategory(id));
        }
        itemService.addItem(new Items(null, name, desc, price, stars, small_pic,
                large_pic, new Date(), in_top_page, brandService.getBrand(brand_id), categories));
        return "redirect:/admin/items";
    }

    @PostMapping(value = "/edit_item")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String edit(@RequestParam("name") String name,
                       @RequestParam("desc") String desc,
                       @RequestParam("price") double price,
                       @RequestParam("in_top_page") boolean in_top_page,
                       @RequestParam("small_pic_url") String small_pic,
                       @RequestParam("large_pic_url") String large_pic,
                       @RequestParam("rating") int stars,
                       @RequestParam("id") Long id,
                       @RequestParam("brand") Long brand
    ) {
        Items item = new Items(id, name, desc, price, stars, small_pic, large_pic,
                itemService.getItem(id).getAddedDate(), in_top_page, brandService.getBrand(brand), itemService.getItem(id).getCategories());
        itemService.saveItem(item);
        return "redirect:/admin/items";
    }

    @GetMapping(value = "/details/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String details(Model model, @PathVariable(name = "id") String _id, HttpServletRequest request) {
        Long id = Long.parseLong(_id.split("_")[0]);
        Items item = itemService.getItem(id);
        List<Categories> item_cats = item.getCategories();
        List<Brands> brands = brandService.getAllBrands();
        List<Categories> categories = categoryService.getAllCategories();
        boolean rem = categories.removeAll(item_cats);
        List<Pictures> pictures = picturesService.getPicturesByItem(item);
        lng_config(request);
        model.addAttribute("item", item);
        model.addAttribute("item_cats", item_cats);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("pictures", pictures);

        return "admin_details";
    }

    @GetMapping(value = "/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String userDetails(Model model, @PathVariable(name = "id") String _id, HttpServletRequest request) {
        Long id = Long.parseLong(_id.split("_")[0]);
        Users user = userService.getUserById(id);
        lng_config(request);
        model.addAttribute("user", user);
        if (request.getParameter("error") != null) {
            request.setAttribute("error", request.getParameter("error"));
        }
        if (request.getParameter("success") != null) {
            request.setAttribute("success", "999");
        }
        return "admin_user_details";
    }

    @GetMapping(value = "/assign_cat")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String assignCat(@RequestParam("id") Long id,
                            @RequestParam("cat_id") Long cat_id) {
        Categories category = categoryService.getCategory(cat_id);
        Items item = itemService.getItem(id);
        List<Categories> categories = item.getCategories();
        categories.add(category);
        item.setCategories(categories);
        itemService.saveItem(item);

        return "redirect:/admin/details/" + id + '_' + item.getName().replaceAll(" ", "-");

    }

    @GetMapping(value = "/unassign_cat")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String unassignCat(@RequestParam("id") Long id,
                              @RequestParam("cat_id") Long cat_id) {
        Categories category = categoryService.getCategory(cat_id);
        Items item = itemService.getItem(id);
        List<Categories> categories = item.getCategories();
        categories.remove(category);
        item.setCategories(categories);
        itemService.saveItem(item);

        return "redirect:/admin/details/" + id + '_' + item.getName().replaceAll(" ", "-");

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
        Cookie[] cookies = request.getCookies();
        String lng_param = request.getParameter("lng");
        request.setAttribute("currentUser", getUserData());
        request.setAttribute("page", request.getRequestURI());
        if (lng_param != null) {
            request.setAttribute("lng", lng_param);
        } else {
            for (Cookie c : cookies) {
                if (c.getName().equals("language")) {
                    request.setAttribute("lng", c.getValue());
                    break;
                }
            }
        }

    }
}
