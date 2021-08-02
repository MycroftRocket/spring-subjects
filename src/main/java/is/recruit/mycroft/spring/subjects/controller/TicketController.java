package is.recruit.mycroft.spring.subjects.controller;

import is.recruit.mycroft.spring.subjects.model.ticket.Ticket;
import is.recruit.mycroft.spring.subjects.model.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketRepository ticketRepository;

    @GetMapping("{ticketNo}")
    public Mono<?> get(@PathVariable Long ticketNo) {

        return Mono.just(ticketRepository.findById(ticketNo));
    }

    @GetMapping(value = "all", produces = {MediaType.TEXT_EVENT_STREAM_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Flux<?> getAll() {

        return Flux.fromIterable(ticketRepository.findAll());
    }

    @PostMapping("")
    public Mono<?> create(@RequestBody Ticket ticket) {

        return Mono.just(ticketRepository.save(ticket));
    }

    @PostMapping("list")
    public Flux<?> createList(@RequestBody List<Ticket> ticketList) {

        return Flux.fromIterable(ticketRepository.saveAll(ticketList));
    }
}
