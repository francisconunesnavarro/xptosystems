package com.navarro.xptosystems.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.navarro.xptosystems.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	@Transactional(readOnly = true)
	public List<Cidade> findAllByOrderByName();

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.id = :estadoId ORDER BY obj.name")
	public List<Cidade> findCidades(@Param("estadoId") Integer estado_id);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.capital = 1 ORDER BY obj.name")
	public List<Cidade> findCapitais();

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.estado.uf IN :uf")
	public Page<Cidade> findCidadesByUF(@Param("uf") String uf, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE UPPER(obj.name) LIKE %:name%")
	public Page<Cidade> findCidadeLikeName(@Param("name") String name, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE UPPER(obj.estado.uf) LIKE %:uf%")
	public Page<Cidade> findCidadeLikeUf(@Param("uf") String uf, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE TO_CHAR(obj.ibgeId) LIKE %:ibgeId%")
	public Page<Cidade> findCidadeLikeIbge(@Param("ibgeId") String ibgeId, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE UPPER(obj.noAccents) LIKE %:noAccents%")
	public Page<Cidade> findCidadeLikeNoAccents(@Param("noAccents") String noAccents, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE UPPER(obj.alternativeNames) LIKE %:alterName%")
	public Page<Cidade> findCidadeLikeAlterName(@Param("alterName") String alterName, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE UPPER(obj.microregion) LIKE %:microRegion%")
	public Page<Cidade> findCidadeLikeMicroRegion(@Param("microRegion") String microRegion, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE UPPER(obj.mesoregion) LIKE %:mesoRegion% ")
	public Page<Cidade> findCidadeLikeMesoRegion(@Param("mesoRegion") String mesoRegion, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Cidade obj WHERE obj.ibgeId = :ibgeId")
	public Page<Cidade> findByIbge(@Param("ibgeId") Integer ibgeId, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(obj) FROM Cidade obj")
	public Integer findQtdCidades();

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(obj) FROM Cidade obj WHERE obj.estado.uf IN :uf")
	public Integer findQtdCidadesByUf(@Param("uf") String uf);

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(DISTINCT obj.ibgeId) FROM Cidade obj")
	public Integer findQtdByIbgeId();

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(DISTINCT obj.name) FROM Cidade obj")
	public Integer findQtdByName();

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(DISTINCT obj.estado.id) FROM Cidade obj")
	public Integer findQtdByUf();

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(DISTINCT obj.microregion) FROM Cidade obj")
	public Integer findQtdByMicroRegion();

	@Transactional(readOnly = true)
	@Query("SELECT COUNT(DISTINCT obj.mesoregion) FROM Cidade obj")
	public Integer findQtdByMesoRegion();

}
