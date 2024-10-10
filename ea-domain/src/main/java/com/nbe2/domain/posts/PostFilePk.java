package com.nbe2.domain.posts;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
public class PostFilePk implements Serializable {
    private Long fileMetaDataId;
    private Long postId;
}
