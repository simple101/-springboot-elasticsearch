package com.hl.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "user_object", type = "_doc")
@Mapping(mappingPath = "UserObject.json") // 解决IK分词不能使用问题
@Data
public class UserObjectDocument implements Serializable {
    @Id
    private String id;
    //@Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String object_name;
    //@Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String code;
    private String email;
    private String gender;
    private String ipAddress;
    private String assetAddress;
    private Date ctime;
    private Date utime;
}
