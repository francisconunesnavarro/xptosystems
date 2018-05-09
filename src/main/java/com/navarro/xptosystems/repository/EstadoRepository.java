package com.navarro.xptosystems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.navarro.xptosystems.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Transactional(readOnly = true)
	public List<Estado> findAllByOrderByName();

	@Transactional(readOnly = true)
	public Estado findByUf(String uf);

	@Query("SELECT obj.uf, COUNT(1) FROM Estado obj JOIN Cidade cid ON obj.id = cid.estado.id GROUP BY obj.uf")
	public List<Estado> findEstadoMinQtdCidade();

}
