package com.rodrigo.literalurarodrigo.service;

import com.rodrigo.literalurarodrigo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    @Autowired
    private LibroRepository repository;


}
