package com.galaxyeye.websocket.infrastructure.repository.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArticleStatisticsExample() {
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

        public Criteria andArticleIdIsNull() {
            addCriterion("article_id is null");
            return (Criteria) this;
        }

        public Criteria andArticleIdIsNotNull() {
            addCriterion("article_id is not null");
            return (Criteria) this;
        }

        public Criteria andArticleIdEqualTo(Long value) {
            addCriterion("article_id =", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotEqualTo(Long value) {
            addCriterion("article_id <>", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThan(Long value) {
            addCriterion("article_id >", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("article_id >=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThan(Long value) {
            addCriterion("article_id <", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdLessThanOrEqualTo(Long value) {
            addCriterion("article_id <=", value, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdIn(List<Long> values) {
            addCriterion("article_id in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotIn(List<Long> values) {
            addCriterion("article_id not in", values, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdBetween(Long value1, Long value2) {
            addCriterion("article_id between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andArticleIdNotBetween(Long value1, Long value2) {
            addCriterion("article_id not between", value1, value2, "articleId");
            return (Criteria) this;
        }

        public Criteria andPushNumIsNull() {
            addCriterion("push_num is null");
            return (Criteria) this;
        }

        public Criteria andPushNumIsNotNull() {
            addCriterion("push_num is not null");
            return (Criteria) this;
        }

        public Criteria andPushNumEqualTo(Integer value) {
            addCriterion("push_num =", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumNotEqualTo(Integer value) {
            addCriterion("push_num <>", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumGreaterThan(Integer value) {
            addCriterion("push_num >", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_num >=", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumLessThan(Integer value) {
            addCriterion("push_num <", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumLessThanOrEqualTo(Integer value) {
            addCriterion("push_num <=", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumIn(List<Integer> values) {
            addCriterion("push_num in", values, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumNotIn(List<Integer> values) {
            addCriterion("push_num not in", values, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumBetween(Integer value1, Integer value2) {
            addCriterion("push_num between", value1, value2, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumNotBetween(Integer value1, Integer value2) {
            addCriterion("push_num not between", value1, value2, "pushNum");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNull() {
            addCriterion("read_num is null");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNotNull() {
            addCriterion("read_num is not null");
            return (Criteria) this;
        }

        public Criteria andReadNumEqualTo(Integer value) {
            addCriterion("read_num =", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotEqualTo(Integer value) {
            addCriterion("read_num <>", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThan(Integer value) {
            addCriterion("read_num >", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("read_num >=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThan(Integer value) {
            addCriterion("read_num <", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThanOrEqualTo(Integer value) {
            addCriterion("read_num <=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumIn(List<Integer> values) {
            addCriterion("read_num in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotIn(List<Integer> values) {
            addCriterion("read_num not in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumBetween(Integer value1, Integer value2) {
            addCriterion("read_num between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("read_num not between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumIsNull() {
            addCriterion("favor_num is null");
            return (Criteria) this;
        }

        public Criteria andFavorNumIsNotNull() {
            addCriterion("favor_num is not null");
            return (Criteria) this;
        }

        public Criteria andFavorNumEqualTo(Integer value) {
            addCriterion("favor_num =", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumNotEqualTo(Integer value) {
            addCriterion("favor_num <>", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumGreaterThan(Integer value) {
            addCriterion("favor_num >", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("favor_num >=", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumLessThan(Integer value) {
            addCriterion("favor_num <", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumLessThanOrEqualTo(Integer value) {
            addCriterion("favor_num <=", value, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumIn(List<Integer> values) {
            addCriterion("favor_num in", values, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumNotIn(List<Integer> values) {
            addCriterion("favor_num not in", values, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumBetween(Integer value1, Integer value2) {
            addCriterion("favor_num between", value1, value2, "favorNum");
            return (Criteria) this;
        }

        public Criteria andFavorNumNotBetween(Integer value1, Integer value2) {
            addCriterion("favor_num not between", value1, value2, "favorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumIsNull() {
            addCriterion("disfavor_num is null");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumIsNotNull() {
            addCriterion("disfavor_num is not null");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumEqualTo(Integer value) {
            addCriterion("disfavor_num =", value, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumNotEqualTo(Integer value) {
            addCriterion("disfavor_num <>", value, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumGreaterThan(Integer value) {
            addCriterion("disfavor_num >", value, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("disfavor_num >=", value, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumLessThan(Integer value) {
            addCriterion("disfavor_num <", value, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumLessThanOrEqualTo(Integer value) {
            addCriterion("disfavor_num <=", value, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumIn(List<Integer> values) {
            addCriterion("disfavor_num in", values, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumNotIn(List<Integer> values) {
            addCriterion("disfavor_num not in", values, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumBetween(Integer value1, Integer value2) {
            addCriterion("disfavor_num between", value1, value2, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andDisfavorNumNotBetween(Integer value1, Integer value2) {
            addCriterion("disfavor_num not between", value1, value2, "disfavorNum");
            return (Criteria) this;
        }

        public Criteria andShareNumIsNull() {
            addCriterion("share_num is null");
            return (Criteria) this;
        }

        public Criteria andShareNumIsNotNull() {
            addCriterion("share_num is not null");
            return (Criteria) this;
        }

        public Criteria andShareNumEqualTo(Integer value) {
            addCriterion("share_num =", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotEqualTo(Integer value) {
            addCriterion("share_num <>", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumGreaterThan(Integer value) {
            addCriterion("share_num >", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_num >=", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumLessThan(Integer value) {
            addCriterion("share_num <", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumLessThanOrEqualTo(Integer value) {
            addCriterion("share_num <=", value, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumIn(List<Integer> values) {
            addCriterion("share_num in", values, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotIn(List<Integer> values) {
            addCriterion("share_num not in", values, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumBetween(Integer value1, Integer value2) {
            addCriterion("share_num between", value1, value2, "shareNum");
            return (Criteria) this;
        }

        public Criteria andShareNumNotBetween(Integer value1, Integer value2) {
            addCriterion("share_num not between", value1, value2, "shareNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumIsNull() {
            addCriterion("share_person_num is null");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumIsNotNull() {
            addCriterion("share_person_num is not null");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumEqualTo(Integer value) {
            addCriterion("share_person_num =", value, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumNotEqualTo(Integer value) {
            addCriterion("share_person_num <>", value, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumGreaterThan(Integer value) {
            addCriterion("share_person_num >", value, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("share_person_num >=", value, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumLessThan(Integer value) {
            addCriterion("share_person_num <", value, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumLessThanOrEqualTo(Integer value) {
            addCriterion("share_person_num <=", value, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumIn(List<Integer> values) {
            addCriterion("share_person_num in", values, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumNotIn(List<Integer> values) {
            addCriterion("share_person_num not in", values, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumBetween(Integer value1, Integer value2) {
            addCriterion("share_person_num between", value1, value2, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andSharePersonNumNotBetween(Integer value1, Integer value2) {
            addCriterion("share_person_num not between", value1, value2, "sharePersonNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumIsNull() {
            addCriterion("collect_num is null");
            return (Criteria) this;
        }

        public Criteria andCollectNumIsNotNull() {
            addCriterion("collect_num is not null");
            return (Criteria) this;
        }

        public Criteria andCollectNumEqualTo(Integer value) {
            addCriterion("collect_num =", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotEqualTo(Integer value) {
            addCriterion("collect_num <>", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumGreaterThan(Integer value) {
            addCriterion("collect_num >", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collect_num >=", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumLessThan(Integer value) {
            addCriterion("collect_num <", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumLessThanOrEqualTo(Integer value) {
            addCriterion("collect_num <=", value, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumIn(List<Integer> values) {
            addCriterion("collect_num in", values, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotIn(List<Integer> values) {
            addCriterion("collect_num not in", values, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumBetween(Integer value1, Integer value2) {
            addCriterion("collect_num between", value1, value2, "collectNum");
            return (Criteria) this;
        }

        public Criteria andCollectNumNotBetween(Integer value1, Integer value2) {
            addCriterion("collect_num not between", value1, value2, "collectNum");
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

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIsNull() {
            addCriterion("deleted_at is null");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIsNotNull() {
            addCriterion("deleted_at is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedAtEqualTo(Date value) {
            addCriterion("deleted_at =", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotEqualTo(Date value) {
            addCriterion("deleted_at <>", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtGreaterThan(Date value) {
            addCriterion("deleted_at >", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("deleted_at >=", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtLessThan(Date value) {
            addCriterion("deleted_at <", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtLessThanOrEqualTo(Date value) {
            addCriterion("deleted_at <=", value, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtIn(List<Date> values) {
            addCriterion("deleted_at in", values, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotIn(List<Date> values) {
            addCriterion("deleted_at not in", values, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtBetween(Date value1, Date value2) {
            addCriterion("deleted_at between", value1, value2, "deletedAt");
            return (Criteria) this;
        }

        public Criteria andDeletedAtNotBetween(Date value1, Date value2) {
            addCriterion("deleted_at not between", value1, value2, "deletedAt");
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