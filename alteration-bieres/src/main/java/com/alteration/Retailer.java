package com.alteration;

public record Retailer(
  String id,
  String name,
  String address,
  String city,
  String postalCode,
  String phone,
  String description,
  double lat,
  double lng
) { }
