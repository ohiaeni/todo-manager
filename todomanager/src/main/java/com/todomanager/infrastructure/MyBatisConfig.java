package com.todomanager.infrastructure;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * MyBatisの設定を行うクラスです。
 */
@Configuration
@MapperScan("com.todomanager.infrastructure")
public class MyBatisConfig {
  /**
   * SqlSessionFactoryを生成するためのBeanを定義します。
   * 
   * @param dataSource データソース
   * @return SqlSessionFactory
   * @throws Exception 生成に失敗した場合
   */
  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setMapperLocations(
        new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

    org.apache.ibatis.session.Configuration configuration =
        new org.apache.ibatis.session.Configuration();
    configuration.setMapUnderscoreToCamelCase(true);
    factoryBean.setConfiguration(configuration);

    return factoryBean.getObject();
  }

  /**
   * SqlSessionTemplateを生成するためのBeanを定義します。
   * 
   * @param sqlSessionFactory SqlSessionFactory
   * @return SqlSessionTemplate
   */
  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}
