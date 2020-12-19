package javaee.hometask7.services.impl;

import javaee.hometask7.entities.Comment;
import javaee.hometask7.entities.Countries;
import javaee.hometask7.entities.Items;
import javaee.hometask7.repositories.CommentsRepository;
import javaee.hometask7.repositories.CountriesRepository;
import javaee.hometask7.services.CommentService;
import javaee.hometask7.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public List<Comment> getAllComments(Items items) {
        return commentsRepository.findAllByItemEquals(items);
    }

    @Override
    public void addComment(Comment comment) {
        commentsRepository.save(comment);
    }

    @Override
    public void saveComment(Comment comment) {
        commentsRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentsRepository.delete(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentsRepository.getOne(id);
    }
}
