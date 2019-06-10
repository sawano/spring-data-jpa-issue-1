# Spring Data JPA issue

Example code to illustrate issue with `org.springframework.data.domain.Slice` return type in 
`PagingAndSortingRepositories`.

**Jira issue**: [https://jira.spring.io/browse/DATAJPA-1554](https://jira.spring.io/browse/DATAJPA-1554)

[This test](https://github.com/sawano/spring-data-jpa-issue-1/blob/master/src/test/java/se/sawano/spring/data/jpa/issue/RepositoryTests.java#L24)
 should not fail when calling [this method](https://github.com/sawano/spring-data-jpa-issue-1/blob/master/src/main/java/se/sawano/spring/data/jpa/issue/PersonRepository.java#L10).

The method `org.springframework.data.jpa.repository.query.JpaQueryExecution.SlicedExecution.doExecute()` uses a
`ParametersParameterAccessor` that can return an `Unpaged`, which in turn will throw an `UnsupportedOperationException` when invoking 
`Unpaged.getPageSize()`

```java
java.lang.UnsupportedOperationException
	at org.springframework.data.domain.Unpaged.getPageSize(Unpaged.java:78)
	at org.springframework.data.jpa.repository.query.JpaQueryExecution$SlicedExecution.doExecute(JpaQueryExecution.java:164)
	at org.springframework.data.jpa.repository.query.JpaQueryExecution.execute(JpaQueryExecution.java:91)
	at org.springframework.data.jpa.repository.query.AbstractJpaQuery.doExecute(AbstractJpaQuery.java:136)
	at org.springframework.data.jpa.repository.query.AbstractJpaQuery.execute(AbstractJpaQuery.java:125)
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.doInvoke(RepositoryFactorySupport.java:605)
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.lambda$invoke$3(RepositoryFactorySupport.java:595)
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$QueryExecutorMethodInterceptor.invoke(RepositoryFactorySupport.java:595)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:59)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:295)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:98)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.data.jpa.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:138)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:93)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.data.repository.core.support.SurroundingTransactionDetectorMethodInterceptor.invoke(SurroundingTransactionDetectorMethodInterceptor.java:61)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
	at com.sun.proxy.$Proxy73.findAllByName(Unknown Source)
```
