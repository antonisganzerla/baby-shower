package com.sgztech.service;

import com.sgztech.domain.entity.*;
import com.sgztech.domain.enums.EventStatus;
import com.sgztech.domain.repository.*;
import com.sgztech.exception.EntityNotFoundException;
import com.sgztech.rest.dto.BabyEventDTO;
import com.sgztech.rest.dto.EventDTO;
import com.sgztech.rest.dto.EventInfoDTO;
import com.sgztech.rest.dto.ProductEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl {

    @Autowired
    private EventRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BabyRepository babyRepository;

    @Autowired
    private ProductEventRepository productEventRepository;

    @Autowired
    private BabyEventRepository babyEventRepository;

    @Transactional
    public Event save(EventDTO eventDTO) {
        User user = userRepository
                .findById(eventDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setAddress(eventDTO.getAddress());
        event.setStatus(EventStatus.CREATED);
        event.setUser(user);

        List<ProductEvent> productsEvent = mapToProductEventList(eventDTO.getProducts(), event);
        List<BabyEvent> babiesEvent = mapToBabyEventList(eventDTO.getBabies(), event);

        repository.save(event);
        productEventRepository.saveAll(productsEvent);
        babyEventRepository.saveAll(babiesEvent);

        event.setProducts(productsEvent);
        event.setBabies(babiesEvent);
        return event;
    }

    private List<ProductEvent> mapToProductEventList(List<ProductEventDTO> items, Event event) {
        return items.stream()
                .map(p -> {
                    Product product = productRepository
                            .findById(p.getProductId())
                            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
                    ProductEvent productEvent = new ProductEvent();
                    productEvent.setProduct(product);
                    productEvent.setQuantity(p.getQuantity());
                    productEvent.setEvent(event);
                    return productEvent;
                }).collect(Collectors.toList());
    }

    private List<BabyEvent> mapToBabyEventList(List<BabyEventDTO> items, Event event) {
        return items.stream()
                .map(p -> {
                    Baby baby = babyRepository
                            .findById(p.getBabyId())
                            .orElseThrow(() -> new EntityNotFoundException("Bebê não encontrado"));
                    BabyEvent babyEvent = new BabyEvent();
                    babyEvent.setBaby(baby);
                    babyEvent.setEvent(event);
                    return babyEvent;
                }).collect(Collectors.toList());
    }

    public EventInfoDTO getById(Integer id) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));

        EventInfoDTO dto = new EventInfoDTO();
        dto.setId(event.getId());
        dto.setAddress(event.getAddress());
        dto.setName(event.getName());
        dto.setDate(event.getDate());
        dto.setStatus(event.getStatus());
        dto.setUserId(event.getUser().getId());
        dto.setBabies(event.getBabies());
        dto.setProducts(event.getProducts());
        return dto;
    }
}
