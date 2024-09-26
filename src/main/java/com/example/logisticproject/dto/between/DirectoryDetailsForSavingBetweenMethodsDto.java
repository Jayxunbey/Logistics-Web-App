package com.example.logisticproject.dto.between;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectoryDetailsForSavingBetweenMethodsDto {

    private String fullPath;
    private String fileName;
    private String fileExtension;

}
