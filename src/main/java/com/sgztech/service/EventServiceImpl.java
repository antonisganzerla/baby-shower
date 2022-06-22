package com.sgztech.service;

import com.sgztech.domain.entity.*;
import com.sgztech.domain.enums.EventPresence;
import com.sgztech.domain.enums.EventStatus;
import com.sgztech.domain.repository.*;
import com.sgztech.exception.BusinessRuleException;
import com.sgztech.exception.EntityNotFoundException;
import com.sgztech.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Autowired
    private GuestEventRepository guestEventRepository;

    @Autowired
    private MessageEventRepository messageEventRepository;

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

    public void cancel(Integer id) {
        Event event = getEvent(id);
        if (event.getStatus() == EventStatus.CLOSED)
            throw new BusinessRuleException("Evento encerrado não pode ser cancelado");
        event.setStatus(EventStatus.CANCELED);
        repository.save(event);
    }

    public void close(Integer id) {
        Event event = getEvent(id);
        if (event.getStatus() == EventStatus.CANCELED)
            throw new BusinessRuleException("Evento cancelado não pode ser encerrado");

        event.setStatus(EventStatus.CLOSED);
        repository.save(event);
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
        Event event = getEvent(id);

        EventInfoDTO dto = new EventInfoDTO();
        dto.setId(event.getId());
        dto.setAddress(event.getAddress());
        dto.setName(event.getName());
        dto.setDate(event.getDate());
        dto.setStatus(event.getStatus());
        dto.setUserId(event.getUser().getId());
        dto.setBabies(event.getBabies());
        dto.setProducts(event.getProducts());
        dto.setGuests(event.getGuests());
        dto.setMessages(event.getMessages());
        return dto;
    }

    public void saveGuest(Integer eventId, GuestDTO dto) {
        Event event = getEvent(eventId);

        GuestEvent guestEvent = new GuestEvent();
        guestEvent.setName(dto.getName());
        guestEvent.setEmail(dto.getEmail());
        guestEvent.setDate(LocalDateTime.now());
        guestEvent.setPresence(EventPresence.WAITING);
        guestEvent.setEvent(event);

        guestEventRepository.save(guestEvent);
    }

    public void saveMessage(Integer eventId, MessageDTO dto) {
        Event event = getEvent(eventId);

        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setMessage(dto.getMessage());
        messageEvent.setSender(dto.getSender());
        messageEvent.setDate(LocalDateTime.now());
        messageEvent.setEvent(event);

        messageEventRepository.save(messageEvent);
    }

    private Event getEvent(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento não encontrado"));
    }
}
