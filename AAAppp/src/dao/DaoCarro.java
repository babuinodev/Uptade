package dao;

import java.util.List;

import entidade.Carro;

public interface DaoCarro {
	void save(Carro carro);
    List<Carro> loadAll();
    }