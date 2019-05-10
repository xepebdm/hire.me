package br.com.bemobi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bemobi.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

}
