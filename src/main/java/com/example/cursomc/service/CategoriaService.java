package com.example.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired //instancia automaticamente pelo spring
	private CategoriaRepository repo;
	
	
	
	public Categoria find(Integer id){
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null );
	}
}
