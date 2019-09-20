package com.hl.service.impl;

import com.hl.document.UserObjectDocument;
import com.hl.repository.UserObjectDocumentRepository;
import com.hl.service.UserObjectEsSearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class UserObjectEsSearchServiceImpl extends BaseSearchServiceImpl<UserObjectDocument> implements UserObjectEsSearchService {
    @Resource
    private UserObjectDocumentRepository userObjectDocumentRepository;

    @Override
    public void save(UserObjectDocument... userObjectDocuments) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserObjectDocument getById(String id) {
        return userObjectDocumentRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserObjectDocument> getAll() {
        List<UserObjectDocument> list = new ArrayList<>();
        userObjectDocumentRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public List<UserObjectDocument> getByCode(String code) {
        List<UserObjectDocument> list = new ArrayList<>();
                userObjectDocumentRepository.findUserObjectDocumentByCodeOrderByCtimeDesc(
                code).forEach(list::add);
        return list;
    }
}
