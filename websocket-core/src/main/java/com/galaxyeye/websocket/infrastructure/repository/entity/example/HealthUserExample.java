package com.galaxyeye.websocket.infrastructure.repository.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HealthUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HealthUserExample() {
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

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(Integer value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(Integer value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(Integer value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(Integer value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(Integer value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(Integer value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<Integer> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<Integer> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(Integer value1, Integer value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(Integer value1, Integer value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andRightIsNull() {
            addCriterion("right is null");
            return (Criteria) this;
        }

        public Criteria andRightIsNotNull() {
            addCriterion("right is not null");
            return (Criteria) this;
        }

        public Criteria andRightEqualTo(Integer value) {
            addCriterion("right =", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightNotEqualTo(Integer value) {
            addCriterion("right <>", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightGreaterThan(Integer value) {
            addCriterion("right >", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightGreaterThanOrEqualTo(Integer value) {
            addCriterion("right >=", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightLessThan(Integer value) {
            addCriterion("right <", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightLessThanOrEqualTo(Integer value) {
            addCriterion("right <=", value, "right");
            return (Criteria) this;
        }

        public Criteria andRightIn(List<Integer> values) {
            addCriterion("right in", values, "right");
            return (Criteria) this;
        }

        public Criteria andRightNotIn(List<Integer> values) {
            addCriterion("right not in", values, "right");
            return (Criteria) this;
        }

        public Criteria andRightBetween(Integer value1, Integer value2) {
            addCriterion("right between", value1, value2, "right");
            return (Criteria) this;
        }

        public Criteria andRightNotBetween(Integer value1, Integer value2) {
            addCriterion("right not between", value1, value2, "right");
            return (Criteria) this;
        }

        public Criteria andSignDaysIsNull() {
            addCriterion("sign_days is null");
            return (Criteria) this;
        }

        public Criteria andSignDaysIsNotNull() {
            addCriterion("sign_days is not null");
            return (Criteria) this;
        }

        public Criteria andSignDaysEqualTo(Integer value) {
            addCriterion("sign_days =", value, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysNotEqualTo(Integer value) {
            addCriterion("sign_days <>", value, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysGreaterThan(Integer value) {
            addCriterion("sign_days >", value, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("sign_days >=", value, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysLessThan(Integer value) {
            addCriterion("sign_days <", value, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysLessThanOrEqualTo(Integer value) {
            addCriterion("sign_days <=", value, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysIn(List<Integer> values) {
            addCriterion("sign_days in", values, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysNotIn(List<Integer> values) {
            addCriterion("sign_days not in", values, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysBetween(Integer value1, Integer value2) {
            addCriterion("sign_days between", value1, value2, "signDays");
            return (Criteria) this;
        }

        public Criteria andSignDaysNotBetween(Integer value1, Integer value2) {
            addCriterion("sign_days not between", value1, value2, "signDays");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerIsNull() {
            addCriterion("today_answer is null");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerIsNotNull() {
            addCriterion("today_answer is not null");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerEqualTo(Short value) {
            addCriterion("today_answer =", value, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerNotEqualTo(Short value) {
            addCriterion("today_answer <>", value, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerGreaterThan(Short value) {
            addCriterion("today_answer >", value, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerGreaterThanOrEqualTo(Short value) {
            addCriterion("today_answer >=", value, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerLessThan(Short value) {
            addCriterion("today_answer <", value, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerLessThanOrEqualTo(Short value) {
            addCriterion("today_answer <=", value, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerIn(List<Short> values) {
            addCriterion("today_answer in", values, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerNotIn(List<Short> values) {
            addCriterion("today_answer not in", values, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerBetween(Short value1, Short value2) {
            addCriterion("today_answer between", value1, value2, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayAnswerNotBetween(Short value1, Short value2) {
            addCriterion("today_answer not between", value1, value2, "todayAnswer");
            return (Criteria) this;
        }

        public Criteria andTodayRightIsNull() {
            addCriterion("today_right is null");
            return (Criteria) this;
        }

        public Criteria andTodayRightIsNotNull() {
            addCriterion("today_right is not null");
            return (Criteria) this;
        }

        public Criteria andTodayRightEqualTo(Short value) {
            addCriterion("today_right =", value, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightNotEqualTo(Short value) {
            addCriterion("today_right <>", value, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightGreaterThan(Short value) {
            addCriterion("today_right >", value, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightGreaterThanOrEqualTo(Short value) {
            addCriterion("today_right >=", value, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightLessThan(Short value) {
            addCriterion("today_right <", value, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightLessThanOrEqualTo(Short value) {
            addCriterion("today_right <=", value, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightIn(List<Short> values) {
            addCriterion("today_right in", values, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightNotIn(List<Short> values) {
            addCriterion("today_right not in", values, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightBetween(Short value1, Short value2) {
            addCriterion("today_right between", value1, value2, "todayRight");
            return (Criteria) this;
        }

        public Criteria andTodayRightNotBetween(Short value1, Short value2) {
            addCriterion("today_right not between", value1, value2, "todayRight");
            return (Criteria) this;
        }

        public Criteria andAnswerAtIsNull() {
            addCriterion("answer_at is null");
            return (Criteria) this;
        }

        public Criteria andAnswerAtIsNotNull() {
            addCriterion("answer_at is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerAtEqualTo(Date value) {
            addCriterion("answer_at =", value, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtNotEqualTo(Date value) {
            addCriterion("answer_at <>", value, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtGreaterThan(Date value) {
            addCriterion("answer_at >", value, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtGreaterThanOrEqualTo(Date value) {
            addCriterion("answer_at >=", value, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtLessThan(Date value) {
            addCriterion("answer_at <", value, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtLessThanOrEqualTo(Date value) {
            addCriterion("answer_at <=", value, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtIn(List<Date> values) {
            addCriterion("answer_at in", values, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtNotIn(List<Date> values) {
            addCriterion("answer_at not in", values, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtBetween(Date value1, Date value2) {
            addCriterion("answer_at between", value1, value2, "answerAt");
            return (Criteria) this;
        }

        public Criteria andAnswerAtNotBetween(Date value1, Date value2) {
            addCriterion("answer_at not between", value1, value2, "answerAt");
            return (Criteria) this;
        }

        public Criteria andSignAtIsNull() {
            addCriterion("sign_at is null");
            return (Criteria) this;
        }

        public Criteria andSignAtIsNotNull() {
            addCriterion("sign_at is not null");
            return (Criteria) this;
        }

        public Criteria andSignAtEqualTo(Date value) {
            addCriterion("sign_at =", value, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtNotEqualTo(Date value) {
            addCriterion("sign_at <>", value, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtGreaterThan(Date value) {
            addCriterion("sign_at >", value, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtGreaterThanOrEqualTo(Date value) {
            addCriterion("sign_at >=", value, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtLessThan(Date value) {
            addCriterion("sign_at <", value, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtLessThanOrEqualTo(Date value) {
            addCriterion("sign_at <=", value, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtIn(List<Date> values) {
            addCriterion("sign_at in", values, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtNotIn(List<Date> values) {
            addCriterion("sign_at not in", values, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtBetween(Date value1, Date value2) {
            addCriterion("sign_at between", value1, value2, "signAt");
            return (Criteria) this;
        }

        public Criteria andSignAtNotBetween(Date value1, Date value2) {
            addCriterion("sign_at not between", value1, value2, "signAt");
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

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
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