/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.mahout.math;

import org.apache.mahout.common.MutablePair;

/**
 * Decorates a vector with a floating point weight and an index.
 */
public class WeightedVector extends DecoratedVector<MutablePair<Double, Integer>> {
  private static final int INVALID_INDEX = -1;

  public WeightedVector(Vector v, double weight, int index) {
    super(v, new MutablePair<Double, Integer>(weight, index));
  }

  public WeightedVector(Vector v, Vector projection, int index) {
    super(v, new MutablePair<Double, Integer>(v.dot(projection), index));
  }

  public static WeightedVector project(Vector v, Vector projection) {
    return project(v, projection, INVALID_INDEX);
  }

  public static WeightedVector project(Vector v, Vector projection, int index) {
    return new WeightedVector(v, projection, index);
  }

  public double getWeight() {
    return getValue().getFirst();
  }

  public int getIndex() {
    return getValue().getSecond();
  }

  public void setWeight(double newWeight) {
    getValue().setFirst(newWeight);
  }

  public void setIndex(int index) {
    getValue().setSecond(index);
  }

  @Override
  public WeightedVector like() {
    return new WeightedVector(getVector().like(), getValue().getFirst(), getValue().getSecond());
  }

  @Override
  public String toString() {
    return String.format("index=%d, weight=%.2f, v=%s", getIndex(), getWeight(), getVector());
  }

  @Override
  public WeightedVector clone() {
    WeightedVector v = (WeightedVector)super.clone();
    v.setValue(v.getValue().clone());
    return v;
  }
}
