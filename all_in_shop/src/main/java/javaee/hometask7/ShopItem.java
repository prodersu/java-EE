package javaee.hometask7;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ShopItem {

    private Long id;
    private String name;
    private String description;
    private int price;
    private int amount;
    private int stars;
    private String pictureUrl;


}
