package com.example.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		Produto produto1 = new Produto(null, "computador", 2000.00);
		Produto produto2 = new Produto(null, "impressora", 800.00);
		Produto produto3 = new Produto(null, "mouse", 80.00);
		
		
		//adicionando os produtos na categoria
		cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		cat2.getProdutos().addAll(Arrays.asList(produto2));
		
		produto1.getCategorias().add(cat1);
		produto2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		produto3.getCategorias().add(cat1); 
		
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
		
	}

}
