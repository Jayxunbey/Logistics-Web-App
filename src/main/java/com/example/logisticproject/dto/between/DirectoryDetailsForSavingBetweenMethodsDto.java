package com.example.logisticproject.dto.between;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class
DirectoryDetailsForSavingBetweenMethodsDto {

    private String fullPath;
    private String fileName;
    private String fileExtension;

}
