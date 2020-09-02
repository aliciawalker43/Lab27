package GC.Lab27;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import GC.Lab27.dao.MovieDao;
import GC.Lab27.pojo.Movie;

@RestController
public class MovieApiController {

	@Autowired
	private MovieDao dao;
	
	
	//Get a list of all movies
	@GetMapping("/movie")
    public List<Movie> showAllMovies() {
	return dao.findAll();
	}
	
	// Get a list of all movies in a specific category
	// User specifies category as a query parameter
	@GetMapping("/movie-category")
	public List<Movie> showByCategory(@RequestParam(required=false) String category) {
		return dao.findByCategory( category);
	}
	
	//. Get a list of movies which have a keyword in their title
	// User specifies title as a query parameter
	@GetMapping("/movie-title")
	public List<Movie> showByTitle(@RequestParam(required=false) String title) {
		return dao.findByTitle(title);
	}
	
	// Get info about a specific movie
	//User specifies id as a path variable
	@GetMapping("/movie/{id}")
	public Movie getInfo(@PathVariable ("id") Long id) {
		return dao.findById(id).get();
	}
	
	
	//Get a list of all movie categories
	@GetMapping("/categories")
	public String listCategories(){
	List<Movie> list = dao.findAll();
	 String cate = null;
	
	  for(int i=0; i< list.size(); i++) {
		   cate= list.get(i).getCategory();
	  }
	return cate;
}


	//Get a random movie pick
	@GetMapping("/movie-random")
	public Optional<Movie> findRandom() {
		List<Movie> list= dao.findAll();
		
		int upper= list.size();
		int lower=1;
	
		Long r = (long) (Math.random() * (upper - lower)) + lower;
		return dao.findById(r);
		
	}
	
	//6. Get a list random movie pick from a specific category
	// User specifies category as a query parameter
	@GetMapping("/category-random")
	public List<Movie> findRandomInCategory(@RequestParam ("category") String category) {
		List<Movie> list= dao.findByCategory(category);
		Collections.shuffle(list);
		
		return list;
	}	
	
	//7. Get a list of random movie picks
	//User specifies quantity as a query parameter*/
		@GetMapping("/random-pick")
		public List<Movie> findRandomAmount(@RequestParam ("number") int num) {
			List<Movie> list= dao.findAll();
			Collections.shuffle(list);
			List<Movie> newlist= null;
			
			for(int i=0; i< num; i++) { 
				newlist.add(list.get(i));
			}
				return newlist;
			
			
}
}

