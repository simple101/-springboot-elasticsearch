package com.hl.repository;

import com.hl.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductDocumentRepository extends ElasticsearchRepository<ProductDocument, String> {
}
