package com.navarro.xptosystems.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navarro.xptosystems.domain.Cidade;
import com.navarro.xptosystems.domain.Estado;
import com.navarro.xptosystems.repository.CidadeRepository;
import com.navarro.xptosystems.repository.EstadoRepository;

@Service
public class DBService {

	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public void instantiateTestDataBase() throws ParseException {

		File arquivoCSV = new File("C:\\temp\\ws\\xptosystems\\Desafio Cidades - Back End.csv");

		try {
			@SuppressWarnings("resource")
			Scanner leitor = new Scanner(arquivoCSV);
			String linhasDoArquivo = new String();
			leitor.nextLine();
			while (leitor.hasNext()) {
				linhasDoArquivo = leitor.nextLine();
				String[] valoresEntreVirgulas = linhasDoArquivo.split(",");

				Boolean vbCapital = false;
				if (valoresEntreVirgulas[3].equals("true")) {
					vbCapital = true;
				}

				Estado estAux = estadoRepository.findByUf(valoresEntreVirgulas[1]);

				if (estAux == null) {
					Estado est = new Estado(null, valoresEntreVirgulas[1], valoresEntreVirgulas[1]);
					Cidade cid = new Cidade(null, Integer.parseInt(valoresEntreVirgulas[0]), valoresEntreVirgulas[2],
							est, vbCapital, Double.parseDouble(valoresEntreVirgulas[4]),
							Double.parseDouble(valoresEntreVirgulas[5]), valoresEntreVirgulas[6],
							valoresEntreVirgulas[7], valoresEntreVirgulas[8], valoresEntreVirgulas[9]);

					est.getCidades().addAll(Arrays.asList(cid));

					estadoRepository.saveAll(Arrays.asList(est));
					cidadeRepository.saveAll(Arrays.asList(cid));
				} else {
					Cidade cid = new Cidade(null, Integer.parseInt(valoresEntreVirgulas[0]), valoresEntreVirgulas[2],
							estAux, vbCapital, Double.parseDouble(valoresEntreVirgulas[4]),
							Double.parseDouble(valoresEntreVirgulas[5]), valoresEntreVirgulas[6],
							valoresEntreVirgulas[7], valoresEntreVirgulas[8], valoresEntreVirgulas[9]);

					cidadeRepository.saveAll(Arrays.asList(cid));
				}
			}

		} catch (FileNotFoundException e) {
		}
	}
}
