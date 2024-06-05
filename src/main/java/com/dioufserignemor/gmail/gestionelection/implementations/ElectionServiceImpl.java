package com.dioufserignemor.gmail.gestionelection.implementations;

import com.dioufserignemor.gmail.gestionelection.DTO.ElectionDto;
import com.dioufserignemor.gmail.gestionelection.entites.Election;
import com.dioufserignemor.gmail.gestionelection.repositories.ElectionRepository;
import com.dioufserignemor.gmail.gestionelection.repositories.VoteRepository;
import com.dioufserignemor.gmail.gestionelection.services.ElectionService;
import mappers.GenericMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class ElectionServiceImpl implements ElectionService {
    @Autowired
    private  ElectionRepository electionRepository;

    @Autowired
    private VoteRepository voteRepository;

    MessageSource messageSource;

    private GenericMapper<Election, ElectionDto> electionMapper;

    private static final Logger logger = LogManager.getLogger(ElectionService.class);
}
