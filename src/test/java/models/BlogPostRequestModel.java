package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

        import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogPostRequestModel {
    private String id;
    private String title;
    private String shortDescription;
    private String content;
    private String featuredImageUrl;
    private String urlHandle;
    private String  publishedDate;
    private String author;
    @JsonProperty("isVisible")
    private boolean isVisible;
    private String[] categories;
}