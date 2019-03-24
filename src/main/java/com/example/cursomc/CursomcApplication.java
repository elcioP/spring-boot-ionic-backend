package com.example.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.domain.Estado;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.domain.enums.TipoCliente;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.repositories.EstadoRepository;
import com.example.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//criando objeto categoria
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "escritorio");
		//salvando categoria no banco
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		//criando objeto produto
		Produto produto1 = new Produto(null, "computador", 2000.00);
		Produto produto2 = new Produto(null, "impressora", 800.00);
		Produto produto3 = new Produto(null, "mouse", 80.00);
		
		
		//adicionando os produtos na categoria
		cat1.getProdutos().addAll(Arrays.asList(produto1,produto2,produto3));
		cat2.getProdutos().addAll(Arrays.asList(produto2));
		
		//adicionando as categorias nos produtos
		produto1.getCategorias().add(cat1);
		produto2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		produto3.getCategorias().add(cat1); 
		
		//salvando os produtos no banco, ja com as categorias
		produtoRepository.saveAll(Arrays.asList(produto1,produto2,produto3));
		
		//instanciando os estados
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1); 
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		
		//inserindo as cidades nos estados
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		//salvando os estados no banco
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		
		
		//salvando cidade no banco
		cidadeRepository.saveAll(Arrays.asList(cidade1,cidade2, cidade3));
		
		//instanciando cliente
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		//instanciando endeeço
		Endereco e1 = new Endereco(null,"Rua flores", "300", "Apto 203", "Jardim", "38220834", cli1, cidade1);
		Endereco e2 = new Endereco (null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cidade2);
		
		//inserindo os endereços no cliente
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		//salvando o  cliente no banco
		clienteRepository.saveAll(Arrays.asList(cli1));
	
		//salvando os endereços no banco
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
