package com.estatisticapersonal.estatistica.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estatisticapersonal.estatistica.repository.PessoaRepository;

@Service
public class EstatisticaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void gerarEstatisticas() {
        long total = pessoaRepository.contarTotal();

        // Porcentagem por sexo
        long homens = pessoaRepository.contarPorSexo("Masculino");
        long mulheres = pessoaRepository.contarPorSexo("Feminino");
        long outro = pessoaRepository.contarPorSexo("Outro");

        double porcentagemHomens = (double) homens / total * 100;
        double porcentagemMulheres = (double) mulheres / total * 100;
        double porcentagemOutro = (double) outro / total * 100;

        // Média de idade
        double mediaIdade = pessoaRepository.calcularMediaIdade();

        // Porcentagem por biotipo
        long endomorfo = pessoaRepository.contarPorBiotipo("Endomorfo");
        long ectomorfo = pessoaRepository.contarPorBiotipo("Ectomorfo");
        long mesomorfo = pessoaRepository.contarPorBiotipo("Mesomorfo");

        double porcentagemEndomorfo = (double) endomorfo / total * 100;
        double porcentagemEctomorfo = (double) ectomorfo / total * 100;
        double porcentagemMesomorfo = (double) mesomorfo / total * 100;

        // Porcentagem por nível de experiência
        long iniciante = pessoaRepository.contarPorExperiencia("Iniciante");
        long intermediario = pessoaRepository.contarPorExperiencia("Intermediário");
        long avancado = pessoaRepository.contarPorExperiencia("Avançado");

        double porcentagemIniciante = (double) iniciante / total * 100;
        double porcentagemIntermediario = (double) intermediario / total * 100;
        double porcentagemAvancado = (double) avancado / total * 100;

        // Exibir os resultados
        System.out.println("Porcentagem de Homens: " + porcentagemHomens + "%");
        System.out.println("Porcentagem de Mulheres: " + porcentagemMulheres + "%");
        System.out.println("Porcentagem de Outro: " + porcentagemOutro + "%");
        System.out.println("Média de Idade: " + mediaIdade);
        System.out.println("Porcentagem de Endomorfos: " + porcentagemEndomorfo + "%");
        System.out.println("Porcentagem de Ectomorfos: " + porcentagemEctomorfo + "%");
        System.out.println("Porcentagem de Mesomorfos: " + porcentagemMesomorfo + "%");
        System.out.println("Porcentagem de Iniciantes: " + porcentagemIniciante + "%");
        System.out.println("Porcentagem de Intermediários: " + porcentagemIntermediario + "%");
        System.out.println("Porcentagem de Avançados: " + porcentagemAvancado + "%");
    }
}