package com.hl.jest;

import com.google.gson.Gson;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.mapping.GetMapping;
import io.searchbox.indices.mapping.PutMapping;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.ArrayList;
import java.util.List;

public class JestTest {
    private static JestClient jestClient;
    private static String indexName = "user_object";
    private static String typeName = "_doc";
    private static String elasticIps = "http://127.0.0.1:9200";

    public static void main(String[] args) throws Exception {
        jestClient = getJestClient();
//        insertBatch();
//        serach1();
//        serach2();
        serach3();
        jestClient.close();
    }

    private static JestClient getJestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder(elasticIps).connTimeout(60000).readTimeout(60000).multiThreaded(
                        true).build());
        return factory.getObject();
    }

    public static void insertBatch() {
        List<Object> objs = new ArrayList<>();
//        objs.add(new User(1L, "张三", 20, "张三是个Java开发工程师","2018-4-25 11:07:42"));
//        objs.add(new User(2L, "李四", 24, "李四是个测试工程师","1980-2-15 19:01:32"));
//        objs.add(new User(3L, "王五", 25, "王五是个运维工程师","2016-8-21 06:11:32"));
        boolean result = false;
        try {
            result = insertBatch(jestClient, indexName, typeName, objs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("批量新增:" + result);
    }

    /**
     * 全文搜索
     */
    private static void serach1() {
        String query = "Zamit";
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.queryStringQuery(query));
            searchSourceBuilder.sort("ctime", SortOrder.DESC);
            //分页设置
            searchSourceBuilder.from(0).size(2);
            System.out.println("全文搜索查询语句:" + searchSourceBuilder.toString());
            System.out.println("全文搜索返回结果:" +
                    new Gson().toJson(search(jestClient, indexName, typeName, searchSourceBuilder.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 精确搜索
     */
    private static void serach2() {
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("code", "local"));
            searchSourceBuilder.sort("ctime", SortOrder.DESC);
            System.out.println("精确搜索查询语句:" + searchSourceBuilder.toString());
            System.out.println("精确搜索返回结果:" +
                    new Gson().toJson(search(jestClient, indexName, typeName, searchSourceBuilder.toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 区间搜索
     */
    private static void serach3() {
        String createtm = "ctime";
        long from = 1567268939000L;
        long to = 1569857339000L;

        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.rangeQuery(createtm).gte(from).lte(to));
            searchSourceBuilder.sort(createtm, SortOrder.DESC);
            System.out.println("区间搜索语句:" + searchSourceBuilder.toString());
            System.out.println("区间搜索返回结果:" + search(jestClient, indexName, typeName, searchSourceBuilder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建索引
     */
    private boolean createIndex(JestClient jestClient, String indexName) throws Exception {
        JestResult jr = jestClient.execute(new CreateIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * 新增数据
     */
    private boolean insert(JestClient jestClient, String indexName, String typeName, String source) throws Exception {
        PutMapping putMapping = new PutMapping.Builder(indexName, typeName, source).build();
        JestResult jr = jestClient.execute(putMapping);
        return jr.isSucceeded();
    }

    /**
     * 查询数据
     */
    private static String getIndexMapping(JestClient jestClient, String indexName, String typeName) throws Exception {
        GetMapping getMapping = new GetMapping.Builder().addIndex(indexName).addType(typeName).build();
        JestResult jr = jestClient.execute(getMapping);
        return jr.getJsonString();
    }

    /**
     * 批量新增数据
     */
    private static boolean insertBatch(JestClient jestClient, String indexName, String typeName,
            List<Object> objs) throws Exception {
        Bulk.Builder bulk = new Bulk.Builder().defaultIndex(indexName).defaultType(typeName);
        for (Object obj : objs) {
            Index index = new Index.Builder(obj).build();
            bulk.addAction(index);
        }
        BulkResult br = jestClient.execute(bulk.build());
        return br.isSucceeded();
    }

    /**
     * 全文搜索
     */
    private static String search(JestClient jestClient, String indexName, String typeName,
            String query) throws Exception {
        Search search = new Search.Builder(query).addIndex(indexName).addType(typeName).build();
        JestResult jr = jestClient.execute(search);
        return jr.getSourceAsString();
    }

    /**
     * 删除索引
     */
    private boolean delete(JestClient jestClient, String indexName) throws Exception {
        JestResult jr = jestClient.execute(new DeleteIndex.Builder(indexName).build());
        return jr.isSucceeded();
    }

    /**
     * 删除数据
     */
    private boolean delete(JestClient jestClient, String indexName, String typeName, String id) throws Exception {
        DocumentResult dr = jestClient.execute(new Delete.Builder(id).index(indexName).type(typeName).build());
        return dr.isSucceeded();
    }
}
