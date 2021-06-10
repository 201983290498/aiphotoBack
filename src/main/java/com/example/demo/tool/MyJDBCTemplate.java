package com.example.demo.tool;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 重写jdbcTemplatejdbc功能扩展类
 */
public class MyJDBCTemplate extends JdbcTemplate {

  /**
   * 避免出现去查询为空集的
   * @param sql
   * @param rowMapper
   * @param args
   * @param <T>
   * @return
   * @throws DataAccessException
   */
  public <T> T queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args){
    try {
      List<T> results = (List) this.query((String) sql, (Object[]) args, (ResultSetExtractor) (new RowMapperResultSetExtractor(rowMapper, 1)));
      return DataAccessUtils.nullableSingleResult(results);
    }catch (DataAccessException e){
      return null;
    }

  }
}
