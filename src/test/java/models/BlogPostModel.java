package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPostModel {
    private String id;
    private String title;
    private String shortDescription;
    private String content;
    private String featuredImageUrl;
    private String urlHandle;
    private String  publishedDate;
    private String author;
    private boolean isVisible;
    private CategoryModel[] categories;
}
