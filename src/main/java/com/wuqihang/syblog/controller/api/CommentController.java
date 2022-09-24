package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.pojo.Comment;
import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.services.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("add-comment")
    public ResponsePKG addComment(@RequestBody Comment comment) {
        int id = commentService.addComment(comment.getBlogId(),comment);
        return ResponsePKG.returnData(id);
    }

    @RequestMapping("delete-comment")
    public ResponsePKG deleteComment(@RequestParam int id) {
        commentService.deleteComment(id);
        return ResponsePKG.OK;
    }

    @RequestMapping("get-comment-ids")
    public ResponsePKG getCommentIds() {
        return ResponsePKG.returnData(commentService.getAllId());
    }

    @RequestMapping("get-comments")
    public ResponsePKG getComments(@RequestParam String blogId) {
        return ResponsePKG.returnData(commentService.getComments(blogId));
    }

    @RequestMapping("get-all-comments")
    public ResponsePKG getAllComments() {
        return ResponsePKG.returnData(commentService.getAllComments());
    }
}
