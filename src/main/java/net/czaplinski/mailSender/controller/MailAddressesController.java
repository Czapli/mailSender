package net.czaplinski.mailSender.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MailAddressesController {
    private final MailAddressesService service;
    private final MailAddressesMapper mapper;
    @Operation(description = "This method fetches all email addresses")
    @GetMapping
    public ResponseEntity<List<MailAddressesDto>> getAllMailAddresses() {
        log.trace("get all email addresses");
        return ResponseEntity.ok(mapper.mapToMailAddressesDtoList(service.getAllAddresses()));
    }
    @Operation(description = "This method fetches email address by id")
    @GetMapping("/{id}")
    public ResponseEntity<MailAddressesDto> getMailAddressById(@PathVariable Long id) throws AddressNotFoundException {
        log.trace("looking email address with ID: " + id);
        return ResponseEntity.ok(mapper.mapToMailAddressDto(service.getById(id)));
    }

    @Operation(description = "This method creates a new email address and saves it in the database")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailAddressesDto> addMailAddress(@Valid @RequestBody MailAddressesDto mailAddressesDto) {
        log.trace("add new email address: " + mailAddressesDto.getEmailAddress());
        service.addMailAddresses(mapper.mapToMailAddress(mailAddressesDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "This method updates the email address with the given ID")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailAddressesDto> updateMailAddress(@PathVariable Long id, @Valid @RequestBody MailAddressesDto mailAddressesDto) throws AddressNotFoundException {
        log.trace("update email address: with ID: " + id +" for : " + mailAddressesDto.getEmailAddress());
        service.updateMailAddress(id, mapper.mapToMailAddress(mailAddressesDto));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @Operation(description = "This method updates the email address with the given ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMailAddressById(@PathVariable Long id) throws AddressNotFoundException {
        log.trace("delete email address with ID: " + id);
        service.deleteMailAddress(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

