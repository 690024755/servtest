package com.galaxyeye.websocket.infrastructure.repository.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SxcFixPriceBaseInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SxcFixPriceBaseInfoExample() {
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

        public Criteria andItemIdIsNull() {
            addCriterion("item_id is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("item_id is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(Integer value) {
            addCriterion("item_id =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(Integer value) {
            addCriterion("item_id <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(Integer value) {
            addCriterion("item_id >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("item_id >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(Integer value) {
            addCriterion("item_id <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("item_id <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<Integer> values) {
            addCriterion("item_id in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<Integer> values) {
            addCriterion("item_id not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(Integer value1, Integer value2) {
            addCriterion("item_id between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("item_id not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNull() {
            addCriterion("sku_id is null");
            return (Criteria) this;
        }

        public Criteria andSkuIdIsNotNull() {
            addCriterion("sku_id is not null");
            return (Criteria) this;
        }

        public Criteria andSkuIdEqualTo(Integer value) {
            addCriterion("sku_id =", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotEqualTo(Integer value) {
            addCriterion("sku_id <>", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThan(Integer value) {
            addCriterion("sku_id >", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sku_id >=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThan(Integer value) {
            addCriterion("sku_id <", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdLessThanOrEqualTo(Integer value) {
            addCriterion("sku_id <=", value, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdIn(List<Integer> values) {
            addCriterion("sku_id in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotIn(List<Integer> values) {
            addCriterion("sku_id not in", values, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdBetween(Integer value1, Integer value2) {
            addCriterion("sku_id between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSkuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sku_id not between", value1, value2, "skuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdIsNull() {
            addCriterion("spu_id is null");
            return (Criteria) this;
        }

        public Criteria andSpuIdIsNotNull() {
            addCriterion("spu_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpuIdEqualTo(Integer value) {
            addCriterion("spu_id =", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotEqualTo(Integer value) {
            addCriterion("spu_id <>", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdGreaterThan(Integer value) {
            addCriterion("spu_id >", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spu_id >=", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdLessThan(Integer value) {
            addCriterion("spu_id <", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdLessThanOrEqualTo(Integer value) {
            addCriterion("spu_id <=", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdIn(List<Integer> values) {
            addCriterion("spu_id in", values, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotIn(List<Integer> values) {
            addCriterion("spu_id not in", values, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdBetween(Integer value1, Integer value2) {
            addCriterion("spu_id between", value1, value2, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spu_id not between", value1, value2, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuNameIsNull() {
            addCriterion("spu_name is null");
            return (Criteria) this;
        }

        public Criteria andSpuNameIsNotNull() {
            addCriterion("spu_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpuNameEqualTo(String value) {
            addCriterion("spu_name =", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameNotEqualTo(String value) {
            addCriterion("spu_name <>", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameGreaterThan(String value) {
            addCriterion("spu_name >", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameGreaterThanOrEqualTo(String value) {
            addCriterion("spu_name >=", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameLessThan(String value) {
            addCriterion("spu_name <", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameLessThanOrEqualTo(String value) {
            addCriterion("spu_name <=", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameLike(String value) {
            addCriterion("spu_name like", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameNotLike(String value) {
            addCriterion("spu_name not like", value, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameIn(List<String> values) {
            addCriterion("spu_name in", values, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameNotIn(List<String> values) {
            addCriterion("spu_name not in", values, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameBetween(String value1, String value2) {
            addCriterion("spu_name between", value1, value2, "spuName");
            return (Criteria) this;
        }

        public Criteria andSpuNameNotBetween(String value1, String value2) {
            addCriterion("spu_name not between", value1, value2, "spuName");
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

        public Criteria andPurchasePriceIsNull() {
            addCriterion("purchase_price is null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIsNotNull() {
            addCriterion("purchase_price is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceEqualTo(Long value) {
            addCriterion("purchase_price =", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotEqualTo(Long value) {
            addCriterion("purchase_price <>", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThan(Long value) {
            addCriterion("purchase_price >", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceGreaterThanOrEqualTo(Long value) {
            addCriterion("purchase_price >=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThan(Long value) {
            addCriterion("purchase_price <", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceLessThanOrEqualTo(Long value) {
            addCriterion("purchase_price <=", value, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceIn(List<Long> values) {
            addCriterion("purchase_price in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotIn(List<Long> values) {
            addCriterion("purchase_price not in", values, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceBetween(Long value1, Long value2) {
            addCriterion("purchase_price between", value1, value2, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchasePriceNotBetween(Long value1, Long value2) {
            addCriterion("purchase_price not between", value1, value2, "purchasePrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeIsNull() {
            addCriterion("purchase_time is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeIsNotNull() {
            addCriterion("purchase_time is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeEqualTo(Date value) {
            addCriterion("purchase_time =", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeNotEqualTo(Date value) {
            addCriterion("purchase_time <>", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeGreaterThan(Date value) {
            addCriterion("purchase_time >", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("purchase_time >=", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeLessThan(Date value) {
            addCriterion("purchase_time <", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("purchase_time <=", value, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeIn(List<Date> values) {
            addCriterion("purchase_time in", values, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeNotIn(List<Date> values) {
            addCriterion("purchase_time not in", values, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeBetween(Date value1, Date value2) {
            addCriterion("purchase_time between", value1, value2, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("purchase_time not between", value1, value2, "purchaseTime");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdIsNull() {
            addCriterion("purchaser_id is null");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdIsNotNull() {
            addCriterion("purchaser_id is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdEqualTo(Integer value) {
            addCriterion("purchaser_id =", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdNotEqualTo(Integer value) {
            addCriterion("purchaser_id <>", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdGreaterThan(Integer value) {
            addCriterion("purchaser_id >", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchaser_id >=", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdLessThan(Integer value) {
            addCriterion("purchaser_id <", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdLessThanOrEqualTo(Integer value) {
            addCriterion("purchaser_id <=", value, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdIn(List<Integer> values) {
            addCriterion("purchaser_id in", values, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdNotIn(List<Integer> values) {
            addCriterion("purchaser_id not in", values, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdBetween(Integer value1, Integer value2) {
            addCriterion("purchaser_id between", value1, value2, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("purchaser_id not between", value1, value2, "purchaserId");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameIsNull() {
            addCriterion("purchaser_name is null");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameIsNotNull() {
            addCriterion("purchaser_name is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameEqualTo(String value) {
            addCriterion("purchaser_name =", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameNotEqualTo(String value) {
            addCriterion("purchaser_name <>", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameGreaterThan(String value) {
            addCriterion("purchaser_name >", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameGreaterThanOrEqualTo(String value) {
            addCriterion("purchaser_name >=", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameLessThan(String value) {
            addCriterion("purchaser_name <", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameLessThanOrEqualTo(String value) {
            addCriterion("purchaser_name <=", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameLike(String value) {
            addCriterion("purchaser_name like", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameNotLike(String value) {
            addCriterion("purchaser_name not like", value, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameIn(List<String> values) {
            addCriterion("purchaser_name in", values, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameNotIn(List<String> values) {
            addCriterion("purchaser_name not in", values, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameBetween(String value1, String value2) {
            addCriterion("purchaser_name between", value1, value2, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserNameNotBetween(String value1, String value2) {
            addCriterion("purchaser_name not between", value1, value2, "purchaserName");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneIsNull() {
            addCriterion("purchaser_mobile_phone is null");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneIsNotNull() {
            addCriterion("purchaser_mobile_phone is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneEqualTo(String value) {
            addCriterion("purchaser_mobile_phone =", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneNotEqualTo(String value) {
            addCriterion("purchaser_mobile_phone <>", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneGreaterThan(String value) {
            addCriterion("purchaser_mobile_phone >", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("purchaser_mobile_phone >=", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneLessThan(String value) {
            addCriterion("purchaser_mobile_phone <", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("purchaser_mobile_phone <=", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneLike(String value) {
            addCriterion("purchaser_mobile_phone like", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneNotLike(String value) {
            addCriterion("purchaser_mobile_phone not like", value, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneIn(List<String> values) {
            addCriterion("purchaser_mobile_phone in", values, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneNotIn(List<String> values) {
            addCriterion("purchaser_mobile_phone not in", values, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneBetween(String value1, String value2) {
            addCriterion("purchaser_mobile_phone between", value1, value2, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaserMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("purchaser_mobile_phone not between", value1, value2, "purchaserMobilePhone");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceIsNull() {
            addCriterion("purchase_inquiry_price is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceIsNotNull() {
            addCriterion("purchase_inquiry_price is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceEqualTo(Long value) {
            addCriterion("purchase_inquiry_price =", value, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceNotEqualTo(Long value) {
            addCriterion("purchase_inquiry_price <>", value, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceGreaterThan(Long value) {
            addCriterion("purchase_inquiry_price >", value, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("purchase_inquiry_price >=", value, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceLessThan(Long value) {
            addCriterion("purchase_inquiry_price <", value, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceLessThanOrEqualTo(Long value) {
            addCriterion("purchase_inquiry_price <=", value, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceIn(List<Long> values) {
            addCriterion("purchase_inquiry_price in", values, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceNotIn(List<Long> values) {
            addCriterion("purchase_inquiry_price not in", values, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceBetween(Long value1, Long value2) {
            addCriterion("purchase_inquiry_price between", value1, value2, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andPurchaseInquiryPriceNotBetween(Long value1, Long value2) {
            addCriterion("purchase_inquiry_price not between", value1, value2, "purchaseInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceIsNull() {
            addCriterion("market_inquiry_price is null");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceIsNotNull() {
            addCriterion("market_inquiry_price is not null");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceEqualTo(Long value) {
            addCriterion("market_inquiry_price =", value, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceNotEqualTo(Long value) {
            addCriterion("market_inquiry_price <>", value, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceGreaterThan(Long value) {
            addCriterion("market_inquiry_price >", value, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("market_inquiry_price >=", value, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceLessThan(Long value) {
            addCriterion("market_inquiry_price <", value, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceLessThanOrEqualTo(Long value) {
            addCriterion("market_inquiry_price <=", value, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceIn(List<Long> values) {
            addCriterion("market_inquiry_price in", values, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceNotIn(List<Long> values) {
            addCriterion("market_inquiry_price not in", values, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceBetween(Long value1, Long value2) {
            addCriterion("market_inquiry_price between", value1, value2, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceNotBetween(Long value1, Long value2) {
            addCriterion("market_inquiry_price not between", value1, value2, "marketInquiryPrice");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeIsNull() {
            addCriterion("inquiry_time is null");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeIsNotNull() {
            addCriterion("inquiry_time is not null");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeEqualTo(Date value) {
            addCriterion("inquiry_time =", value, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeNotEqualTo(Date value) {
            addCriterion("inquiry_time <>", value, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeGreaterThan(Date value) {
            addCriterion("inquiry_time >", value, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("inquiry_time >=", value, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeLessThan(Date value) {
            addCriterion("inquiry_time <", value, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("inquiry_time <=", value, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeIn(List<Date> values) {
            addCriterion("inquiry_time in", values, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeNotIn(List<Date> values) {
            addCriterion("inquiry_time not in", values, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeBetween(Date value1, Date value2) {
            addCriterion("inquiry_time between", value1, value2, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("inquiry_time not between", value1, value2, "inquiryTime");
            return (Criteria) this;
        }

        public Criteria andInquirierIdIsNull() {
            addCriterion("inquirier_id is null");
            return (Criteria) this;
        }

        public Criteria andInquirierIdIsNotNull() {
            addCriterion("inquirier_id is not null");
            return (Criteria) this;
        }

        public Criteria andInquirierIdEqualTo(Integer value) {
            addCriterion("inquirier_id =", value, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdNotEqualTo(Integer value) {
            addCriterion("inquirier_id <>", value, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdGreaterThan(Integer value) {
            addCriterion("inquirier_id >", value, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("inquirier_id >=", value, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdLessThan(Integer value) {
            addCriterion("inquirier_id <", value, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdLessThanOrEqualTo(Integer value) {
            addCriterion("inquirier_id <=", value, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdIn(List<Integer> values) {
            addCriterion("inquirier_id in", values, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdNotIn(List<Integer> values) {
            addCriterion("inquirier_id not in", values, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdBetween(Integer value1, Integer value2) {
            addCriterion("inquirier_id between", value1, value2, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierIdNotBetween(Integer value1, Integer value2) {
            addCriterion("inquirier_id not between", value1, value2, "inquirierId");
            return (Criteria) this;
        }

        public Criteria andInquirierNameIsNull() {
            addCriterion("inquirier_name is null");
            return (Criteria) this;
        }

        public Criteria andInquirierNameIsNotNull() {
            addCriterion("inquirier_name is not null");
            return (Criteria) this;
        }

        public Criteria andInquirierNameEqualTo(String value) {
            addCriterion("inquirier_name =", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameNotEqualTo(String value) {
            addCriterion("inquirier_name <>", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameGreaterThan(String value) {
            addCriterion("inquirier_name >", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameGreaterThanOrEqualTo(String value) {
            addCriterion("inquirier_name >=", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameLessThan(String value) {
            addCriterion("inquirier_name <", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameLessThanOrEqualTo(String value) {
            addCriterion("inquirier_name <=", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameLike(String value) {
            addCriterion("inquirier_name like", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameNotLike(String value) {
            addCriterion("inquirier_name not like", value, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameIn(List<String> values) {
            addCriterion("inquirier_name in", values, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameNotIn(List<String> values) {
            addCriterion("inquirier_name not in", values, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameBetween(String value1, String value2) {
            addCriterion("inquirier_name between", value1, value2, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierNameNotBetween(String value1, String value2) {
            addCriterion("inquirier_name not between", value1, value2, "inquirierName");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneIsNull() {
            addCriterion("inquirier_mobile_phone is null");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneIsNotNull() {
            addCriterion("inquirier_mobile_phone is not null");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneEqualTo(String value) {
            addCriterion("inquirier_mobile_phone =", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneNotEqualTo(String value) {
            addCriterion("inquirier_mobile_phone <>", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneGreaterThan(String value) {
            addCriterion("inquirier_mobile_phone >", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("inquirier_mobile_phone >=", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneLessThan(String value) {
            addCriterion("inquirier_mobile_phone <", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("inquirier_mobile_phone <=", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneLike(String value) {
            addCriterion("inquirier_mobile_phone like", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneNotLike(String value) {
            addCriterion("inquirier_mobile_phone not like", value, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneIn(List<String> values) {
            addCriterion("inquirier_mobile_phone in", values, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneNotIn(List<String> values) {
            addCriterion("inquirier_mobile_phone not in", values, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneBetween(String value1, String value2) {
            addCriterion("inquirier_mobile_phone between", value1, value2, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andInquirierMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("inquirier_mobile_phone not between", value1, value2, "inquirierMobilePhone");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumIsNull() {
            addCriterion("spare_stock_num is null");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumIsNotNull() {
            addCriterion("spare_stock_num is not null");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumEqualTo(Integer value) {
            addCriterion("spare_stock_num =", value, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumNotEqualTo(Integer value) {
            addCriterion("spare_stock_num <>", value, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumGreaterThan(Integer value) {
            addCriterion("spare_stock_num >", value, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("spare_stock_num >=", value, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumLessThan(Integer value) {
            addCriterion("spare_stock_num <", value, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumLessThanOrEqualTo(Integer value) {
            addCriterion("spare_stock_num <=", value, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumIn(List<Integer> values) {
            addCriterion("spare_stock_num in", values, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumNotIn(List<Integer> values) {
            addCriterion("spare_stock_num not in", values, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumBetween(Integer value1, Integer value2) {
            addCriterion("spare_stock_num between", value1, value2, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareStockNumNotBetween(Integer value1, Integer value2) {
            addCriterion("spare_stock_num not between", value1, value2, "spareStockNum");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeIsNull() {
            addCriterion("spare_sales_time is null");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeIsNotNull() {
            addCriterion("spare_sales_time is not null");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeEqualTo(Double value) {
            addCriterion("spare_sales_time =", value, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeNotEqualTo(Double value) {
            addCriterion("spare_sales_time <>", value, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeGreaterThan(Double value) {
            addCriterion("spare_sales_time >", value, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeGreaterThanOrEqualTo(Double value) {
            addCriterion("spare_sales_time >=", value, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeLessThan(Double value) {
            addCriterion("spare_sales_time <", value, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeLessThanOrEqualTo(Double value) {
            addCriterion("spare_sales_time <=", value, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeIn(List<Double> values) {
            addCriterion("spare_sales_time in", values, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeNotIn(List<Double> values) {
            addCriterion("spare_sales_time not in", values, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeBetween(Double value1, Double value2) {
            addCriterion("spare_sales_time between", value1, value2, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andSpareSalesTimeNotBetween(Double value1, Double value2) {
            addCriterion("spare_sales_time not between", value1, value2, "spareSalesTime");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeIsNull() {
            addCriterion("inquiry_tax_fee is null");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeIsNotNull() {
            addCriterion("inquiry_tax_fee is not null");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeEqualTo(Long value) {
            addCriterion("inquiry_tax_fee =", value, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeNotEqualTo(Long value) {
            addCriterion("inquiry_tax_fee <>", value, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeGreaterThan(Long value) {
            addCriterion("inquiry_tax_fee >", value, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("inquiry_tax_fee >=", value, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeLessThan(Long value) {
            addCriterion("inquiry_tax_fee <", value, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeLessThanOrEqualTo(Long value) {
            addCriterion("inquiry_tax_fee <=", value, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeIn(List<Long> values) {
            addCriterion("inquiry_tax_fee in", values, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeNotIn(List<Long> values) {
            addCriterion("inquiry_tax_fee not in", values, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeBetween(Long value1, Long value2) {
            addCriterion("inquiry_tax_fee between", value1, value2, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andInquiryTaxFeeNotBetween(Long value1, Long value2) {
            addCriterion("inquiry_tax_fee not between", value1, value2, "inquiryTaxFee");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeIsNull() {
            addCriterion("purchase_order_type is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeIsNotNull() {
            addCriterion("purchase_order_type is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeEqualTo(Integer value) {
            addCriterion("purchase_order_type =", value, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeNotEqualTo(Integer value) {
            addCriterion("purchase_order_type <>", value, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeGreaterThan(Integer value) {
            addCriterion("purchase_order_type >", value, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("purchase_order_type >=", value, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeLessThan(Integer value) {
            addCriterion("purchase_order_type <", value, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("purchase_order_type <=", value, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeIn(List<Integer> values) {
            addCriterion("purchase_order_type in", values, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeNotIn(List<Integer> values) {
            addCriterion("purchase_order_type not in", values, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("purchase_order_type between", value1, value2, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andPurchaseOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("purchase_order_type not between", value1, value2, "purchaseOrderType");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoIsNull() {
            addCriterion("marketing_activity_info is null");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoIsNotNull() {
            addCriterion("marketing_activity_info is not null");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoEqualTo(String value) {
            addCriterion("marketing_activity_info =", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoNotEqualTo(String value) {
            addCriterion("marketing_activity_info <>", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoGreaterThan(String value) {
            addCriterion("marketing_activity_info >", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoGreaterThanOrEqualTo(String value) {
            addCriterion("marketing_activity_info >=", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoLessThan(String value) {
            addCriterion("marketing_activity_info <", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoLessThanOrEqualTo(String value) {
            addCriterion("marketing_activity_info <=", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoLike(String value) {
            addCriterion("marketing_activity_info like", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoNotLike(String value) {
            addCriterion("marketing_activity_info not like", value, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoIn(List<String> values) {
            addCriterion("marketing_activity_info in", values, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoNotIn(List<String> values) {
            addCriterion("marketing_activity_info not in", values, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoBetween(String value1, String value2) {
            addCriterion("marketing_activity_info between", value1, value2, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andMarketingActivityInfoNotBetween(String value1, String value2) {
            addCriterion("marketing_activity_info not between", value1, value2, "marketingActivityInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoIsNull() {
            addCriterion("sales_info is null");
            return (Criteria) this;
        }

        public Criteria andSalesInfoIsNotNull() {
            addCriterion("sales_info is not null");
            return (Criteria) this;
        }

        public Criteria andSalesInfoEqualTo(String value) {
            addCriterion("sales_info =", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoNotEqualTo(String value) {
            addCriterion("sales_info <>", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoGreaterThan(String value) {
            addCriterion("sales_info >", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoGreaterThanOrEqualTo(String value) {
            addCriterion("sales_info >=", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoLessThan(String value) {
            addCriterion("sales_info <", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoLessThanOrEqualTo(String value) {
            addCriterion("sales_info <=", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoLike(String value) {
            addCriterion("sales_info like", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoNotLike(String value) {
            addCriterion("sales_info not like", value, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoIn(List<String> values) {
            addCriterion("sales_info in", values, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoNotIn(List<String> values) {
            addCriterion("sales_info not in", values, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoBetween(String value1, String value2) {
            addCriterion("sales_info between", value1, value2, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andSalesInfoNotBetween(String value1, String value2) {
            addCriterion("sales_info not between", value1, value2, "salesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagIsNull() {
            addCriterion("market_inquiry_price_flag is null");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagIsNotNull() {
            addCriterion("market_inquiry_price_flag is not null");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagEqualTo(Integer value) {
            addCriterion("market_inquiry_price_flag =", value, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagNotEqualTo(Integer value) {
            addCriterion("market_inquiry_price_flag <>", value, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagGreaterThan(Integer value) {
            addCriterion("market_inquiry_price_flag >", value, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("market_inquiry_price_flag >=", value, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagLessThan(Integer value) {
            addCriterion("market_inquiry_price_flag <", value, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagLessThanOrEqualTo(Integer value) {
            addCriterion("market_inquiry_price_flag <=", value, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagIn(List<Integer> values) {
            addCriterion("market_inquiry_price_flag in", values, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagNotIn(List<Integer> values) {
            addCriterion("market_inquiry_price_flag not in", values, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagBetween(Integer value1, Integer value2) {
            addCriterion("market_inquiry_price_flag between", value1, value2, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andMarketInquiryPriceFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("market_inquiry_price_flag not between", value1, value2, "marketInquiryPriceFlag");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(Long value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(Long value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(Long value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(Long value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(Long value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(Long value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<Long> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<Long> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(Long value1, Long value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(Long value1, Long value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoIsNull() {
            addCriterion("market_sales_info is null");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoIsNotNull() {
            addCriterion("market_sales_info is not null");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoEqualTo(String value) {
            addCriterion("market_sales_info =", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoNotEqualTo(String value) {
            addCriterion("market_sales_info <>", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoGreaterThan(String value) {
            addCriterion("market_sales_info >", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoGreaterThanOrEqualTo(String value) {
            addCriterion("market_sales_info >=", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoLessThan(String value) {
            addCriterion("market_sales_info <", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoLessThanOrEqualTo(String value) {
            addCriterion("market_sales_info <=", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoLike(String value) {
            addCriterion("market_sales_info like", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoNotLike(String value) {
            addCriterion("market_sales_info not like", value, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoIn(List<String> values) {
            addCriterion("market_sales_info in", values, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoNotIn(List<String> values) {
            addCriterion("market_sales_info not in", values, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoBetween(String value1, String value2) {
            addCriterion("market_sales_info between", value1, value2, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andMarketSalesInfoNotBetween(String value1, String value2) {
            addCriterion("market_sales_info not between", value1, value2, "marketSalesInfo");
            return (Criteria) this;
        }

        public Criteria andFeaturesIsNull() {
            addCriterion("features is null");
            return (Criteria) this;
        }

        public Criteria andFeaturesIsNotNull() {
            addCriterion("features is not null");
            return (Criteria) this;
        }

        public Criteria andFeaturesEqualTo(String value) {
            addCriterion("features =", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotEqualTo(String value) {
            addCriterion("features <>", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesGreaterThan(String value) {
            addCriterion("features >", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesGreaterThanOrEqualTo(String value) {
            addCriterion("features >=", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLessThan(String value) {
            addCriterion("features <", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLessThanOrEqualTo(String value) {
            addCriterion("features <=", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesLike(String value) {
            addCriterion("features like", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotLike(String value) {
            addCriterion("features not like", value, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesIn(List<String> values) {
            addCriterion("features in", values, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotIn(List<String> values) {
            addCriterion("features not in", values, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesBetween(String value1, String value2) {
            addCriterion("features between", value1, value2, "features");
            return (Criteria) this;
        }

        public Criteria andFeaturesNotBetween(String value1, String value2) {
            addCriterion("features not between", value1, value2, "features");
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