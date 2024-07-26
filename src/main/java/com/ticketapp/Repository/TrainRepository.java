package com.ticketapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ticketapp.entities.Train;


public interface TrainRepository extends JpaRepository<Train, Integer> {
   
	@Query("select t from Train t where t.sourceStation like %:source  and t.destinationStation like %:destination ")
	List<Train> findBySourceAndDestination(@Param("source") String source, @Param("destination") String destination);
//	@Query(value = "SELECT * FROM train WHERE source_station LIKE '%' || :source || '%' AND destination_station LIKE '%' || :destination || '%'", nativeQuery = true)
//	List<Train> findBySourceAndDestination(@Param("source") String source, @Param("destination") String destination);
}
