package br.com.bemobi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.bemobi.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

	@Query("select u from URL u where u.alias = :alias")
	Url findByAlias(@Param("alias")String alias);

}
