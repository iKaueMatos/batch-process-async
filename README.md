# Processamento Assíncrono com Spring Batch

Este projeto tem como objetivo demonstrar como otimizar jobs em Spring Batch com o uso de processamento assíncrono, alcançando ganhos significativos de performance. A redução de tempo de execução do job foi de até 88%, passando de **2m30s301ms** para **18s238ms**.

### Desempenho Antes e Depois da Otimização

- **Tempo antes da otimização**: 2 minutos, 30 segundos e 301 milissegundos
- **Tempo depois da otimização**: 18 segundos e 238 milissegundos
- **Redução de performance**: 88% de redução no tempo de execução!

## Objetivo do Projeto

O objetivo principal desse projeto é ilustrar como o uso de processamento assíncrono no Spring Batch pode melhorar significativamente a performance de jobs em larga escala, principalmente em cenários de grande volume de dados.

## Roteiro de Desenvolvimento

O processo de otimização foi dividido nas seguintes etapas:

- [x] **Motivação e necessidade de otimização**: Entenda o cenário inicial e a necessidade de otimizar o processamento de grandes volumes de dados.
  
- [x] **Projeto inicial**: A configuração do projeto Spring Batch antes da implementação do processamento assíncrono. Aqui, você verá como o processamento foi feito de forma sequencial, resultando em um desempenho subótimo.

- [x] **Adição do processamento assíncrono**: Introduzindo o conceito de processamento assíncrono para reduzir o tempo de execução. Vamos utilizar técnicas como o processamento em paralelo e o uso de threads para realizar múltiplas operações simultaneamente, aproveitando melhor os recursos do sistema.

- [x] **Considerações finais**: A conclusão da otimização e uma análise dos resultados obtidos com o processamento assíncrono. Refletiremos sobre como a implementação pode ser expandida e aplicada em outros cenários de processamento em lote.

## Como Funciona o Spring Batch

O Spring Batch é uma framework poderosa para o processamento de grandes volumes de dados em batch. Ele permite que você divida seu processamento em unidades menores e paralelize as tarefas para melhorar a performance. Neste projeto, o foco foi otimizar o processamento utilizando threads e processamento assíncrono.

### Principais Componentes do Spring Batch

- **Job**: A unidade de trabalho que contém os passos a serem executados.
- **Step**: Cada etapa do job. Cada step pode ter um reader, um processor e um writer configurados.
- **ItemReader**: Responsável por ler os dados de uma fonte (por exemplo, banco de dados, arquivo CSV).
- **ItemProcessor**: Processa os dados lidos antes de enviá-los ao writer.
- **ItemWriter**: Responsável por escrever os dados em um destino (por exemplo, banco de dados, arquivo de saída).

## Processamento Assíncrono no Spring Batch

O processamento assíncrono no Spring Batch é alcançado utilizando **multithreading** ou **paralelismo**. A ideia é que cada `Step` do job seja executado em threads separadas, permitindo que múltiplas tarefas sejam realizadas ao mesmo tempo, sem bloqueio de recursos.

### Como Implementar o Processamento Assíncrono

1. **Configuração do Job**: O primeiro passo é configurar o job para usar múltiplos threads, dividindo o processamento de dados em pequenas tarefas paralelizadas.

2. **Usando o `TaskExecutor`**: O `TaskExecutor` do Spring Batch permite configurar a execução paralela dos `Steps`. Com ele, é possível controlar o número de threads a serem utilizadas.

3. **Configurando o `SimpleAsyncTaskExecutor` ou `ThreadPoolTaskExecutor`**: Dependendo do caso, você pode usar executores diferentes para gerenciar a execução assíncrona. O `SimpleAsyncTaskExecutor` é simples e roda as tarefas em threads separadas, enquanto o `ThreadPoolTaskExecutor` permite controlar o número de threads ativas ao mesmo tempo.

4. **Exemplo de código**: 

```java
@Bean
public Job asyncJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    return jobBuilderFactory.get("asyncJob")
            .start(asyncStep())
            .build();
}

@Bean
public Step asyncStep() {
    return stepBuilderFactory.get("asyncStep")
            .<ItemType, ItemType>chunk(10)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .taskExecutor(taskExecutor())
            .build();
}

@Bean
public TaskExecutor taskExecutor() {
    return new SimpleAsyncTaskExecutor();
}
```

## Como Rodar o Projeto

### Pré-requisitos

1. **Java 17 ou superior**: O projeto foi desenvolvido utilizando Java 17.
2. **Spring Boot**: O projeto utiliza o Spring Boot para facilitar a configuração e execução do Spring Batch.

### Passos para Executar

1. Clone o repositório:
    ```bash
    git clone https://github.com/iKaueMatos/batch-process-async
    cd batch-process-async
    ```

2. Compile o projeto:
    ```bash
    ./mvnw clean install
    ```

3. Execute o job:
    ```bash
    ./mvnw spring-boot:run
    ```

4. Monitore os logs para verificar a execução do job e a redução do tempo de processamento.

## Resultados Esperados

Após a implementação do processamento assíncrono, você deve observar uma significativa redução no tempo de execução do job. Isso ocorre porque as tarefas são divididas e processadas paralelamente, aproveitando melhor os recursos do sistema e reduzindo o tempo total de processamento.

## Referências

- [Vídeo do Youtube explicando o processo de otimização (Giuliana Bezerra)](https://youtu.be/AbQcWO91Bx4)
- [Documentação oficial do Spring Batch](https://docs.spring.io/spring-batch/docs/current/reference/html/)

## Conclusão

Com a implementação de processamento assíncrono no Spring Batch, conseguimos melhorar significativamente a performance de jobs em grande escala. Além disso, a flexibilidade do Spring Batch permite que você personalize o processamento de acordo com a necessidade do seu projeto, seja utilizando execução paralela ou distribuída.

--- 