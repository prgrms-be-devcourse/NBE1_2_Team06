package com.nbe2.domain.post;

import org.springframework.test.util.ReflectionTestUtils;

import com.nbe2.domain.posts.City;
import com.nbe2.domain.posts.Post;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

public class PostFixture {
    public static Post createPost() {
        User user = UserFixture.createUser();
        return Post.create(user, "title", "content", City.ANDONG);
    }

    public static Post createPostWithId(Long postId) {
        Post post = createPost();
        ReflectionTestUtils.setField(post, "id", postId);
        return post;
    }
}
