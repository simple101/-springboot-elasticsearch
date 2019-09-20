package com.hl.controller;

import com.hl.document.UserObjectDocument;
import com.hl.model.BaseResponse;
import com.hl.model.GenericResponse;
import com.hl.page.Page;
import com.hl.service.UserObjectEsSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/user/es")
@RestController
public class UserObjectSearchController {
    @Resource
    private UserObjectEsSearchService userObjectEsSearchService;

    /**
     * 新增 / 修改索引
     */
    @RequestMapping("save")
    public ResponseEntity<GenericResponse<String>> add(@RequestBody UserObjectDocument userObjectDocument) {
        userObjectEsSearchService.save(userObjectDocument);
        return BaseResponse.ltResponse(null);
    }

    /**
     * 删除索引
     */
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable String id) {
        userObjectEsSearchService.delete(id);
        return "success";
    }

    /**
     * 清空索引
     */
    @RequestMapping("delete_all")
    public String deleteAll() {
        userObjectEsSearchService.deleteAll();
        return "success";
    }

    /**
     * 根据ID获取
     */
    @RequestMapping("get/{id}")
    public UserObjectDocument getById(@PathVariable String id) {

        return userObjectEsSearchService.getById(id);
    }

    /**
     * 根据获取全部
     */
    @RequestMapping("get_all")
    public List<UserObjectDocument> getAll() {
        return userObjectEsSearchService.getAll();
    }

    /**
     * 搜索
     */
    @RequestMapping("query/{keyword}")
    public List<UserObjectDocument> query(@PathVariable String keyword) {
        return userObjectEsSearchService.query(keyword, UserObjectDocument.class);
    }

    /**
     * 按照code查询
     */
    @RequestMapping("query/code/{keyword}")
    public List<UserObjectDocument> queryCode(@PathVariable String keyword) {
        return userObjectEsSearchService.getByCode(keyword);
    }

    /**
     * 搜索，命中关键字高亮
     *
     * @param keyword   关键字
     * @param indexName 索引库名称
     * @param fields    搜索字段名称，多个以“，”分割
     */
    @RequestMapping("query_hit")
    public List<Map<String, Object>> queryHit(@RequestParam String keyword, @RequestParam String indexName,
            @RequestParam String fields) {
        String[] fieldNames;
        if (fields.contains(",")) {
            fieldNames = fields.split(",");
        } else {
            fieldNames = new String[1];
            fieldNames[0] = fields;
        }
        return userObjectEsSearchService.queryHit(keyword, indexName, fieldNames);
    }

    /**
     * 时间范围查询
     *
     * @param fieldNames 字段
     * @param indexName  索引库名称
     */
    @RequestMapping("query_date")
    public List<Map<String, Object>> queryDate(@RequestParam String indexName, @RequestParam String fieldNames,
            @RequestParam String startDate, @RequestParam String endDate) {

        return userObjectEsSearchService.queryHitDate(indexName, fieldNames, startDate, endDate);
    }

    /**
     * 搜索，命中关键字高亮
     *
     * @param pageNo    当前页
     * @param pageSize  每页显示的数据条数
     * @param keyword   关键字
     * @param indexName 索引库名称
     * @param fields    搜索字段名称，多个以“，”分割
     */
    @RequestMapping("query_hit_page")
    public Page<Map<String, Object>> queryHitByPage(@RequestParam int pageNo, @RequestParam int pageSize,
            @RequestParam String keyword, @RequestParam String indexName, @RequestParam String fields) {
        String[] fieldNames;
        if (fields.contains(",")) {
            fieldNames = fields.split(",");
        } else {
            fieldNames = new String[1];
            fieldNames[0] = fields;
        }
        return userObjectEsSearchService.queryHitByPage(pageNo, pageSize, keyword, indexName, fieldNames);
    }

    /**
     * 删除索引库
     */
    @RequestMapping("delete_index/{indexName}")
    public String deleteIndex(@PathVariable String indexName) {
        userObjectEsSearchService.deleteIndex(indexName);
        return "success";
    }
}
