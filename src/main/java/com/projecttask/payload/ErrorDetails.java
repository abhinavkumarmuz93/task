package com.projecttask.payload;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private Date timeStamp;
    private String message;
    private String details;

}
