package com.hl.service;

import com.hl.document.ProductDocument;

import java.util.List;

public interface ProductEsSearchService extends BaseSearchService<ProductDocument> {
    /**
     * 保存
     */
    void save(ProductDocument... productDocuments);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 清空索引
     */
    void deleteAll();

    /**
     * 根据ID查询
     */
    ProductDocument getById(String id);

    /**
     * 查询全部
     */
    List<ProductDocument> getAll();
}
