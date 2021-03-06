package org.hoteia.qalingo.core.web.validation.contraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import org.hoteia.qalingo.core.web.validation.annotation.TwoFieldsLink;
import org.hoteia.qalingo.core.web.validation.annotation.TwoFieldsOr;

public class TwoFieldsOrValidator implements ConstraintValidator<TwoFieldsOr, TwoFieldsLink<? extends Object>> {
  private String[] highlightFieldNames;

  @Override
  public void initialize(TwoFieldsOr constraintAnnotation) {
    this.highlightFieldNames = constraintAnnotation.highlightFieldNames();
  }

  @Override
  public boolean isValid(TwoFieldsLink<? extends Object> value, ConstraintValidatorContext ctx) {
    if(value.getField() == null && value.getField2() == null) return false;

    String field1 = null;
    String field2 = null;
    if (value.getField() != null) field1 = value.getField().toString();
    if (value.getField2() != null) field2 = value.getField2().toString();

    boolean isValid = StringUtils.isNotEmpty(field1) || StringUtils.isNotEmpty(field2);
    if(!isValid) {
      ctx.disableDefaultConstraintViolation();
      String template = ctx.getDefaultConstraintMessageTemplate();
      for(String highlightFieldName  : highlightFieldNames) {
        ctx.buildConstraintViolationWithTemplate(template).addNode(highlightFieldName).addConstraintViolation();
        template = "_hidden_";
      }
    }
    return isValid;
  }
  
}