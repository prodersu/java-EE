package javaee.hometask7.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "stars")
    private int stars;
    @Column(name = "small_pic_url")
    private String smallPicURL;
    @Column(name = "large_pic_url")
    private String largePicURL;
    @Column(name = "added_date")
    private Date addedDate;
    @Column(name = "in_top_page")
    private boolean inTopPage; // if true, then this item will be in top of index page

    @ManyToOne(fetch = FetchType.EAGER)
    private Brands brands;

    @ManyToMany
    private List<Categories> categories;

}
