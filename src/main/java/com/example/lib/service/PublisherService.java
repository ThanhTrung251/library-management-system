package com.example.lib.service;

import com.example.lib.model.dto.PublisherDTO;
import com.example.lib.model.entity.Publisher;
import com.example.lib.model.request.BookRequest;

import java.util.List;

public interface PublisherService {
    List<PublisherDTO> getAllPublisher();

    PublisherDTO creatPublisher(BookRequest bookRequest);

    Publisher updatePublisher(long id, Publisher publisher);

    void deletePublisher(long id);

    Publisher getPublisherById(long id);
}
