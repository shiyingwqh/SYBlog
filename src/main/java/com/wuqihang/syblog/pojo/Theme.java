package com.wuqihang.syblog.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/**
 * @author Wuqihang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theme {
    @JsonIgnore
    private int id;
    private String name;
    private String author;
    private String url;
    private String staticPath;
    private String templatesPath;
    private String Version;
    @JsonIgnore
    private File dir;
}
