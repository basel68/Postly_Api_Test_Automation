package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageModel {
    private String id;
    private String fileName;
    private String fileExtension;
    private String url;
    private String title;
    private String dateCreated;
}
