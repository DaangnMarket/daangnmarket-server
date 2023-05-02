package com.clone.daangnmarketserver.common;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Id<R> implements Serializable {

  private Class<R> reference;

  private Long value;

  protected Id() {
  }

  private Id(Class<R> reference, Long value) {
    this.reference = reference;
    this.value = value;
  }

  public static <R> Id<R> of(Class<R> reference, Long value) {
    Preconditions.checkArgument(reference != null, "reference must be provided.");
    Preconditions.checkArgument(value != null, "value must be provided.");

    return new Id<>(reference, value);
  }

  public Long value() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Id<?> id = (Id<?>) o;
    return com.google.common.base.Objects.equal(reference, id.reference)
        && com.google.common.base.Objects.equal(value, id.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(reference, value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("reference", reference)
        .append("value", value)
        .toString();
  }
}
