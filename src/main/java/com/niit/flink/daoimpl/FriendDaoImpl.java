package com.niit.flink.daoimpl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.flink.dao.FriendDao;
import com.niit.flink.model.Friend;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {
	
	@Autowired
	SessionFactory sessionFactory;
       
	 public FriendDaoImpl(SessionFactory sessionFactory) {
		 
		 this.sessionFactory=sessionFactory;
		
	}

	public boolean sendRequest(Friend f) {
		 
		this.sessionFactory.getCurrentSession().save(f);
		
		return true;
	}
	public ArrayList<String> fetchFriendList(String userid){
		
		String uhql="select friendid from Friend where userid='"+userid+"'";
		String fhql="select userid   from Friend where friendid='"+userid+"'";
         Query uq=sessionFactory.getCurrentSession().createQuery(uhql);
         Query fq=sessionFactory.getCurrentSession().createQuery(fhql);
         
         @SuppressWarnings("unchecked")
		ArrayList<String> uslist=(ArrayList<String>) uq.list();
         @SuppressWarnings("unchecked")
		ArrayList<String> frlist=(ArrayList<String>) fq.list();
            uslist.addAll(frlist);
         
	return uslist;
		
	}

	public ArrayList<String> fetchFriends(String userid) {
		String uhql="select username from UserDetails";
		Query q=sessionFactory.getCurrentSession().createQuery(uhql);
		ArrayList<String> allulist=(ArrayList<String>) q.list();
		
		
	ArrayList<String> flist=fetchFriendList(userid);
                flist.add(userid);
                allulist.removeAll(flist);
	         
		
		return allulist;
	}

	
	public ArrayList<Friend> objectFriendList(String userid){
		
		String uhql="from Friend where userid='"+userid+"'";
		String fhql="from Friend where friendid='"+userid+"'";
         Query uq=sessionFactory.getCurrentSession().createQuery(uhql);
         Query fq=sessionFactory.getCurrentSession().createQuery(fhql);
         
         @SuppressWarnings("unchecked")
		ArrayList<Friend> uslist=(ArrayList<Friend>) uq.list();
         @SuppressWarnings("unchecked")
		ArrayList<Friend> frlist=(ArrayList<Friend>) fq.list();
            uslist.addAll(frlist);
         
	return uslist;
		
	}

}
