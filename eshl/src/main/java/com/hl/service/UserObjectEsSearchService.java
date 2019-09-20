package com.hl.service;

import com.hl.document.UserObjectDocument;

import java.util.List;

public interface UserObjectEsSearchService extends BaseSearchService<UserObjectDocument> {
    /**
     * 保存
     */
    void save(UserObjectDocument... userObjectDocuments);

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
    UserObjectDocument getById(String id);

    /**
     * 查询全部
     */
    List<UserObjectDocument> getAll();


    /**
     * 根据code查询
     */
    List<UserObjectDocument> getByCode(String code);
}
