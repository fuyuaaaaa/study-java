package top.fuyuaaa.leetcode.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : fuyuaaa
 * @date : 2020-08-25 23:57
 */
@Data
public class FinishSolutionPo implements Serializable {
    private static final long serialVersionUID = 2536817929031534158L;
    private Long id;
    private String solutionId;
    private String solutionName;
    private String solutionUrl;
    private String finishTime;
}
