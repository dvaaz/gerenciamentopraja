//package gerenciamentorestaurante.projeto1.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//  @ExceptionHandler(IllegalArgumentException.class)
//  public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
//    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
//        .body("Erro de validação: " + ex.getMessage());
//  }
//}