package com.treinamento.microservices.springbootlab6;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.List;

@Secured("ROLE_USER")
@RepositoryRestResource(path = "/disciplina")
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@RestResource(path = "next", rel = "next")
	@Query("select d from Disciplina d where d.dataInicio > current_date")
	List<Disciplina> listNextCourses();

}
