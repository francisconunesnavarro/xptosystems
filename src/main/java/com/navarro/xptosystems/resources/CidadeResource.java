package com.navarro.xptosystems.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.navarro.xptosystems.domain.Cidade;
import com.navarro.xptosystems.dto.CidadeDTO;
import com.navarro.xptosystems.resources.utils.URL;
import com.navarro.xptosystems.services.CidadeService;

@Controller
@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<CidadeDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cidade> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CidadeDTO> listDTO = list.map(obj -> new CidadeDTO(obj));
		for (int i = 0; i < listDTO.getSize(); i++) {
			listDTO.getContent().get(i).getEstado()
					.setQtdCidades(service.findQtdCidadesByUf(listDTO.getContent().get(i).getEstado().getUf()));
		}
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findAll() {
		List<Cidade> list = service.findAll();
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		for (int i = 0; i < listDTO.size(); i++) {
			listDTO.get(i).getEstado().setQtdCidades(service.findQtdCidadesByUf(listDTO.get(i).getEstado().getUf()));
		}
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cidade> find(@PathVariable Integer id) {
		Cidade obj = service.find(id);
		obj.getEstado().setQtdCidades(service.findQtdCidadesByUf(obj.getEstado().getUf()));
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CidadeDTO objDto) {
		Cidade obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CidadeDTO objDto, @PathVariable Integer id) {
		Cidade obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/capitais", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCapitais() {
		List<Cidade> list = service.findCapitais();
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		for (int i = 0; i < listDTO.size(); i++) {
			listDTO.get(i).getEstado().setQtdCidades(service.findQtdCidadesByUf(listDTO.get(i).getEstado().getUf()));
		}
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/qtdcidades", method = RequestMethod.GET)
	public ResponseEntity<String> findQtdCidades() {
		Integer qtdAux = service.findQtdCidades();
		String obj = "Quantidade de registros: " + qtdAux.toString();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/ibge/{ibgeId}", method = RequestMethod.GET)
	public ResponseEntity<Page<CidadeDTO>> findByIbge(@PathVariable Integer ibgeId,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cidade> list = service.findByIbge(ibgeId, page, linesPerPage, orderBy, direction);
		Page<CidadeDTO> listDTO = list.map(obj -> new CidadeDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "/qtdregistros", method = RequestMethod.GET)
	public ResponseEntity<String> findQtdRegistros() {
		Integer qtdAux = service.findQtdCidades();
		String obj = "Quantidade de registros: " + qtdAux.toString();
		qtdAux = service.findQtdRegistros("IBGE ID");
		obj = obj + "\nQuantidade de registros por IBGE ID: " + qtdAux.toString();
		qtdAux = service.findQtdRegistros("Nome");
		obj = obj + "\nQuantidade de registros por Nome: " + qtdAux.toString();
		qtdAux = service.findQtdRegistros("UF");
		obj = obj + "\nQuantidade de registros por UF: " + qtdAux.toString();
		qtdAux = service.findQtdRegistros("MicroRegiao");
		obj = obj + "\nQuantidade de registros por MicroRegiao: " + qtdAux.toString();
		qtdAux = service.findQtdRegistros("MesoRegiao");
		obj = obj + "\nQuantidade de registros por MesoRegiao: " + qtdAux.toString();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/qtdregistros/ibge", method = RequestMethod.GET)
	public ResponseEntity<String> findQtdRegistrosIbge() {
		Integer qtdAux = service.findQtdRegistros("IBGE ID");
		String obj = "Quantidade de registros por IBGE ID: " + qtdAux.toString();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/qtdregistros/nome", method = RequestMethod.GET)
	public ResponseEntity<String> findQtdRegistrosName() {
		Integer qtdAux = service.findQtdRegistros("Nome");
		String obj = "Quantidade de registros por Nome: " + qtdAux.toString();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/qtdregistros/uf", method = RequestMethod.GET)
	public ResponseEntity<String> findQtdRegistrosUf() {
		Integer qtdAux = service.findQtdRegistros("UF");
		String obj = "Quantidade de registros por UF: " + qtdAux.toString();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/qtdregistros/microregiao", method = RequestMethod.GET)
	public ResponseEntity<String> findQtdByMicroRegion() {
		Integer qtdAux = service.findQtdRegistros("MicroRegiao");
		String obj = "Quantidade de registros por MicroRegiao: " + qtdAux.toString();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/qtdregistros/mesoregiao", method = RequestMethod.GET)
	public ResponseEntity<String> findQtdByMesoRegion() {
		Integer qtdAux = service.findQtdRegistros("MesoRegiao");
		String obj = "Quantidade de registros por MesoRegiao: " + qtdAux.toString();
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/busca", method = RequestMethod.GET)
	public ResponseEntity<Page<CidadeDTO>> findPageName(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "uf", defaultValue = "") String uf,
			@RequestParam(value = "ibge", defaultValue = "") String ibgeId,
			@RequestParam(value = "noAccents", defaultValue = "") String noAccents,
			@RequestParam(value = "alterNames", defaultValue = "") String alterNames,
			@RequestParam(value = "microregion", defaultValue = "") String microregion,
			@RequestParam(value = "mesoregion", defaultValue = "") String mesoregion,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nameDecoded = URL.decodeParam(name, "U");
		String ufDecoded = URL.decodeParam(uf, "U");
		String ibgeIdDecoded = ibgeId.toString();
		String noAccentsDecoded = URL.decodeParam(noAccents, "U");
		String alterNamesDecoded = URL.decodeParam(alterNames, "U");
		String microregionDecoded = URL.decodeParam(microregion, "U");
		String mesoregionDecoded = URL.decodeParam(mesoregion, "U");
		Page<Cidade> list;
		if (!name.isEmpty()) {
			list = service.findCidadeLikeName(nameDecoded, page, linesPerPage, orderBy, direction);
		} else if (!uf.isEmpty()) {
			list = service.findCidadeLikeUf(ufDecoded, page, linesPerPage, orderBy, direction);
		} else if (!ibgeId.isEmpty()) {
			list = service.findCidadeLikeIbge(ibgeIdDecoded, page, linesPerPage, orderBy, direction);
		} else if (!noAccents.isEmpty()) {
			list = service.findCidadeLikeNoAccents(noAccentsDecoded, page, linesPerPage, orderBy, direction);
		} else if (!alterNames.isEmpty()) {
			list = service.findCidadeLikeAlterName(alterNamesDecoded, page, linesPerPage, orderBy, direction);
		} else if (!microregion.isEmpty()) {
			list = service.findCidadeLikeMicroRegion(microregionDecoded, page, linesPerPage, orderBy, direction);
		} else if (!mesoregion.isEmpty()) {
			list = service.findCidadeLikeMesoRegion(mesoregionDecoded, page, linesPerPage, orderBy, direction);
		} else {
			list = service.findCidadeLikeName(nameDecoded, page, linesPerPage, orderBy, direction);
		}
		Page<CidadeDTO> listDTO = list.map(obj -> new CidadeDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
