package com.challengealura.Literalura.repository;

import com.challengealura.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByYearNacimientoBeforeAndYearFallecimientoAfter(int year1, int year2);
}
