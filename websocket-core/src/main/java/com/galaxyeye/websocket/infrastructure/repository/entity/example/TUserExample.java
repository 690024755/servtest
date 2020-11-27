package com.galaxyeye.websocket.infrastructure.repository.entity.example;

import java.util.ArrayList;
import java.util.List;

public class TUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TUserExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUnameIsNull() {
            addCriterion("uname is null");
            return (Criteria) this;
        }

        public Criteria andUnameIsNotNull() {
            addCriterion("uname is not null");
            return (Criteria) this;
        }

        public Criteria andUnameEqualTo(String value) {
            addCriterion("uname =", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotEqualTo(String value) {
            addCriterion("uname <>", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThan(String value) {
            addCriterion("uname >", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameGreaterThanOrEqualTo(String value) {
            addCriterion("uname >=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThan(String value) {
            addCriterion("uname <", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLessThanOrEqualTo(String value) {
            addCriterion("uname <=", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameLike(String value) {
            addCriterion("uname like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotLike(String value) {
            addCriterion("uname not like", value, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameIn(List<String> values) {
            addCriterion("uname in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotIn(List<String> values) {
            addCriterion("uname not in", values, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameBetween(String value1, String value2) {
            addCriterion("uname between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andUnameNotBetween(String value1, String value2) {
            addCriterion("uname not between", value1, value2, "uname");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andChannelnoIsNull() {
            addCriterion("channelNo is null");
            return (Criteria) this;
        }

        public Criteria andChannelnoIsNotNull() {
            addCriterion("channelNo is not null");
            return (Criteria) this;
        }

        public Criteria andChannelnoEqualTo(String value) {
            addCriterion("channelNo =", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoNotEqualTo(String value) {
            addCriterion("channelNo <>", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoGreaterThan(String value) {
            addCriterion("channelNo >", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoGreaterThanOrEqualTo(String value) {
            addCriterion("channelNo >=", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoLessThan(String value) {
            addCriterion("channelNo <", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoLessThanOrEqualTo(String value) {
            addCriterion("channelNo <=", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoLike(String value) {
            addCriterion("channelNo like", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoNotLike(String value) {
            addCriterion("channelNo not like", value, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoIn(List<String> values) {
            addCriterion("channelNo in", values, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoNotIn(List<String> values) {
            addCriterion("channelNo not in", values, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoBetween(String value1, String value2) {
            addCriterion("channelNo between", value1, value2, "channelno");
            return (Criteria) this;
        }

        public Criteria andChannelnoNotBetween(String value1, String value2) {
            addCriterion("channelNo not between", value1, value2, "channelno");
            return (Criteria) this;
        }

        public Criteria andActivitynoIsNull() {
            addCriterion("activityNo is null");
            return (Criteria) this;
        }

        public Criteria andActivitynoIsNotNull() {
            addCriterion("activityNo is not null");
            return (Criteria) this;
        }

        public Criteria andActivitynoEqualTo(String value) {
            addCriterion("activityNo =", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoNotEqualTo(String value) {
            addCriterion("activityNo <>", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoGreaterThan(String value) {
            addCriterion("activityNo >", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoGreaterThanOrEqualTo(String value) {
            addCriterion("activityNo >=", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoLessThan(String value) {
            addCriterion("activityNo <", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoLessThanOrEqualTo(String value) {
            addCriterion("activityNo <=", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoLike(String value) {
            addCriterion("activityNo like", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoNotLike(String value) {
            addCriterion("activityNo not like", value, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoIn(List<String> values) {
            addCriterion("activityNo in", values, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoNotIn(List<String> values) {
            addCriterion("activityNo not in", values, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoBetween(String value1, String value2) {
            addCriterion("activityNo between", value1, value2, "activityno");
            return (Criteria) this;
        }

        public Criteria andActivitynoNotBetween(String value1, String value2) {
            addCriterion("activityNo not between", value1, value2, "activityno");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Long value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Long value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Long value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Long value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Long value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Long> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Long> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Long value1, Long value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Long value1, Long value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNull() {
            addCriterion("modifyTime is null");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNotNull() {
            addCriterion("modifyTime is not null");
            return (Criteria) this;
        }

        public Criteria andModifytimeEqualTo(Long value) {
            addCriterion("modifyTime =", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotEqualTo(Long value) {
            addCriterion("modifyTime <>", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThan(Long value) {
            addCriterion("modifyTime >", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThanOrEqualTo(Long value) {
            addCriterion("modifyTime >=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThan(Long value) {
            addCriterion("modifyTime <", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThanOrEqualTo(Long value) {
            addCriterion("modifyTime <=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIn(List<Long> values) {
            addCriterion("modifyTime in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotIn(List<Long> values) {
            addCriterion("modifyTime not in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeBetween(Long value1, Long value2) {
            addCriterion("modifyTime between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotBetween(Long value1, Long value2) {
            addCriterion("modifyTime not between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andBlockedIsNull() {
            addCriterion("blocked is null");
            return (Criteria) this;
        }

        public Criteria andBlockedIsNotNull() {
            addCriterion("blocked is not null");
            return (Criteria) this;
        }

        public Criteria andBlockedEqualTo(Integer value) {
            addCriterion("blocked =", value, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedNotEqualTo(Integer value) {
            addCriterion("blocked <>", value, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedGreaterThan(Integer value) {
            addCriterion("blocked >", value, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedGreaterThanOrEqualTo(Integer value) {
            addCriterion("blocked >=", value, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedLessThan(Integer value) {
            addCriterion("blocked <", value, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedLessThanOrEqualTo(Integer value) {
            addCriterion("blocked <=", value, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedIn(List<Integer> values) {
            addCriterion("blocked in", values, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedNotIn(List<Integer> values) {
            addCriterion("blocked not in", values, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedBetween(Integer value1, Integer value2) {
            addCriterion("blocked between", value1, value2, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedNotBetween(Integer value1, Integer value2) {
            addCriterion("blocked not between", value1, value2, "blocked");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutIsNull() {
            addCriterion("blockedTimeout is null");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutIsNotNull() {
            addCriterion("blockedTimeout is not null");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutEqualTo(Long value) {
            addCriterion("blockedTimeout =", value, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutNotEqualTo(Long value) {
            addCriterion("blockedTimeout <>", value, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutGreaterThan(Long value) {
            addCriterion("blockedTimeout >", value, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutGreaterThanOrEqualTo(Long value) {
            addCriterion("blockedTimeout >=", value, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutLessThan(Long value) {
            addCriterion("blockedTimeout <", value, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutLessThanOrEqualTo(Long value) {
            addCriterion("blockedTimeout <=", value, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutIn(List<Long> values) {
            addCriterion("blockedTimeout in", values, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutNotIn(List<Long> values) {
            addCriterion("blockedTimeout not in", values, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutBetween(Long value1, Long value2) {
            addCriterion("blockedTimeout between", value1, value2, "blockedtimeout");
            return (Criteria) this;
        }

        public Criteria andBlockedtimeoutNotBetween(Long value1, Long value2) {
            addCriterion("blockedTimeout not between", value1, value2, "blockedtimeout");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("appid is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appid is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appid =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appid <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appid >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appid >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appid <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appid <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appid like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appid not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appid in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appid not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appid between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appid not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andUcIsNull() {
            addCriterion("uc is null");
            return (Criteria) this;
        }

        public Criteria andUcIsNotNull() {
            addCriterion("uc is not null");
            return (Criteria) this;
        }

        public Criteria andUcEqualTo(Long value) {
            addCriterion("uc =", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotEqualTo(Long value) {
            addCriterion("uc <>", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThan(Long value) {
            addCriterion("uc >", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcGreaterThanOrEqualTo(Long value) {
            addCriterion("uc >=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThan(Long value) {
            addCriterion("uc <", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcLessThanOrEqualTo(Long value) {
            addCriterion("uc <=", value, "uc");
            return (Criteria) this;
        }

        public Criteria andUcIn(List<Long> values) {
            addCriterion("uc in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotIn(List<Long> values) {
            addCriterion("uc not in", values, "uc");
            return (Criteria) this;
        }

        public Criteria andUcBetween(Long value1, Long value2) {
            addCriterion("uc between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andUcNotBetween(Long value1, Long value2) {
            addCriterion("uc not between", value1, value2, "uc");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIsNull() {
            addCriterion("lastLoginTime is null");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIsNotNull() {
            addCriterion("lastLoginTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeEqualTo(Long value) {
            addCriterion("lastLoginTime =", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotEqualTo(Long value) {
            addCriterion("lastLoginTime <>", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeGreaterThan(Long value) {
            addCriterion("lastLoginTime >", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeGreaterThanOrEqualTo(Long value) {
            addCriterion("lastLoginTime >=", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeLessThan(Long value) {
            addCriterion("lastLoginTime <", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeLessThanOrEqualTo(Long value) {
            addCriterion("lastLoginTime <=", value, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeIn(List<Long> values) {
            addCriterion("lastLoginTime in", values, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotIn(List<Long> values) {
            addCriterion("lastLoginTime not in", values, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeBetween(Long value1, Long value2) {
            addCriterion("lastLoginTime between", value1, value2, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogintimeNotBetween(Long value1, Long value2) {
            addCriterion("lastLoginTime not between", value1, value2, "lastlogintime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeIsNull() {
            addCriterion("lastLogoutTime is null");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeIsNotNull() {
            addCriterion("lastLogoutTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeEqualTo(Long value) {
            addCriterion("lastLogoutTime =", value, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeNotEqualTo(Long value) {
            addCriterion("lastLogoutTime <>", value, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeGreaterThan(Long value) {
            addCriterion("lastLogoutTime >", value, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeGreaterThanOrEqualTo(Long value) {
            addCriterion("lastLogoutTime >=", value, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeLessThan(Long value) {
            addCriterion("lastLogoutTime <", value, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeLessThanOrEqualTo(Long value) {
            addCriterion("lastLogoutTime <=", value, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeIn(List<Long> values) {
            addCriterion("lastLogoutTime in", values, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeNotIn(List<Long> values) {
            addCriterion("lastLogoutTime not in", values, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeBetween(Long value1, Long value2) {
            addCriterion("lastLogoutTime between", value1, value2, "lastlogouttime");
            return (Criteria) this;
        }

        public Criteria andLastlogouttimeNotBetween(Long value1, Long value2) {
            addCriterion("lastLogoutTime not between", value1, value2, "lastlogouttime");
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