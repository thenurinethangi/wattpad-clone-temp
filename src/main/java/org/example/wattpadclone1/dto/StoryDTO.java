package org.example.wattpadclone1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoryDTO {

    private String userId;
    private String title;
    private String description;
    private String mainCharacters;
    private String category;
    private String tags;
    private String targetAudience;
    private String language;
    private String copyright;
    private String isMature;
    private MultipartFile coverImage;
}
