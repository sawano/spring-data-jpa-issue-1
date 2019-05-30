# Spring Data JPA issue

Example code to illustrate issue with `org.springframework.data.domain.Slice` return type in 
`PagingAndSortingRepositories`.

[This test](https://github.com/sawano/spring-data-jpa-issue-1/blob/master/src/test/java/se/sawano/spring/data/jpa/issue/RepositoryTests.java#L24)
 should not fail when calling [this method](https://github.com/sawano/spring-data-jpa-issue-1/blob/master/src/main/java/se/sawano/spring/data/jpa/issue/PersonRepository.java#L12).
