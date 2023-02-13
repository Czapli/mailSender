package net.czaplinski.mailSender.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.czaplinski.mailSender.controller.exceptions.AddressNotFoundException;
import net.czaplinski.mailSender.domain.dto.MailAddressesDto;
import net.czaplinski.mailSender.domain.mapper.MailAddressesMapper;
import net.czaplinski.mailSender.service.MailAddressesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mailAddresses")
@AllArgsConstructor

public class MailAddressesController {
    private final MailAddressesService service;
    private final MailAddressesMapper mapper;

    @GetMapping
    public ResponseEntity<List<MailAddressesDto>> getAllMailAddresses() {
        return ResponseEntity.ok(mapper.mapToMailAddressesDtoList(service.getAllAddresses()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MailAddressesDto> getMailAddressById(@PathVariable Long id) throws AddressNotFoundException {
        return ResponseEntity.ok(mapper.mapToMailAddressDto(service.getById(id)));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailAddressesDto> addMailAddress(@Valid @RequestBody MailAddressesDto mailAddressesDto) {
        service.addMailAddresses(mapper.mapToMailAddress(mailAddressesDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailAddressesDto> updateMailAddress(@PathVariable Long id, @Valid @RequestBody MailAddressesDto mailAddressesDto) throws AddressNotFoundException {
        service.updateMailAddress(id, mapper.mapToMailAddress(mailAddressesDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMailAddressById(@PathVariable Long id) {
        service.deleteMailAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

