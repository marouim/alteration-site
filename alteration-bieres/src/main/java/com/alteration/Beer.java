package com.alteration;

import java.util.List;

public record Beer(
  String slug,
  String name,
  String category,
  String style,
  String abv,
  String batch,
  String description,
  List<String> notes,
  double price,
  String accent,
  int initialInventory,
  int remainingInventory,
  List<String> hops,
  String yeast,
  String technique,
  List<String> availableAt
) { }
