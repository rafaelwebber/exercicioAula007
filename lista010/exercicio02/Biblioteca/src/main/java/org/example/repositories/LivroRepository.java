package org.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
}
