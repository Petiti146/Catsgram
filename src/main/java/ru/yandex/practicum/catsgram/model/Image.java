package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "id" })
public class Image {
    private Long id;
    private long postId;
    private String originalFileName;
    private String filePath;

    public Image(long postId, String originalFileName, String filePath) {
        this.postId = postId;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
    }
}
