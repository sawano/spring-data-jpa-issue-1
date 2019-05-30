package se.sawano.spring.data.jpa.issue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

    @Autowired
    PersonRepository myRepository;

    @Test
    public void readFromDatabase() {
        assertThatCode(() -> myRepository.findAllByCity("some-city", Pageable.unpaged()))
                .doesNotThrowAnyException();

        assertThatCode(() -> myRepository.findAllByName("some-name", Pageable.unpaged()))
                .doesNotThrowAnyException();
    }

}
