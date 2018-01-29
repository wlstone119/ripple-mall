package com.stone.ripple.dal.pojo.music;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stone.lava.pojo.LavaDoExample;

public class SongSheetDoExample extends LavaDoExample {
	protected String orderByClause;

	protected boolean distinct;

	protected List<Criteria> oredCriteria;

	public SongSheetDoExample() {
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

		public Criteria andAuthorIsNull() {
			addCriterion("author is null");
			return (Criteria) this;
		}

		public Criteria andAuthorIsNotNull() {
			addCriterion("author is not null");
			return (Criteria) this;
		}

		public Criteria andAuthorEqualTo(String value) {
			addCriterion("author =", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotEqualTo(String value) {
			addCriterion("author <>", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorGreaterThan(String value) {
			addCriterion("author >", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorGreaterThanOrEqualTo(String value) {
			addCriterion("author >=", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLessThan(String value) {
			addCriterion("author <", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLessThanOrEqualTo(String value) {
			addCriterion("author <=", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLike(String value) {
			addCriterion("author like", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotLike(String value) {
			addCriterion("author not like", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorIn(List<String> values) {
			addCriterion("author in", values, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotIn(List<String> values) {
			addCriterion("author not in", values, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorBetween(String value1, String value2) {
			addCriterion("author between", value1, value2, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotBetween(String value1, String value2) {
			addCriterion("author not between", value1, value2, "author");
			return (Criteria) this;
		}

		public Criteria andCoverImgIsNull() {
			addCriterion("cover_img is null");
			return (Criteria) this;
		}

		public Criteria andCoverImgIsNotNull() {
			addCriterion("cover_img is not null");
			return (Criteria) this;
		}

		public Criteria andCoverImgEqualTo(String value) {
			addCriterion("cover_img =", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgNotEqualTo(String value) {
			addCriterion("cover_img <>", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgGreaterThan(String value) {
			addCriterion("cover_img >", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgGreaterThanOrEqualTo(String value) {
			addCriterion("cover_img >=", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgLessThan(String value) {
			addCriterion("cover_img <", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgLessThanOrEqualTo(String value) {
			addCriterion("cover_img <=", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgLike(String value) {
			addCriterion("cover_img like", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgNotLike(String value) {
			addCriterion("cover_img not like", value, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgIn(List<String> values) {
			addCriterion("cover_img in", values, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgNotIn(List<String> values) {
			addCriterion("cover_img not in", values, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgBetween(String value1, String value2) {
			addCriterion("cover_img between", value1, value2, "coverImg");
			return (Criteria) this;
		}

		public Criteria andCoverImgNotBetween(String value1, String value2) {
			addCriterion("cover_img not between", value1, value2, "coverImg");
			return (Criteria) this;
		}

		public Criteria andIntroductionIsNull() {
			addCriterion("introduction is null");
			return (Criteria) this;
		}

		public Criteria andIntroductionIsNotNull() {
			addCriterion("introduction is not null");
			return (Criteria) this;
		}

		public Criteria andIntroductionEqualTo(String value) {
			addCriterion("introduction =", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotEqualTo(String value) {
			addCriterion("introduction <>", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionGreaterThan(String value) {
			addCriterion("introduction >", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionGreaterThanOrEqualTo(String value) {
			addCriterion("introduction >=", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionLessThan(String value) {
			addCriterion("introduction <", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionLessThanOrEqualTo(String value) {
			addCriterion("introduction <=", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionLike(String value) {
			addCriterion("introduction like", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotLike(String value) {
			addCriterion("introduction not like", value, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionIn(List<String> values) {
			addCriterion("introduction in", values, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotIn(List<String> values) {
			addCriterion("introduction not in", values, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionBetween(String value1, String value2) {
			addCriterion("introduction between", value1, value2, "introduction");
			return (Criteria) this;
		}

		public Criteria andIntroductionNotBetween(String value1, String value2) {
			addCriterion("introduction not between", value1, value2, "introduction");
			return (Criteria) this;
		}

		public Criteria andPlayCountIsNull() {
			addCriterion("play_count is null");
			return (Criteria) this;
		}

		public Criteria andPlayCountIsNotNull() {
			addCriterion("play_count is not null");
			return (Criteria) this;
		}

		public Criteria andPlayCountEqualTo(Integer value) {
			addCriterion("play_count =", value, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountNotEqualTo(Integer value) {
			addCriterion("play_count <>", value, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountGreaterThan(Integer value) {
			addCriterion("play_count >", value, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("play_count >=", value, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountLessThan(Integer value) {
			addCriterion("play_count <", value, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountLessThanOrEqualTo(Integer value) {
			addCriterion("play_count <=", value, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountIn(List<Integer> values) {
			addCriterion("play_count in", values, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountNotIn(List<Integer> values) {
			addCriterion("play_count not in", values, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountBetween(Integer value1, Integer value2) {
			addCriterion("play_count between", value1, value2, "playCount");
			return (Criteria) this;
		}

		public Criteria andPlayCountNotBetween(Integer value1, Integer value2) {
			addCriterion("play_count not between", value1, value2, "playCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountIsNull() {
			addCriterion("collect_count is null");
			return (Criteria) this;
		}

		public Criteria andCollectCountIsNotNull() {
			addCriterion("collect_count is not null");
			return (Criteria) this;
		}

		public Criteria andCollectCountEqualTo(Integer value) {
			addCriterion("collect_count =", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountNotEqualTo(Integer value) {
			addCriterion("collect_count <>", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountGreaterThan(Integer value) {
			addCriterion("collect_count >", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("collect_count >=", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountLessThan(Integer value) {
			addCriterion("collect_count <", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountLessThanOrEqualTo(Integer value) {
			addCriterion("collect_count <=", value, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountIn(List<Integer> values) {
			addCriterion("collect_count in", values, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountNotIn(List<Integer> values) {
			addCriterion("collect_count not in", values, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountBetween(Integer value1, Integer value2) {
			addCriterion("collect_count between", value1, value2, "collectCount");
			return (Criteria) this;
		}

		public Criteria andCollectCountNotBetween(Integer value1, Integer value2) {
			addCriterion("collect_count not between", value1, value2, "collectCount");
			return (Criteria) this;
		}

		public Criteria andShareCountIsNull() {
			addCriterion("share_count is null");
			return (Criteria) this;
		}

		public Criteria andShareCountIsNotNull() {
			addCriterion("share_count is not null");
			return (Criteria) this;
		}

		public Criteria andShareCountEqualTo(Integer value) {
			addCriterion("share_count =", value, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountNotEqualTo(Integer value) {
			addCriterion("share_count <>", value, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountGreaterThan(Integer value) {
			addCriterion("share_count >", value, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("share_count >=", value, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountLessThan(Integer value) {
			addCriterion("share_count <", value, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountLessThanOrEqualTo(Integer value) {
			addCriterion("share_count <=", value, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountIn(List<Integer> values) {
			addCriterion("share_count in", values, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountNotIn(List<Integer> values) {
			addCriterion("share_count not in", values, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountBetween(Integer value1, Integer value2) {
			addCriterion("share_count between", value1, value2, "shareCount");
			return (Criteria) this;
		}

		public Criteria andShareCountNotBetween(Integer value1, Integer value2) {
			addCriterion("share_count not between", value1, value2, "shareCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountIsNull() {
			addCriterion("comment_count is null");
			return (Criteria) this;
		}

		public Criteria andCommentCountIsNotNull() {
			addCriterion("comment_count is not null");
			return (Criteria) this;
		}

		public Criteria andCommentCountEqualTo(Integer value) {
			addCriterion("comment_count =", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotEqualTo(Integer value) {
			addCriterion("comment_count <>", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountGreaterThan(Integer value) {
			addCriterion("comment_count >", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("comment_count >=", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountLessThan(Integer value) {
			addCriterion("comment_count <", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountLessThanOrEqualTo(Integer value) {
			addCriterion("comment_count <=", value, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountIn(List<Integer> values) {
			addCriterion("comment_count in", values, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotIn(List<Integer> values) {
			addCriterion("comment_count not in", values, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountBetween(Integer value1, Integer value2) {
			addCriterion("comment_count between", value1, value2, "commentCount");
			return (Criteria) this;
		}

		public Criteria andCommentCountNotBetween(Integer value1, Integer value2) {
			addCriterion("comment_count not between", value1, value2, "commentCount");
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