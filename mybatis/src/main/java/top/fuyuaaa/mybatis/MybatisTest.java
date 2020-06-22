package top.fuyuaaa.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author : fuyuaaa
 * @date : 2020-06-18 15:16
 */
public class MybatisTest {

    public static void main(String[] args)throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        try {
            MiniAppUserDao mapper = session.getMapper(MiniAppUserDao.class);
            MiniAppUserDo miniAppUserDo = mapper.findByOpenid("omQSR4iwRI9D5p1Rrb4bWCjssAqA");
            System.out.println(miniAppUserDo);
        } finally {
            session.close();
        }
    }
}
