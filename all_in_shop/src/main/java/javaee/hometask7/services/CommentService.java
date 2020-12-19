package javaee.hometask7.services;

import javaee.hometask7.entities.Brands;
import javaee.hometask7.entities.Categories;
import javaee.hometask7.entities.Comment;
import javaee.hometask7.entities.Items;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments(Items items);
    void addComment(Comment comment);
    void saveComment(Comment comment);
    void deleteComment(Comment comment);
    Comment getCommentById(Long id);




}
