package com.galaxyeye.websocket.infrastructure.repository.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SxcServiceRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SxcServiceRuleExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNull() {
            addCriterion("city_code is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("city_code is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(Integer value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(Integer value) {
            addCriterion("city_code <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(Integer value) {
            addCriterion("city_code >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("city_code >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(Integer value) {
            addCriterion("city_code <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(Integer value) {
            addCriterion("city_code <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<Integer> values) {
            addCriterion("city_code in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<Integer> values) {
            addCriterion("city_code not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(Integer value1, Integer value2) {
            addCriterion("city_code between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("city_code not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdIsNull() {
            addCriterion("pickhouse_id is null");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdIsNotNull() {
            addCriterion("pickhouse_id is not null");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdEqualTo(Integer value) {
            addCriterion("pickhouse_id =", value, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdNotEqualTo(Integer value) {
            addCriterion("pickhouse_id <>", value, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdGreaterThan(Integer value) {
            addCriterion("pickhouse_id >", value, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pickhouse_id >=", value, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdLessThan(Integer value) {
            addCriterion("pickhouse_id <", value, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("pickhouse_id <=", value, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdIn(List<Integer> values) {
            addCriterion("pickhouse_id in", values, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdNotIn(List<Integer> values) {
            addCriterion("pickhouse_id not in", values, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdBetween(Integer value1, Integer value2) {
            addCriterion("pickhouse_id between", value1, value2, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andPickhouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pickhouse_id not between", value1, value2, "pickhouseId");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsIsNull() {
            addCriterion("goods_group_ids is null");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsIsNotNull() {
            addCriterion("goods_group_ids is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsEqualTo(String value) {
            addCriterion("goods_group_ids =", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsNotEqualTo(String value) {
            addCriterion("goods_group_ids <>", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsGreaterThan(String value) {
            addCriterion("goods_group_ids >", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsGreaterThanOrEqualTo(String value) {
            addCriterion("goods_group_ids >=", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsLessThan(String value) {
            addCriterion("goods_group_ids <", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsLessThanOrEqualTo(String value) {
            addCriterion("goods_group_ids <=", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsLike(String value) {
            addCriterion("goods_group_ids like", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsNotLike(String value) {
            addCriterion("goods_group_ids not like", value, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsIn(List<String> values) {
            addCriterion("goods_group_ids in", values, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsNotIn(List<String> values) {
            addCriterion("goods_group_ids not in", values, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsBetween(String value1, String value2) {
            addCriterion("goods_group_ids between", value1, value2, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andGoodsGroupIdsNotBetween(String value1, String value2) {
            addCriterion("goods_group_ids not between", value1, value2, "goodsGroupIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsIsNull() {
            addCriterion("cat_ids is null");
            return (Criteria) this;
        }

        public Criteria andCatIdsIsNotNull() {
            addCriterion("cat_ids is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdsEqualTo(String value) {
            addCriterion("cat_ids =", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotEqualTo(String value) {
            addCriterion("cat_ids <>", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsGreaterThan(String value) {
            addCriterion("cat_ids >", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsGreaterThanOrEqualTo(String value) {
            addCriterion("cat_ids >=", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLessThan(String value) {
            addCriterion("cat_ids <", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLessThanOrEqualTo(String value) {
            addCriterion("cat_ids <=", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLike(String value) {
            addCriterion("cat_ids like", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotLike(String value) {
            addCriterion("cat_ids not like", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsIn(List<String> values) {
            addCriterion("cat_ids in", values, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotIn(List<String> values) {
            addCriterion("cat_ids not in", values, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsBetween(String value1, String value2) {
            addCriterion("cat_ids between", value1, value2, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotBetween(String value1, String value2) {
            addCriterion("cat_ids not between", value1, value2, "catIds");
            return (Criteria) this;
        }

        public Criteria andChargeStandardIsNull() {
            addCriterion("charge_standard is null");
            return (Criteria) this;
        }

        public Criteria andChargeStandardIsNotNull() {
            addCriterion("charge_standard is not null");
            return (Criteria) this;
        }

        public Criteria andChargeStandardEqualTo(String value) {
            addCriterion("charge_standard =", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardNotEqualTo(String value) {
            addCriterion("charge_standard <>", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardGreaterThan(String value) {
            addCriterion("charge_standard >", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardGreaterThanOrEqualTo(String value) {
            addCriterion("charge_standard >=", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardLessThan(String value) {
            addCriterion("charge_standard <", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardLessThanOrEqualTo(String value) {
            addCriterion("charge_standard <=", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardLike(String value) {
            addCriterion("charge_standard like", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardNotLike(String value) {
            addCriterion("charge_standard not like", value, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardIn(List<String> values) {
            addCriterion("charge_standard in", values, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardNotIn(List<String> values) {
            addCriterion("charge_standard not in", values, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardBetween(String value1, String value2) {
            addCriterion("charge_standard between", value1, value2, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andChargeStandardNotBetween(String value1, String value2) {
            addCriterion("charge_standard not between", value1, value2, "chargeStandard");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeIsNull() {
            addCriterion("gmt_start_time is null");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeIsNotNull() {
            addCriterion("gmt_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeEqualTo(Date value) {
            addCriterion("gmt_start_time =", value, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeNotEqualTo(Date value) {
            addCriterion("gmt_start_time <>", value, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeGreaterThan(Date value) {
            addCriterion("gmt_start_time >", value, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_start_time >=", value, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeLessThan(Date value) {
            addCriterion("gmt_start_time <", value, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("gmt_start_time <=", value, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeIn(List<Date> values) {
            addCriterion("gmt_start_time in", values, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeNotIn(List<Date> values) {
            addCriterion("gmt_start_time not in", values, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeBetween(Date value1, Date value2) {
            addCriterion("gmt_start_time between", value1, value2, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("gmt_start_time not between", value1, value2, "gmtStartTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeIsNull() {
            addCriterion("gmt_end_time is null");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeIsNotNull() {
            addCriterion("gmt_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeEqualTo(Date value) {
            addCriterion("gmt_end_time =", value, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeNotEqualTo(Date value) {
            addCriterion("gmt_end_time <>", value, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeGreaterThan(Date value) {
            addCriterion("gmt_end_time >", value, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_end_time >=", value, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeLessThan(Date value) {
            addCriterion("gmt_end_time <", value, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("gmt_end_time <=", value, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeIn(List<Date> values) {
            addCriterion("gmt_end_time in", values, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeNotIn(List<Date> values) {
            addCriterion("gmt_end_time not in", values, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeBetween(Date value1, Date value2) {
            addCriterion("gmt_end_time between", value1, value2, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andGmtEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("gmt_end_time not between", value1, value2, "gmtEndTime");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Boolean value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Boolean value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Boolean value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Boolean value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Boolean value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Boolean value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Boolean> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Boolean> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Boolean value1, Boolean value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Boolean value1, Boolean value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdIsNull() {
            addCriterion("rule_group_id is null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdIsNotNull() {
            addCriterion("rule_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdEqualTo(String value) {
            addCriterion("rule_group_id =", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotEqualTo(String value) {
            addCriterion("rule_group_id <>", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdGreaterThan(String value) {
            addCriterion("rule_group_id >", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("rule_group_id >=", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLessThan(String value) {
            addCriterion("rule_group_id <", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLessThanOrEqualTo(String value) {
            addCriterion("rule_group_id <=", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLike(String value) {
            addCriterion("rule_group_id like", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotLike(String value) {
            addCriterion("rule_group_id not like", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdIn(List<String> values) {
            addCriterion("rule_group_id in", values, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotIn(List<String> values) {
            addCriterion("rule_group_id not in", values, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdBetween(String value1, String value2) {
            addCriterion("rule_group_id between", value1, value2, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotBetween(String value1, String value2) {
            addCriterion("rule_group_id not between", value1, value2, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeIsNull() {
            addCriterion("gmt_create_time is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeIsNotNull() {
            addCriterion("gmt_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeEqualTo(Date value) {
            addCriterion("gmt_create_time =", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeNotEqualTo(Date value) {
            addCriterion("gmt_create_time <>", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeGreaterThan(Date value) {
            addCriterion("gmt_create_time >", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create_time >=", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeLessThan(Date value) {
            addCriterion("gmt_create_time <", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create_time <=", value, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeIn(List<Date> values) {
            addCriterion("gmt_create_time in", values, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeNotIn(List<Date> values) {
            addCriterion("gmt_create_time not in", values, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeBetween(Date value1, Date value2) {
            addCriterion("gmt_create_time between", value1, value2, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create_time not between", value1, value2, "gmtCreateTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeIsNull() {
            addCriterion("gmt_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeIsNotNull() {
            addCriterion("gmt_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeEqualTo(Date value) {
            addCriterion("gmt_modify_time =", value, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeNotEqualTo(Date value) {
            addCriterion("gmt_modify_time <>", value, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeGreaterThan(Date value) {
            addCriterion("gmt_modify_time >", value, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify_time >=", value, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeLessThan(Date value) {
            addCriterion("gmt_modify_time <", value, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify_time <=", value, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeIn(List<Date> values) {
            addCriterion("gmt_modify_time in", values, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeNotIn(List<Date> values) {
            addCriterion("gmt_modify_time not in", values, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeBetween(Date value1, Date value2) {
            addCriterion("gmt_modify_time between", value1, value2, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andGmtModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify_time not between", value1, value2, "gmtModifyTime");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNull() {
            addCriterion("creator_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIsNotNull() {
            addCriterion("creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorIdEqualTo(Integer value) {
            addCriterion("creator_id =", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotEqualTo(Integer value) {
            addCriterion("creator_id <>", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThan(Integer value) {
            addCriterion("creator_id >", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("creator_id >=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThan(Integer value) {
            addCriterion("creator_id <", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("creator_id <=", value, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdIn(List<Integer> values) {
            addCriterion("creator_id in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotIn(List<Integer> values) {
            addCriterion("creator_id not in", values, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdBetween(Integer value1, Integer value2) {
            addCriterion("creator_id between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andCreatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("creator_id not between", value1, value2, "creatorId");
            return (Criteria) this;
        }

        public Criteria andModifierIdIsNull() {
            addCriterion("modifier_id is null");
            return (Criteria) this;
        }

        public Criteria andModifierIdIsNotNull() {
            addCriterion("modifier_id is not null");
            return (Criteria) this;
        }

        public Criteria andModifierIdEqualTo(Integer value) {
            addCriterion("modifier_id =", value, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdNotEqualTo(Integer value) {
            addCriterion("modifier_id <>", value, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdGreaterThan(Integer value) {
            addCriterion("modifier_id >", value, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("modifier_id >=", value, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdLessThan(Integer value) {
            addCriterion("modifier_id <", value, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdLessThanOrEqualTo(Integer value) {
            addCriterion("modifier_id <=", value, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdIn(List<Integer> values) {
            addCriterion("modifier_id in", values, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdNotIn(List<Integer> values) {
            addCriterion("modifier_id not in", values, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdBetween(Integer value1, Integer value2) {
            addCriterion("modifier_id between", value1, value2, "modifierId");
            return (Criteria) this;
        }

        public Criteria andModifierIdNotBetween(Integer value1, Integer value2) {
            addCriterion("modifier_id not between", value1, value2, "modifierId");
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