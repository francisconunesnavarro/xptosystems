package com.navarro.xptosystems.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navarro.xptosystems.domain.Estado;
import com.navarro.xptosystems.dto.EstadoDTO;
import com.navarro.xptosystems.repository.EstadoRepository;
import com.navarro.xptosystems.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado find(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}

	public List<Estado> findAll() {
		return repo.findAllByOrderByName();
	}

	public Estado findByUf(String uf) {
		return repo.findByUf(uf);
	}

	public Estado fromDTO(EstadoDTO objDto) {
		return new Estado(objDto.getId(), objDto.getName(), objDto.getUf());
	}

	public List<Estado> findEstadoMinQtdCidade() {
		return repo.findEstadoMinQtdCidade();
	}

}
