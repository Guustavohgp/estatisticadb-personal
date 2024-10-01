package com.estatisticapersonal.estatistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.estatisticapersonal.estatistica.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    // Contagem por sexo
    @Query("SELECT COUNT(p) FROM Pessoa p WHERE p.sexo = :sexo")
    long contarPorSexo(@Param("sexo") String sexo);

    // Contagem total de pessoas
    @Query("SELECT COUNT(p) FROM Pessoa p")
    long contarTotal();

    // Média de idade
    @Query("SELECT AVG(p.idade) FROM Pessoa p")
    double calcularMediaIdade();

    // Contagem por biotipo
    @Query("SELECT COUNT(p) FROM Pessoa p WHERE p.biotipo = :biotipo")
    long contarPorBiotipo(@Param("biotipo") String biotipo);

    // Contagem por experiência
    @Query("SELECT COUNT(p) FROM Pessoa p WHERE p.experiencia = :experiencia")
    long contarPorExperiencia(@Param("experiencia") String experiencia);
}
