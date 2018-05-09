package com.navarro.xptosystems.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.navarro.xptosystems.domain.Cidade;
import com.navarro.xptosystems.domain.Estado;
import com.navarro.xptosystems.dto.CidadeDTO;
import com.navarro.xptosystems.dto.EstadoDTO;
import com.navarro.xptosystems.resources.utils.URL;
import com.navarro.xptosystems.services.CidadeService;
import com.navarro.xptosystems.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		for (int i = 0; i < listDTO.size(); i++) {
			listDTO.get(i).setQtdCidades(cidadeService.findQtdCidadesByUf(listDTO.get(i).getUf()));
		}
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Estado> find(@PathVariable Integer id) {
		Estado obj = service.find(id);
		obj.setQtdCidades(cidadeService.findQtdCidadesByUf(obj.getUf()));
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/{estadoId}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId) {
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		for (int i = 0; i < listDTO.size(); i++) {
			listDTO.get(i).getEstado()
					.setQtdCidades(cidadeService.findQtdCidadesByUf(listDTO.get(i).getEstado().getUf()));
		}
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/buscauf", method = RequestMethod.GET)
	public ResponseEntity<Page<CidadeDTO>> findPage(@RequestParam(value = "uf", defaultValue = "") String uf,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String ufDecoded = URL.decodeParam(uf, "N");
		Page<Cidade> list = cidadeService.findCidadesByUF(ufDecoded, page, linesPerPage, orderBy, direction);
		Page<CidadeDTO> listDTO = list.map(obj -> new CidadeDTO(obj));
		for (int i = 0; i < listDTO.getSize(); i++) {
			listDTO.getContent().get(i).getEstado()
					.setQtdCidades(cidadeService.findQtdCidadesByUf(listDTO.getContent().get(i).getEstado().getUf()));
		}
		return ResponseEntity.ok().body(listDTO);
	}

}
