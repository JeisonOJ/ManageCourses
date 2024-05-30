package com.jeison.courses.utils.message;

public class ErrorMessage {

  public static String idNotFound(String entity) {
      final String message = "There are no registers in %s with that id";
       return String.format(message, entity);
  }

  public static String idNotFound(String entity, String role) {
    final String message = "%s id is not valid for the role %s";
     return String.format(message, entity, role);
}

}
