package com.estatisticapersonal.estatistica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estatisticapersonal.estatistica.entities.Pessoa;
import com.estatisticapersonal.estatistica.repository.PessoaRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostConstruct
    public void inicializar() {
        salvarPessoasIniciais();
        gerarAnaliseEstatistica();
    }

    private void salvarPessoasIniciais() {
        Pessoa pessoa1 = new Pessoa("João", 28, "Masculino", "Ectomorfo", "Intermediário");
        Pessoa pessoa2 = new Pessoa("Maria", 22, "Feminino", "Endomorfo", "Iniciante");
        Pessoa pessoa3 = new Pessoa("Alex", 35, "Outro", "Mesomorfo", "Avançado");
        Pessoa pessoa4 = new Pessoa("Ana", 30, "Feminino", "Ectomorfo", "Intermediário");
        Pessoa pessoa5 = new Pessoa("Pedro", 18, "Masculino", "Ectomorfo", "Intermediário");
        Pessoa pessoa6 = new Pessoa("Vitor", 21, "Masculino", "Endomorfo", "Avançado");
        Pessoa pessoa7 = new Pessoa("Stela", 22, "Outro", "Mesomorfo", "Intermediário");
        Pessoa pessoa8 = new Pessoa("Tales", 25, "Outro", "Endomorfo", "Iniciante");
        Pessoa pessoa9 = new Pessoa("Fernando", 40, "Masculino", "Ectomorfo", "Avançado");
        Pessoa pessoa10 = new Pessoa("Rebeca", 19, "Feminino", "Mesomorfo", "Intermediário");

        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);
        pessoaRepository.save(pessoa3);
        pessoaRepository.save(pessoa4);
        pessoaRepository.save(pessoa5);
        pessoaRepository.save(pessoa6);
        pessoaRepository.save(pessoa7);
        pessoaRepository.save(pessoa8);
        pessoaRepository.save(pessoa9);
        pessoaRepository.save(pessoa10);

        System.out.println("Pessoas salvas no banco de dados com sucesso!");
    }

    private void gerarAnaliseEstatistica() {
        List<Pessoa> pessoas = pessoaRepository.findAll();

        if (pessoas.isEmpty()) {
            System.out.println("Nenhuma pessoa encontrada para análise estatística.");
            return;
        }

        calcularPorcentagemSexo(pessoas);
        calcularMediaIdade(pessoas);
        calcularPorcentagemBiotipo(pessoas);
        calcularPorcentagemExperiencia(pessoas);
    }

    private void calcularPorcentagemSexo(List<Pessoa> pessoas) {
        long total = pessoas.size();
        Map<String, Long> contagemSexo = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getSexo, Collectors.counting()));

        System.out.println("\n=== Porcentagem por Sexo ===");
        contagemSexo.forEach((sexo, contagem) -> {
            double porcentagem = (contagem * 100.0) / total;
            System.out.printf("%s: %.2f%%\n", sexo, porcentagem);
        });
    }

    private void calcularMediaIdade(List<Pessoa> pessoas) {
        double mediaIdade = pessoas.stream()
                .mapToInt(Pessoa::getIdade)
                .average()
                .orElse(0);

        System.out.println("\n=== Média de Idade ===");
        System.out.printf("A média de idade é: %.2f anos\n", mediaIdade);
    }

    private void calcularPorcentagemBiotipo(List<Pessoa> pessoas) {
        long total = pessoas.size();
        Map<String, Long> contagemBiotipo = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getBiotipo, Collectors.counting()));

        System.out.println("\n=== Porcentagem por Biotipo ===");
        contagemBiotipo.forEach((biotipo, contagem) -> {
            double porcentagem = (contagem * 100.0) / total;
            System.out.printf("%s: %.2f%%\n", biotipo, porcentagem);
        });
    }

    private void calcularPorcentagemExperiencia(List<Pessoa> pessoas) {
        long total = pessoas.size();
        Map<String, Long> contagemExperiencia = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getExperiencia, Collectors.counting()));

        System.out.println("\n=== Porcentagem por Nível de Experiência ===");
        contagemExperiencia.forEach((experiencia, contagem) -> {
            double porcentagem = (contagem * 100.0) / total;
            System.out.printf("%s: %.2f%%\n", experiencia, porcentagem);
        });
    }
}
