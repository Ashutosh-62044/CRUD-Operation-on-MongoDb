package com.MongoDBProject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private long rollNo;
    private String name;
    private String city;
    private String phoneNo;
}
