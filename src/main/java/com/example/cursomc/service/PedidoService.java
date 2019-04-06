package com.example.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Pedido;
import com.example.cursomc.repositories.PedidoRepository;
import com.example.cursomc.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired //instancia automaticamente pelo spring
	private PedidoRepository repo;
	
	
	
	public Pedido find(Integer id){
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id+
                             ", Tipo: " + Pedido.class.getName()));
	}
	
	
	
//	public Pedido buscar(Integer id) {
//		Pedido obj = repo.findOne(id);
//		if(obj == null) {
//			
//		}
//	}
}
