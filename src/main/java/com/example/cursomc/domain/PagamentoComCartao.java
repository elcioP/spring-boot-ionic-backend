package com.example.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.example.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento implements Serializable{
	
	private static final long serialVersionUID = 1L;

		private Integer numeroParcelas;
		public PagamentoComCartao() {
			super();
			
		}

		public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido,  Integer numeroParcelas ) {
			super(id, estado, pedido);
			this.numeroParcelas = numeroParcelas;
		}
		public Integer getNumeroParcelas() {
			return numeroParcelas;
		}

		public void setNumeroParcelas(Integer numeroParcelas) {
			this.numeroParcelas = numeroParcelas;
		}
}
