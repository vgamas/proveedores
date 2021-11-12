package com.mintic.lagenerica.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.lagenerica.model.Proveedor;
import com.mintic.lagenerica.repository.ProveedorRepository;

@CrossOrigin(origins = "http://localhost:9091") // Seguridad
@RestController
@RequestMapping("/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@PostMapping("/guardar")
	public ResponseEntity<?> crearProveedor(@RequestBody Proveedor proveedor){
		return ResponseEntity.status(HttpStatus.CREATED).body(proveedorRepository.save(proveedor));
	}
	
	@GetMapping("/listar")
	public List<Proveedor> listarProveedor() {
		List<Proveedor> listaProveedores = StreamSupport.stream(proveedorRepository.findAll().spliterator(), false).collect(Collectors.toList());
		
		return listaProveedores;
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<?> bucarProveedor(@PathVariable(value = "id") Long id) {

		Optional<Proveedor> oProveedor = proveedorRepository.findById(id) ;
		
		if (oProveedor.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(oProveedor);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> borrarProveedorporId(@PathVariable(value = "id") Long id) {

		Optional<Proveedor> oProveedor = proveedorRepository.findById(id) ;
		
		if (oProveedor.isEmpty())
			return ResponseEntity.notFound().build();
		
		proveedorRepository.deleteById(id);
		
		return ResponseEntity.ok(oProveedor);
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<?> borrarTodos() {

		Proveedor oProveedor = new Proveedor();
		
		proveedorRepository.deleteAll();;
		
		return ResponseEntity.ok(oProveedor);
	}


	@PutMapping("actualizar")
	public ResponseEntity<?> actualizarProveedor(@RequestBody Proveedor proveedor) {

		Optional<Proveedor> proveedorAnt = proveedorRepository.findById(proveedor.getNitproveedor());
		
		if(proveedorAnt.isEmpty())
			return ResponseEntity.notFound().build();
		
		proveedorAnt.get().setNombre_proveedor(proveedor.getNombre_proveedor());
		proveedorAnt.get().setDireccion_proveedor(proveedor.getDireccion_proveedor());
		proveedorAnt.get().setCiudad_proveedor(proveedor.getCiudad_proveedor());
		proveedorAnt.get().setTelefono_proveedor(proveedor.getTelefono_proveedor());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(proveedorRepository.save(proveedorAnt.get()));	
	}
	
}
