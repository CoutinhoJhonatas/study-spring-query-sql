package com.devsuperior.uri2611.repositores;

import com.devsuperior.uri2611.MovieProjection;
import com.devsuperior.uri2611.dto.MovieProjectionDTO;
import com.devsuperior.uri2611.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true,
            value = "SELECT m.id, m.name " +
                    "FROM movies m " +
                    "INNER JOIN genres g ON g.id = m.id_genres " +
                    "WHERE UPPER(g.description) = UPPER(:genre)")
    List<MovieProjection> search(String genre);

    @Query("SELECT new com.devsuperior.uri2611.dto.MovieProjectionDTO(m.id, m.name) " +
            "FROM Movie m " +
            "WHERE UPPER(m.genre.description) = UPPER(:genre)")
    List<MovieProjectionDTO> searchJpql(String genre);



}
