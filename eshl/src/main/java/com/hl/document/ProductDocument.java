package com.hl.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "orders", type = "product")
@Mapping(mappingPath = "productIndex.json") // 解决IK分词不能使用问题
@Data
public class ProductDocument implements Serializable {
    @Id
    private String id;
    private String productName;
    private String productDesc;
    private Date createTime;
    private Date updateTime;
}
