package com.example.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired //instancia automaticamente pelo spring
	private ClienteRepository repo;
	
	
	
	public Cliente find(Integer id){
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id+
                             ", Tipo: " + Cliente.class.getName()));
	}
	
	
	
//	public Cliente buscar(Integer id) {
//		Cliente obj = repo.findOne(id);
//		if(obj == null) {
//			
//		}
//	}
}
