package GC.Lab27.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import GC.Lab27.pojo.Movie;

public interface MovieDao extends JpaRepository<Movie,Long> {

	List<Movie> findByCategory(String category);

	List<Movie> findByTitle(String title);

	
	
	

	
	
	
}
