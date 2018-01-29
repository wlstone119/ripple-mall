package com.stone.ripple.dal.pojo.music;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stone.lava.pojo.LavaDoExample;

public class SongDoExample extends LavaDoExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SongDoExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	public boolean isDistinct() {
		return distinct;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Long> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andCTimeIsNull() {
			addCriterion("c_time is null");
			return (Criteria) this;
		}

		public Criteria andCTimeIsNotNull() {
			addCriterion("c_time is not null");
			return (Criteria) this;
		}

		public Criteria andCTimeEqualTo(Date value) {
			addCriterion("c_time =", value, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeNotEqualTo(Date value) {
			addCriterion("c_time <>", value, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeGreaterThan(Date value) {
			addCriterion("c_time >", value, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("c_time >=", value, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeLessThan(Date value) {
			addCriterion("c_time <", value, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeLessThanOrEqualTo(Date value) {
			addCriterion("c_time <=", value, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeIn(List<Date> values) {
			addCriterion("c_time in", values, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeNotIn(List<Date> values) {
			addCriterion("c_time not in", values, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeBetween(Date value1, Date value2) {
			addCriterion("c_time between", value1, value2, "cTime");
			return (Criteria) this;
		}

		public Criteria andCTimeNotBetween(Date value1, Date value2) {
			addCriterion("c_time not between", value1, value2, "cTime");
			return (Criteria) this;
		}

		public Criteria andCUserIsNull() {
			addCriterion("c_user is null");
			return (Criteria) this;
		}

		public Criteria andCUserIsNotNull() {
			addCriterion("c_user is not null");
			return (Criteria) this;
		}

		public Criteria andCUserEqualTo(String value) {
			addCriterion("c_user =", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserNotEqualTo(String value) {
			addCriterion("c_user <>", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserGreaterThan(String value) {
			addCriterion("c_user >", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserGreaterThanOrEqualTo(String value) {
			addCriterion("c_user >=", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserLessThan(String value) {
			addCriterion("c_user <", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserLessThanOrEqualTo(String value) {
			addCriterion("c_user <=", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserLike(String value) {
			addCriterion("c_user like", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserNotLike(String value) {
			addCriterion("c_user not like", value, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserIn(List<String> values) {
			addCriterion("c_user in", values, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserNotIn(List<String> values) {
			addCriterion("c_user not in", values, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserBetween(String value1, String value2) {
			addCriterion("c_user between", value1, value2, "cUser");
			return (Criteria) this;
		}

		public Criteria andCUserNotBetween(String value1, String value2) {
			addCriterion("c_user not between", value1, value2, "cUser");
			return (Criteria) this;
		}

		public Criteria andMTimeIsNull() {
			addCriterion("m_time is null");
			return (Criteria) this;
		}

		public Criteria andMTimeIsNotNull() {
			addCriterion("m_time is not null");
			return (Criteria) this;
		}

		public Criteria andMTimeEqualTo(Date value) {
			addCriterion("m_time =", value, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeNotEqualTo(Date value) {
			addCriterion("m_time <>", value, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeGreaterThan(Date value) {
			addCriterion("m_time >", value, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("m_time >=", value, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeLessThan(Date value) {
			addCriterion("m_time <", value, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeLessThanOrEqualTo(Date value) {
			addCriterion("m_time <=", value, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeIn(List<Date> values) {
			addCriterion("m_time in", values, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeNotIn(List<Date> values) {
			addCriterion("m_time not in", values, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeBetween(Date value1, Date value2) {
			addCriterion("m_time between", value1, value2, "mTime");
			return (Criteria) this;
		}

		public Criteria andMTimeNotBetween(Date value1, Date value2) {
			addCriterion("m_time not between", value1, value2, "mTime");
			return (Criteria) this;
		}

		public Criteria andMUserIsNull() {
			addCriterion("m_user is null");
			return (Criteria) this;
		}

		public Criteria andMUserIsNotNull() {
			addCriterion("m_user is not null");
			return (Criteria) this;
		}

		public Criteria andMUserEqualTo(String value) {
			addCriterion("m_user =", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserNotEqualTo(String value) {
			addCriterion("m_user <>", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserGreaterThan(String value) {
			addCriterion("m_user >", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserGreaterThanOrEqualTo(String value) {
			addCriterion("m_user >=", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserLessThan(String value) {
			addCriterion("m_user <", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserLessThanOrEqualTo(String value) {
			addCriterion("m_user <=", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserLike(String value) {
			addCriterion("m_user like", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserNotLike(String value) {
			addCriterion("m_user not like", value, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserIn(List<String> values) {
			addCriterion("m_user in", values, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserNotIn(List<String> values) {
			addCriterion("m_user not in", values, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserBetween(String value1, String value2) {
			addCriterion("m_user between", value1, value2, "mUser");
			return (Criteria) this;
		}

		public Criteria andMUserNotBetween(String value1, String value2) {
			addCriterion("m_user not between", value1, value2, "mUser");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andSongAuthorIsNull() {
			addCriterion("song_author is null");
			return (Criteria) this;
		}

		public Criteria andSongAuthorIsNotNull() {
			addCriterion("song_author is not null");
			return (Criteria) this;
		}

		public Criteria andSongAuthorEqualTo(String value) {
			addCriterion("song_author =", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorNotEqualTo(String value) {
			addCriterion("song_author <>", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorGreaterThan(String value) {
			addCriterion("song_author >", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorGreaterThanOrEqualTo(String value) {
			addCriterion("song_author >=", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorLessThan(String value) {
			addCriterion("song_author <", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorLessThanOrEqualTo(String value) {
			addCriterion("song_author <=", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorLike(String value) {
			addCriterion("song_author like", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorNotLike(String value) {
			addCriterion("song_author not like", value, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorIn(List<String> values) {
			addCriterion("song_author in", values, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorNotIn(List<String> values) {
			addCriterion("song_author not in", values, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorBetween(String value1, String value2) {
			addCriterion("song_author between", value1, value2, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAuthorNotBetween(String value1, String value2) {
			addCriterion("song_author not between", value1, value2, "songAuthor");
			return (Criteria) this;
		}

		public Criteria andSongAlbumIsNull() {
			addCriterion("song_album is null");
			return (Criteria) this;
		}

		public Criteria andSongAlbumIsNotNull() {
			addCriterion("song_album is not null");
			return (Criteria) this;
		}

		public Criteria andSongAlbumEqualTo(String value) {
			addCriterion("song_album =", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumNotEqualTo(String value) {
			addCriterion("song_album <>", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumGreaterThan(String value) {
			addCriterion("song_album >", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumGreaterThanOrEqualTo(String value) {
			addCriterion("song_album >=", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumLessThan(String value) {
			addCriterion("song_album <", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumLessThanOrEqualTo(String value) {
			addCriterion("song_album <=", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumLike(String value) {
			addCriterion("song_album like", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumNotLike(String value) {
			addCriterion("song_album not like", value, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumIn(List<String> values) {
			addCriterion("song_album in", values, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumNotIn(List<String> values) {
			addCriterion("song_album not in", values, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumBetween(String value1, String value2) {
			addCriterion("song_album between", value1, value2, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andSongAlbumNotBetween(String value1, String value2) {
			addCriterion("song_album not between", value1, value2, "songAlbum");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(String value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(String value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(String value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(String value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(String value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(String value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLike(String value) {
			addCriterion("type like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotLike(String value) {
			addCriterion("type not like", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<String> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<String> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(String value1, String value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(String value1, String value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andStatusIsNull() {
			addCriterion("status is null");
			return (Criteria) this;
		}

		public Criteria andStatusIsNotNull() {
			addCriterion("status is not null");
			return (Criteria) this;
		}

		public Criteria andStatusEqualTo(String value) {
			addCriterion("status =", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotEqualTo(String value) {
			addCriterion("status <>", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThan(String value) {
			addCriterion("status >", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusGreaterThanOrEqualTo(String value) {
			addCriterion("status >=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThan(String value) {
			addCriterion("status <", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLessThanOrEqualTo(String value) {
			addCriterion("status <=", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusLike(String value) {
			addCriterion("status like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotLike(String value) {
			addCriterion("status not like", value, "status");
			return (Criteria) this;
		}

		public Criteria andStatusIn(List<String> values) {
			addCriterion("status in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotIn(List<String> values) {
			addCriterion("status not in", values, "status");
			return (Criteria) this;
		}

		public Criteria andStatusBetween(String value1, String value2) {
			addCriterion("status between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andStatusNotBetween(String value1, String value2) {
			addCriterion("status not between", value1, value2, "status");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicIsNull() {
			addCriterion("song_album_pic is null");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicIsNotNull() {
			addCriterion("song_album_pic is not null");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicEqualTo(String value) {
			addCriterion("song_album_pic =", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicNotEqualTo(String value) {
			addCriterion("song_album_pic <>", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicGreaterThan(String value) {
			addCriterion("song_album_pic >", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicGreaterThanOrEqualTo(String value) {
			addCriterion("song_album_pic >=", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicLessThan(String value) {
			addCriterion("song_album_pic <", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicLessThanOrEqualTo(String value) {
			addCriterion("song_album_pic <=", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicLike(String value) {
			addCriterion("song_album_pic like", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicNotLike(String value) {
			addCriterion("song_album_pic not like", value, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicIn(List<String> values) {
			addCriterion("song_album_pic in", values, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicNotIn(List<String> values) {
			addCriterion("song_album_pic not in", values, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicBetween(String value1, String value2) {
			addCriterion("song_album_pic between", value1, value2, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongAlbumPicNotBetween(String value1, String value2) {
			addCriterion("song_album_pic not between", value1, value2, "songAlbumPic");
			return (Criteria) this;
		}

		public Criteria andSongDurationIsNull() {
			addCriterion("song_duration is null");
			return (Criteria) this;
		}

		public Criteria andSongDurationIsNotNull() {
			addCriterion("song_duration is not null");
			return (Criteria) this;
		}

		public Criteria andSongDurationEqualTo(String value) {
			addCriterion("song_duration =", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationNotEqualTo(String value) {
			addCriterion("song_duration <>", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationGreaterThan(String value) {
			addCriterion("song_duration >", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationGreaterThanOrEqualTo(String value) {
			addCriterion("song_duration >=", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationLessThan(String value) {
			addCriterion("song_duration <", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationLessThanOrEqualTo(String value) {
			addCriterion("song_duration <=", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationLike(String value) {
			addCriterion("song_duration like", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationNotLike(String value) {
			addCriterion("song_duration not like", value, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationIn(List<String> values) {
			addCriterion("song_duration in", values, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationNotIn(List<String> values) {
			addCriterion("song_duration not in", values, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationBetween(String value1, String value2) {
			addCriterion("song_duration between", value1, value2, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongDurationNotBetween(String value1, String value2) {
			addCriterion("song_duration not between", value1, value2, "songDuration");
			return (Criteria) this;
		}

		public Criteria andSongUrlIsNull() {
			addCriterion("song_url is null");
			return (Criteria) this;
		}

		public Criteria andSongUrlIsNotNull() {
			addCriterion("song_url is not null");
			return (Criteria) this;
		}

		public Criteria andSongUrlEqualTo(String value) {
			addCriterion("song_url =", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlNotEqualTo(String value) {
			addCriterion("song_url <>", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlGreaterThan(String value) {
			addCriterion("song_url >", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlGreaterThanOrEqualTo(String value) {
			addCriterion("song_url >=", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlLessThan(String value) {
			addCriterion("song_url <", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlLessThanOrEqualTo(String value) {
			addCriterion("song_url <=", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlLike(String value) {
			addCriterion("song_url like", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlNotLike(String value) {
			addCriterion("song_url not like", value, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlIn(List<String> values) {
			addCriterion("song_url in", values, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlNotIn(List<String> values) {
			addCriterion("song_url not in", values, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlBetween(String value1, String value2) {
			addCriterion("song_url between", value1, value2, "songUrl");
			return (Criteria) this;
		}

		public Criteria andSongUrlNotBetween(String value1, String value2) {
			addCriterion("song_url not between", value1, value2, "songUrl");
			return (Criteria) this;
		}

		public Criteria andResourcepathIsNull() {
			addCriterion("resourcepath is null");
			return (Criteria) this;
		}

		public Criteria andResourcepathIsNotNull() {
			addCriterion("resourcepath is not null");
			return (Criteria) this;
		}

		public Criteria andResourcepathEqualTo(String value) {
			addCriterion("resourcepath =", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathNotEqualTo(String value) {
			addCriterion("resourcepath <>", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathGreaterThan(String value) {
			addCriterion("resourcepath >", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathGreaterThanOrEqualTo(String value) {
			addCriterion("resourcepath >=", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathLessThan(String value) {
			addCriterion("resourcepath <", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathLessThanOrEqualTo(String value) {
			addCriterion("resourcepath <=", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathLike(String value) {
			addCriterion("resourcepath like", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathNotLike(String value) {
			addCriterion("resourcepath not like", value, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathIn(List<String> values) {
			addCriterion("resourcepath in", values, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathNotIn(List<String> values) {
			addCriterion("resourcepath not in", values, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathBetween(String value1, String value2) {
			addCriterion("resourcepath between", value1, value2, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andResourcepathNotBetween(String value1, String value2) {
			addCriterion("resourcepath not between", value1, value2, "resourcepath");
			return (Criteria) this;
		}

		public Criteria andSongSheetIsNull() {
			addCriterion("song_sheet is null");
			return (Criteria) this;
		}

		public Criteria andSongSheetIsNotNull() {
			addCriterion("song_sheet is not null");
			return (Criteria) this;
		}

		public Criteria andSongSheetEqualTo(String value) {
			addCriterion("song_sheet =", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetNotEqualTo(String value) {
			addCriterion("song_sheet <>", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetGreaterThan(String value) {
			addCriterion("song_sheet >", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetGreaterThanOrEqualTo(String value) {
			addCriterion("song_sheet >=", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetLessThan(String value) {
			addCriterion("song_sheet <", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetLessThanOrEqualTo(String value) {
			addCriterion("song_sheet <=", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetLike(String value) {
			addCriterion("song_sheet like", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetNotLike(String value) {
			addCriterion("song_sheet not like", value, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetIn(List<String> values) {
			addCriterion("song_sheet in", values, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetNotIn(List<String> values) {
			addCriterion("song_sheet not in", values, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetBetween(String value1, String value2) {
			addCriterion("song_sheet between", value1, value2, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSongSheetNotBetween(String value1, String value2) {
			addCriterion("song_sheet not between", value1, value2, "songSheet");
			return (Criteria) this;
		}

		public Criteria andSortIsNull() {
			addCriterion("sort is null");
			return (Criteria) this;
		}

		public Criteria andSortIsNotNull() {
			addCriterion("sort is not null");
			return (Criteria) this;
		}

		public Criteria andSortEqualTo(Integer value) {
			addCriterion("sort =", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotEqualTo(Integer value) {
			addCriterion("sort <>", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThan(Integer value) {
			addCriterion("sort >", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortGreaterThanOrEqualTo(Integer value) {
			addCriterion("sort >=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThan(Integer value) {
			addCriterion("sort <", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortLessThanOrEqualTo(Integer value) {
			addCriterion("sort <=", value, "sort");
			return (Criteria) this;
		}

		public Criteria andSortIn(List<Integer> values) {
			addCriterion("sort in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotIn(List<Integer> values) {
			addCriterion("sort not in", values, "sort");
			return (Criteria) this;
		}

		public Criteria andSortBetween(Integer value1, Integer value2) {
			addCriterion("sort between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andSortNotBetween(Integer value1, Integer value2) {
			addCriterion("sort not between", value1, value2, "sort");
			return (Criteria) this;
		}

		public Criteria andResourceIsNull() {
			addCriterion("resource is null");
			return (Criteria) this;
		}

		public Criteria andResourceIsNotNull() {
			addCriterion("resource is not null");
			return (Criteria) this;
		}

		public Criteria andResourceEqualTo(String value) {
			addCriterion("resource =", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceNotEqualTo(String value) {
			addCriterion("resource <>", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceGreaterThan(String value) {
			addCriterion("resource >", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceGreaterThanOrEqualTo(String value) {
			addCriterion("resource >=", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceLessThan(String value) {
			addCriterion("resource <", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceLessThanOrEqualTo(String value) {
			addCriterion("resource <=", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceLike(String value) {
			addCriterion("resource like", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceNotLike(String value) {
			addCriterion("resource not like", value, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceIn(List<String> values) {
			addCriterion("resource in", values, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceNotIn(List<String> values) {
			addCriterion("resource not in", values, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceBetween(String value1, String value2) {
			addCriterion("resource between", value1, value2, "resource");
			return (Criteria) this;
		}

		public Criteria andResourceNotBetween(String value1, String value2) {
			addCriterion("resource not between", value1, value2, "resource");
			return (Criteria) this;
		}
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}