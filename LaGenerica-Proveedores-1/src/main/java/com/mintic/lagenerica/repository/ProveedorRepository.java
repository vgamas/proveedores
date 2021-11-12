package com.mintic.lagenerica.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mintic.lagenerica.model.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, Long> {

//	List<Proveedor> findByNitproveedor(Long nit);
}
