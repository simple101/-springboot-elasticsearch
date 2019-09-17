package com.hl.repository;

import com.hl.document.UserObjectDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserObjectDocumentRepository extends ElasticsearchRepository<UserObjectDocument, String> {
}
