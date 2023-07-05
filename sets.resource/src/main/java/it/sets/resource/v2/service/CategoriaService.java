package it.sets.resource.v2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sets.resource.v2.model.Categoria;
import it.sets.resource.v2.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		
		return categoriaRepository.findAll();
	}

	public Categoria findbyId(Long id) {
		
		return categoriaRepository.findById(id).get();
	}

	public Categoria addCategoria(Categoria categoria) {
		
		return categoriaRepository.save(categoria);
	}

	public Categoria updateCategoria(Categoria categoria, Long id) {
		Categoria toUpdate = categoriaRepository.findById(id).get();
		toUpdate.setTitolo(categoria.getTitolo());
		toUpdate.setColore(categoria.getColore());
		toUpdate.setNota(categoria.getNota());
		return categoriaRepository.save(toUpdate);
	}
	
	

}
