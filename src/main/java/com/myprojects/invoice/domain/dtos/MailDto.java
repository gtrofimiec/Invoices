package com.myprojects.invoice.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailDto {

    private String mailTo;
    private String subject;
    private String message;
    private String toCc;
    private String attachmentName;
    private File invoice;
}