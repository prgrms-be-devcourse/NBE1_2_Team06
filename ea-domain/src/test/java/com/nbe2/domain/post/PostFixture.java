package com.nbe2.domain.post;

import java.lang.reflect.Field;
import java.util.Optional;

import com.nbe2.domain.posts.City;
import com.nbe2.domain.posts.Post;
import com.nbe2.domain.posts.PostWriteInfo;
import com.nbe2.domain.user.User;
import com.nbe2.domain.user.UserFixture;

public class PostFixture {
    public static Post createPost() {
        User user = UserFixture.createUser();
        return Post.create(user, "title", "content", City.ANDONG);
    }

    public static Post createPostWithId() {
        Post post = createPost();
        try {
            Field id = Post.class.getDeclaredField("id"); // 접근제한자 상관없이, argument 이름의 필드 get
            id.setAccessible(true);
            id.set(post, 1L);
        } catch (Exception ignored) {
        }
        return post;
    }

    public static PostWriteInfo createPostWriteInfo() {
        return PostWriteInfo.create("title", "content", City.ANDONG, Optional.empty());
    }
}
