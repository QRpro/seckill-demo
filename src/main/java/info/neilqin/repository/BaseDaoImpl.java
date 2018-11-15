package info.neilqin.repository;

import info.neilqin.exceptions.GlobalException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 */
public abstract class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    protected static final Log LOG = LogFactory.getLog(BaseDaoImpl.class);

    public static final String SQL_INSERT = "insert";
    public static final String SQL_BATCH_INSERT = "batchInsert";
    public static final String SQL_UPDATE_BY_ID = "updateByPrimaryKey";
    public static final String SQL_BATCH_UPDATE_BY_IDS = "batchUpdateByIds";
    public static final String SQL_BATCH_UPDATE_BY_COLUMN = "batchUpdateByColumn";
    public static final String SQL_SELECT_BY_ID = "selectByPrimaryKey";
    public static final String SQL_LIST_BY_COLUMN = "listByColumn";
    public static final String SQL_COUNT_BY_COLUMN = "getCountByColumn";
    public static final String SQL_DELETE_BY_ID = "deleteByPrimaryKey";
    public static final String SQL_BATCH_DELETE_BY_IDS = "batchDeleteByIds";
    public static final String SQL_BATCH_DELETE_BY_COLUMN = "batchDeleteByColumn";
    public static final String SQL_LIST_PAGE = "listPage";
    public static final String SQL_LIST_BY = "listBy";


    /**
     * 注入SqlSessionTemplate实例(要求Spring中进行SqlSessionTemplate的配置).
     * 可以调用sessionTemplate完成数据库操作.
     */
    @Autowired
    private SqlSessionTemplate sessionTemplate;

    public SqlSessionTemplate getSessionTemplate() {
        return sessionTemplate;
    }

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    public SqlSession getSqlSession() {
        return super.getSqlSession();
    }

    /**
     * 单条插入数据.
     */
    public int insert(T entity) {
        int result = sessionTemplate.insert(getStatement(SQL_INSERT), entity);
        if (result <= 0) {
            throw new  GlobalException("插入失败");
        }
        return result;
    }

    /**
     * 批量插入数据.
     */
    public int insert(List<T> list) {
        if (list.isEmpty() || list.size() <= 0) {
            return 0;
        }
        int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);
        if (result <= 0) {
            throw new  GlobalException("插入失败");
        }
        return result;
    }

    /**
     * 根据id单条更新数据.
     */
    public int update(T entity) {
        int result = sessionTemplate.update(getStatement(SQL_UPDATE_BY_ID), entity);
        if (result <= 0) {
            throw new  GlobalException("插入失败");
        }
        return result;
    }

    /**
     * 根据id批量更新数据.
     */
    public int update(List<T> list) {
        if (list.isEmpty() || list.size() <= 0) {
            return 0;
        }
        int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_IDS), list);
        if (result <= 0) {
            throw new  GlobalException("插入失败");
        }
        return result;
    }

    /**
     * 根据column批量更新数据.
     */
    public int update(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return 0;
        }
        int result = sessionTemplate.update(getStatement(SQL_BATCH_UPDATE_BY_COLUMN), paramMap);
        if (result <= 0) {
            throw new  GlobalException("插入失败");
        }
        return result;
    }

    /**
     * 根据id查询数据.
     */
    public T getById(String id) {
        return sessionTemplate.selectOne(getStatement(SQL_SELECT_BY_ID), id);
    }

    /**
     * 根据column查询数据.
     */
    public T getByColumn(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectOne(getStatement(SQL_LIST_BY_COLUMN), paramMap);
    }

    /**
     * 根据条件查询 getBy: selectOne <br/>
     * 
     * @param paramMap
     * @return
     */
    public T getBy(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectOne(getStatement(SQL_LIST_BY), paramMap);
    }
    
    /**
     * 根据条件查询列表数据.
     */
    public List<T> listBy(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectList(getStatement(SQL_LIST_BY), paramMap);
    }

    /**
     * 根据column查询列表数据.
     */
    public List<T> listByColumn(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectList(getStatement(SQL_LIST_BY_COLUMN), paramMap);
    }

    /**
     * 根据column查询记录数.
     */
    public Long getCountByColumn(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return null;
        }
        return sessionTemplate.selectOne(getStatement(SQL_COUNT_BY_COLUMN), paramMap);
    }

    /**
     * 根据id删除数据.
     */
    public int delete(String id) {
        return (int) sessionTemplate.delete(getStatement(SQL_DELETE_BY_ID), id);
    }

    /**
     * 根据id批量删除数据.
     */
    public int delete(List<T> list) {
        if (list.isEmpty() || list.size() <= 0) {
            return 0;
        } else {
            return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_IDS), list);
        }
    }

    /**
     * 根据column批量删除数据.
     */
    public int delete(Map<String, Object> paramMap) {
        if (paramMap == null) {
            return 0;
        } else {
            return (int) sessionTemplate.delete(getStatement(SQL_BATCH_DELETE_BY_COLUMN), paramMap);
        }
    }

    /**
     * 函数功能说明 ： 获取Mapper命名空间. 修改者名字： Along 修改日期： 2016-1-8 修改内容：
     * 
     * @参数：@param sqlId
     * @参数：@return
     * @return：String
     * @throws
     */
    public String getStatement(String sqlId) {
        String name = this.getClass().getName();
        // 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
    }

}
