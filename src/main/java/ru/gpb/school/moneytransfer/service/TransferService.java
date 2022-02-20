package ru.gpb.school.moneytransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {

    private final TransferRepo transferRepo;

    @Autowired
    public TransferService(TransferRepo transferRepo){
        this.transferRepo = transferRepo;
    }

    public void saveTransfer(Transfer transfer){
        transferRepo.save(transfer);
    }

    public List<Transfer> findAll(){
        return transferRepo.findAll();
    }


    public List<Transfer> findTransfersByRecipientAccount(String id){
        return transferRepo.findAllByRecipientAccount(id);
    }

    public List<Transfer> findTransfersBetween(LocalDateTime start, LocalDateTime end){
        return transferRepo.findByDateTimeGreaterThanAndDateTimeLessThan(start, end);
    }

    public List<Transfer> findTransfersBySenderAccount(String id){
        return transferRepo.findAllBySenderAccount(id);
    }

    public Transfer dtoToTransferEntity(TransferDto transferDto){
        return Transfer.builder()
                .amountOfMoney(transferDto.getAmount())
                .senderAccount(transferDto.getFrom())
                .recipientAccount(transferDto.getTo())
                .dateTime(LocalDateTime.now())
                .build();
    }

}
