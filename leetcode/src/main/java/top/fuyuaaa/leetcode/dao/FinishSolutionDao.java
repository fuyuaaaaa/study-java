package top.fuyuaaa.leetcode.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : fuyuaaa
 * @date : 2020-08-25 23:54
 */
@Mapper
public interface FinishSolutionDao {

    @Insert("INSERT INTO finish_solution (solution_id,solution_name,solution_url,finish_time) values (#{solutionId},#{solutionName},#{solutionUrl},now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(FinishSolutionPo finishSolutionPo);

    @Select("SELECT * FROM finish_solution")
    List<FinishSolutionPo> selectAll();
}
