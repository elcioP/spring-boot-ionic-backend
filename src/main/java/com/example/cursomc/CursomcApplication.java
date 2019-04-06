package com.example.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.domain.Estado;
import com.example.cursomc.domain.ItemPedido;
import com.example.cursomc.domain.Pagamento;
import com.example.cursomc.domain.PagamentoComBoleto;
import com.example.cursomc.domain.PagamentoComCartao;
import com.example.cursomc.domain.Pedido;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.domain.enums.EstadoPagamento;
import com.example.cursomc.domain.enums.TipoCliente;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.repositories.EstadoRepository;
import com.example.cursomc.repositories.ItemPedidoRepository;
import com.example.cursomc.repositories.PagamentoRepository;
import com.example.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date dt1 = sdf.parse("30/09/2017 10:32");
		
		// instancia pedidos
		Pedido ped1 = new Pedido(dt1, cli1 , e1);
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		//instanciando pagamento e inserindo pagamento nos pedidos
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00 ") , null);
		ped2.setPagamento(pgto2);
		
		//inserido pedido nos clientes
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		//salvando os pedidos no banco
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		//salvando os pagamentos no banco
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		
		//instanciando os itens pedido
		ItemPedido  itempedido1 =  new ItemPedido(ped1, produto1, 0.00, 1, 2000.00);
		ItemPedido itempedido2 = new ItemPedido(ped1, produto3, 0.00, 2, 80.00);
		ItemPedido itempedido3 = new ItemPedido(ped2, produto2, 100.00, 1, 800.00); 
		
		//inserindo os itens pedido nos pedidos
		ped1.getItensPedido().addAll(Arrays.asList(itempedido1, itempedido2));
		ped1.getItensPedido().addAll(Arrays.asList(itempedido3));
		
		//inserindo os itens nos produtos
		produto1.getItensPedido().addAll(Arrays.asList(itempedido1));
		produto2.getItensPedido().addAll(Arrays.asList(itempedido3));
		produto3.getItensPedido().addAll(Arrays.asList(itempedido2));
		
		//salvando os itens pedido no banco
		itemPedidoRepository.saveAll(Arrays.asList(itempedido1,itempedido2,itempedido3));
	}

}
