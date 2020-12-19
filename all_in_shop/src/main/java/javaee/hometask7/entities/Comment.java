package javaee.hometask7.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="comment")
    private String comment;

    @Column(name = "added_date")
    private Date addedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Items item;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users author;

}
