package com.sinse.mvcappp.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sinse.mvcappp.exception.CommentException;
import com.sinse.mvcappp.model.Comment;
import com.sinse.mvcappp.mybatis.MybatisConfig;

public class CommentDAO {
	
	MybatisConfig config=MybatisConfig.getInstance();
	
	//모든 댓글 가져오기
	public List selctAll() {
		return null;
	}
	
	//해당 뉴스기사에 소속된 댓글만 가져오기 
	public List selectByNewsId(int news_id) {
		SqlSession sqlSession=config.getSqlSession();
		List list=sqlSession.selectList("Comment.selectByNewsId", news_id);
		sqlSession.close();
		return list;
	}
	
	public Comment select(int comment_id) {
		return null;
	}
	
	public void insert(Comment comment) {	
		SqlSession sqlSession=config.getSqlSession();
		int result=sqlSession.insert("Comment.insert", comment);
		if(result <1) {
			throw new CommentException("등록 실패");
		}
		sqlSession.commit();
		sqlSession.close();
	}
	
	public void update(Comment comment) {		
	}
	public void delete(int comment_id) {
	}
}







