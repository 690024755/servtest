package com.galaxyeye.websocket.infrastructure.repository.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssLabExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AssLabExample() {
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

        public Criteria andAssLabCodeIsNull() {
            addCriterion("ass_lab_code is null");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeIsNotNull() {
            addCriterion("ass_lab_code is not null");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeEqualTo(String value) {
            addCriterion("ass_lab_code =", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeNotEqualTo(String value) {
            addCriterion("ass_lab_code <>", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeGreaterThan(String value) {
            addCriterion("ass_lab_code >", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ass_lab_code >=", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeLessThan(String value) {
            addCriterion("ass_lab_code <", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeLessThanOrEqualTo(String value) {
            addCriterion("ass_lab_code <=", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeLike(String value) {
            addCriterion("ass_lab_code like", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeNotLike(String value) {
            addCriterion("ass_lab_code not like", value, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeIn(List<String> values) {
            addCriterion("ass_lab_code in", values, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeNotIn(List<String> values) {
            addCriterion("ass_lab_code not in", values, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeBetween(String value1, String value2) {
            addCriterion("ass_lab_code between", value1, value2, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabCodeNotBetween(String value1, String value2) {
            addCriterion("ass_lab_code not between", value1, value2, "assLabCode");
            return (Criteria) this;
        }

        public Criteria andAssLabNameIsNull() {
            addCriterion("ass_lab_name is null");
            return (Criteria) this;
        }

        public Criteria andAssLabNameIsNotNull() {
            addCriterion("ass_lab_name is not null");
            return (Criteria) this;
        }

        public Criteria andAssLabNameEqualTo(String value) {
            addCriterion("ass_lab_name =", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameNotEqualTo(String value) {
            addCriterion("ass_lab_name <>", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameGreaterThan(String value) {
            addCriterion("ass_lab_name >", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameGreaterThanOrEqualTo(String value) {
            addCriterion("ass_lab_name >=", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameLessThan(String value) {
            addCriterion("ass_lab_name <", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameLessThanOrEqualTo(String value) {
            addCriterion("ass_lab_name <=", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameLike(String value) {
            addCriterion("ass_lab_name like", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameNotLike(String value) {
            addCriterion("ass_lab_name not like", value, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameIn(List<String> values) {
            addCriterion("ass_lab_name in", values, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameNotIn(List<String> values) {
            addCriterion("ass_lab_name not in", values, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameBetween(String value1, String value2) {
            addCriterion("ass_lab_name between", value1, value2, "assLabName");
            return (Criteria) this;
        }

        public Criteria andAssLabNameNotBetween(String value1, String value2) {
            addCriterion("ass_lab_name not between", value1, value2, "assLabName");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeIsNull() {
            addCriterion("main_type_code is null");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeIsNotNull() {
            addCriterion("main_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeEqualTo(String value) {
            addCriterion("main_type_code =", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeNotEqualTo(String value) {
            addCriterion("main_type_code <>", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeGreaterThan(String value) {
            addCriterion("main_type_code >", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("main_type_code >=", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeLessThan(String value) {
            addCriterion("main_type_code <", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("main_type_code <=", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeLike(String value) {
            addCriterion("main_type_code like", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeNotLike(String value) {
            addCriterion("main_type_code not like", value, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeIn(List<String> values) {
            addCriterion("main_type_code in", values, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeNotIn(List<String> values) {
            addCriterion("main_type_code not in", values, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeBetween(String value1, String value2) {
            addCriterion("main_type_code between", value1, value2, "mainTypeCode");
            return (Criteria) this;
        }

        public Criteria andMainTypeCodeNotBetween(String value1, String value2) {
            addCriterion("main_type_code not between", value1, value2, "mainTypeCode");
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