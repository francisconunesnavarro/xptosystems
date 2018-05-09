package com.navarro.xptosystems.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.navarro.xptosystems.domain.Cidade;
import com.navarro.xptosystems.dto.CidadeDTO;
import com.navarro.xptosystems.repository.CidadeRepository;
import com.navarro.xptosystems.services.exceptions.DataIntegrityException;
import com.navarro.xptosystems.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}

	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cidade update(Cidade obj) {
		Cidade newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public Cidade fromDTO(CidadeDTO objDto) {
		return new Cidade(objDto.getId(), objDto.getIbgeId(), objDto.getName(), objDto.getEstado(), objDto.getCapital(),
				objDto.getLon(), objDto.getLat(), objDto.getNoAccents(), objDto.getAlternativeNames(),
				objDto.getMicroregion(), objDto.getMesoregion());
	}

	private void updateData(Cidade newObj, Cidade obj) {
		newObj.setName(obj.getName());
		newObj.setIbgeId(obj.getIbgeId());
		newObj.setCapital(obj.getCapital());
		newObj.setLon(obj.getLon());
		newObj.setLat(obj.getLat());
		newObj.setNoAccents(obj.getNoAccents());
		newObj.setAlternativeNames(obj.getAlternativeNames());
	}

	public List<Cidade> findAll() {
		return repo.findAllByOrderByName();
	}

	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}

	public List<Cidade> findCapitais() {
		return repo.findCapitais();
	}

	public Page<Cidade> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Page<Cidade> findCidadesByUF(String uf, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadesByUF(uf, pageRequest);
	}

	public Page<Cidade> findCidadeLikeName(String name, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadeLikeName(name, pageRequest);
	}

	public Page<Cidade> findCidadeLikeUf(String uf, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadeLikeUf(uf, pageRequest);
	}

	public Page<Cidade> findCidadeLikeIbge(String ibgeId, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadeLikeIbge(ibgeId, pageRequest);
	}

	public Page<Cidade> findCidadeLikeNoAccents(String noAccents, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadeLikeNoAccents(noAccents, pageRequest);
	}

	public Page<Cidade> findCidadeLikeAlterName(String alterName, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadeLikeAlterName(alterName, pageRequest);
	}

	public Page<Cidade> findCidadeLikeMicroRegion(String microRegion, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadeLikeMicroRegion(microRegion, pageRequest);
	}

	public Page<Cidade> findCidadeLikeMesoRegion(String mesoRegion, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findCidadeLikeMesoRegion(mesoRegion, pageRequest);
	}

	public Page<Cidade> findByIbge(Integer ibgeId, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findByIbge(ibgeId, pageRequest);
	}

	public Integer findQtdCidades() {
		return repo.findQtdCidades();
	}

	public Integer findQtdCidadesByUf(String uf) {
		return repo.findQtdCidadesByUf(uf);
	}

	public Integer findQtdRegistros(String tipo) {
		if (tipo == "IBGE ID") {
			return repo.findQtdByIbgeId();
		} else if (tipo == "Nome") {
			return repo.findQtdByName();
		} else if (tipo == "UF") {
			return repo.findQtdByUf();
		} else if (tipo == "MicroRegiao") {
			return repo.findQtdByMicroRegion();
		} else if (tipo == "MesoRegiao") {
			return repo.findQtdByMesoRegion();
		} else {
			return repo.findQtdCidades();
		}

	}
}
