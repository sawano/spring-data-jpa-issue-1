package se.sawano.spring.data.jpa.issue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Slice<Person> findAllByName(String name, Pageable pageable);

    Page<Person> findAllByCity(String name, Pageable pageable);
}
