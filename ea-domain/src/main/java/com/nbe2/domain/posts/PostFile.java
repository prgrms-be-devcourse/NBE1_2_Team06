package com.nbe2.domain.posts;

import jakarta.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.*;

import com.nbe2.domain.file.FileMetaData;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_files")
public class PostFile {

    @EmbeddedId private PostFilePk postFilePk = new PostFilePk();

    @MapsId("fileMetaDataId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileMetaData fileMetaData;

    @MapsId("postId")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "post_id")
    private Post post;

    private PostFile(final FileMetaData fileMetaData, final Post post) {
        this.postFilePk = PostFilePk.of(fileMetaData.getId(), post.getId());
        this.fileMetaData = fileMetaData;
        setPost(post);
    }

    public static PostFile of(final FileMetaData fileMetaData, final Post post) {
        return new PostFile(fileMetaData, post);
    }

    public Long getFileMetaDataId() {
        return fileMetaData.getId();
    }

    public Long getPostId() {
        return post.getId();
    }

    // ** 연관관계 편의 메서드**//
    public void setPost(final Post post) {
        this.post = post;
        post.addFile(this);
    }
}
