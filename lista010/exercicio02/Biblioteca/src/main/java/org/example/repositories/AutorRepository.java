package org.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.models.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
}
