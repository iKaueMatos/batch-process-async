package br.com.batch.asyncbatch.module.domain.model;

public record Person(Long id, String name, String email, String datebirth, Integer age, String thumbnailUrl) {
}
