package javaee.hometask7;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<ShopItem> items = new ArrayList<>();
    private static Long id = 1L;

    static {
        addItems(new ShopItem(null, "IPhone 11", "16GB RAM,Super Retina XDR Full front OLED 6.1-inch diagonal", 399, 5, 4, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone11-black-select-2019_GEO_EMEA?wid=470&hei=556&fmt=png-alpha&.v=1567021766023"));
        addItems(new ShopItem(null, "Iphone XR", "8GB RAM 2532-by-1170-pixel resolution at 460 ppi", 399, 5, 2, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-xr-blue-select-201809?wid=470&hei=556&fmt=png-alpha&.v=1565209264208"));
        addItems(new ShopItem(null, "IPhone SE", "8GB RAM True Tone technology ", 399, 5, 3, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-se-white-select-2020_GEO_EMEA?wid=940&hei=1112&fmt=png-alpha&qlt=80&.v=1586574260599"));
        addItems(new ShopItem(null, "IPhone 12", "32GB RAM ", 499, 5, 5, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-blue-select-2020?wid=940&hei=1112&fmt=png-alpha&qlt=80&.v=1601830934000"));
        addItems(new ShopItem(null, "Iphone 12 PRO", "32GB RAM", 999, 5, 5, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-pro-graphite-hero?wid=470&hei=556&fmt=png-alpha&.v=1601620625000"));
        addItems(new ShopItem(null, "IPhone 12 Mini", "25GB RAM", 599, 5, 5, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-mini-green-select-2020?wid=940&hei=1112&fmt=png-alpha&qlt=80&.v=1601830931000"));
    }
    public static void addItems(ShopItem item){
        item.setId(id++);
        items.add(item);
    }
    public static ArrayList<ShopItem> getItems(){
        return items;
    }
}
