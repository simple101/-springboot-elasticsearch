package com.hl.controller;

import com.hl.document.ProductDocument;
import com.hl.page.Page;
import com.hl.service.ProductEsSearchService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/project/es")
@RestController
public class ProductSearchController {
    @Resource
    private ProductEsSearchService esSearchService;

    /**
     * 新增 / 修改索引
     */
    @RequestMapping("save")
    public String add(@RequestBody ProductDocument productDocument) {
        esSearchService.save(productDocument);
        return "success";
    }

    /**
     * 删除索引
     */
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable String id) {
        esSearchService.delete(id);
        return "success";
    }

    /**
     * 清空索引
     */
    @RequestMapping("delete_all")
    public String deleteAll() {
        esSearchService.deleteAll();
        return "success";
    }

    /**
     * 根据ID获取
     */
    @RequestMapping("get/{id}")
    public ProductDocument getById(@PathVariable String id) {
        return esSearchService.getById(id);
    }

    /**
     * 根据获取全部
     */
    @RequestMapping("get_all")
    public List<ProductDocument> getAll() {
        return esSearchService.getAll();
    }

    /**
     * 搜索
     */
    @RequestMapping("query/{keyword}")
    public List<ProductDocument> query(@PathVariable String keyword) {
        return esSearchService.query(keyword, ProductDocument.class);
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
        return esSearchService.queryHit(keyword, indexName, fieldNames);
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
        return esSearchService.queryHitByPage(pageNo, pageSize, keyword, indexName, fieldNames);
    }

    /**
     * 删除索引库
     */
    @RequestMapping("delete_index/{indexName}")
    public String deleteIndex(@PathVariable String indexName) {
        esSearchService.deleteIndex(indexName);
        return "success";
    }
}
